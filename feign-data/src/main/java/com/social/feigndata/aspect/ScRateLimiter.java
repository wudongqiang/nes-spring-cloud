package com.social.feigndata.aspect;

import java.lang.annotation.*;

/**
 * 限流
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ScRateLimiter {
    /**
     * 限流key
     *
     * @return
     */
    String key() default "rate:limiter:";

    /**
     * 是否加入用户标识
     *
     * @return
     */
    boolean useUser() default false;

    /**
     * 单位时间限制通过请求数
     *
     * @return
     */
    long limit() default 1;

    /**
     * 过期时间，单位秒
     *
     * @return
     */
    long expire() default 1;

    /**
     * 需要排除的class
     *
     * @return
     */
    Class[] excludeClass() default Void.class;
}
