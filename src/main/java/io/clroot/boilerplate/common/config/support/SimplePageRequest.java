package io.clroot.boilerplate.common.config.support;


import static com.google.common.base.Preconditions.checkArgument;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class SimplePageRequest implements Pageable {

    private final int page;

    private final int size;

    public SimplePageRequest() {
        this(0, 5);
    }

    public SimplePageRequest(int page, int size) {
        checkArgument(page >= 0, "Offset must be greater or equals to zero.");
        checkArgument(size >= 1, "Limit must be greater than zero.");

        this.page = page;
        this.size = size;
    }

    @Override
    public int page() {
        return page;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("page", page)
            .append("size", size)
            .toString();
    }


}