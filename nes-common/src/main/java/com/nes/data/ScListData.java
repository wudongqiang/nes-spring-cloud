package com.nes.data;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 用于包装list数据类型
 *
 * @author wdq
 * @date 2018-08-23-上午10:23
 */
public class ScListData<T> {

    private Collection<T> content;

    public ScListData() {
    }

    private ScListData(Collection<T> content) {
        this.content = content;
    }

    public static <T> ScListData<T> of(Collection<T> value) {
        return new ScListData<>(value);
    }

    public List<T> toList() {
        if (CollectionUtils.isEmpty(content)) {
            return new ArrayList<>(0);
        }
        return new ArrayList<>(content);
    }

    public Set<T> toSet() {
        if (CollectionUtils.isEmpty(content)) {
            return new HashSet<>(0);
        }
        return new HashSet<>(content);
    }

    public Collection<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
