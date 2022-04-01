package com.social.feigndata.aspect;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.List;

@Aspect
@Component
public class RateLimiterHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RateLimiterHandler.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private DefaultRedisScript<Long> redisScript;

    @PostConstruct
    public void init() {
        redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        //Lua脚本放置在classpath下，通过ClassPathResource进行加载
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("limiter.lua")));
        LOGGER.info("RateLimiterHandler[分布式限流处理器]脚本加载完成");
    }

    @Before("@annotation(rateLimiter)")
    public void before(JoinPoint joinPoint, ScRateLimiter rateLimiter) {
        LOGGER.info("RateLimiterHandler[分布式限流处理器]开始执行限流操作");
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("the Annotation @RateLimiter must used on method!");
        }
        Method method = ((MethodSignature) signature).getMethod();
        // 限流模块key
        String parseKey = parseKey(rateLimiter.key(), method, joinPoint.getArgs());
        String limitKey = String.format("%s:%s:%s:%s", joinPoint.getTarget().getClass().getSimpleName(), method.getName(),
                parseKey, "1");
        Preconditions.checkNotNull(limitKey);
        // 限流阈值
        long limitTimes = rateLimiter.limit();
        // 限流超时时间
        long expireTime = rateLimiter.expire();
        LOGGER.info("RateLimiterHandler[分布式限流处理器]参数值为-limitKe{},limitTimes={},limitTimeout={}", limitKey, limitTimes, expireTime);
        List<String> keyList = Lists.newArrayList();
        // 设置key值为注解中的值
        keyList.add(limitKey);
        //调用脚本并执行
        Long result = (Long) redisTemplate.execute(redisScript, keyList, String.valueOf(expireTime), String.valueOf(limitTimes));
        if (result == 0L) {
            LOGGER.info("由于超过单位时间={}-允许的请求次数={} [触发限流]", expireTime, limitTimes);
            throw new RuntimeException("触发限流");
        }
        LOGGER.info("RateLimiterHandler[分布式限流处理器]限流执行结果-result={},请求[正常]响应", result);
    }

    /**
     * 解析SPEL
     *
     * @param key
     * @param method
     * @param args
     * @return
     */
    private String parseKey(String key, Method method, Object[] args) {
        if (StringUtils.isEmpty(key)) {
            return key;
        }
        // 使用变量方式传入业务动态数据
        if (!key.matches("^#.*.$")) {
            return key;
        }

        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);
        if (paraNameArr == null || paraNameArr.length < 1) {
            return key;
        }
        //使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        //SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SPEL上下文中
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context, String.class);
    }
}
