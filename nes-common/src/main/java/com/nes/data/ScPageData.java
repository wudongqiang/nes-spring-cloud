package com.nes.data;

import org.springframework.data.domain.Page;
import org.springframework.data.util.Streamable;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ScPageData<T> implements Streamable<T> {

    private ScPage page;
    private List<T> content;

    public ScPageData() {
    }

    public ScPageData(Page<T> page) {
        this.page = new ScPage(page.getTotalElements(), page.getContent().size());
        this.content = page.getContent();
    }

    public ScPageData(@NotNull List<T> content, long total) {
        if (content == null) {
            this.page = new ScPage(total, 0);
        } else {
            this.page = new ScPage(total, content.size());
        }
        this.content = content;
    }

    public <U> ScPageData<U> map(Function<? super T, ? extends U> converter) {
        return new ScPageData<>(this.getConvertedContent(converter), this.page.getTotal());
    }

    /**
     * Applies the given {@link Function} to the content of the {@link org.springframework.data.domain.Chunk}.
     *
     * @param converter must not be {@literal null}.
     * @return
     */
    protected <U> List<U> getConvertedContent(Function<? super T, ? extends U> converter) {
        Assert.notNull(converter, "Function must not be null!");
        return this.stream().map(converter::apply).collect(Collectors.toList());
    }

    @Override
    public Iterator<T> iterator() {
        return content.iterator();
    }

    public ScPage getPage() {
        return page;
    }

    public void setPage(ScPage page) {
        this.page = page;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
