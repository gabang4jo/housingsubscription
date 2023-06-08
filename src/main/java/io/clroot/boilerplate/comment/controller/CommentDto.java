package io.clroot.boilerplate.comment.controller;

import io.clroot.boilerplate.comment.domain.Comment;
import io.clroot.boilerplate.common.model.Writer;
import java.time.LocalDateTime;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
public class CommentDto {

    private Long id;

    private String contents;
    private Writer writer;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public CommentDto(Comment source){
        this.id = source.getId();
        this.contents = source.getContents();
        this.writer = new Writer(source.getUser().getEmail(),source.getUser().getName());
        this.createdAt = source.getCreatedAt();
        this.updatedAt = source.getUpdatedAt();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("id", id)
            .append("contents", contents)
            .append("writer", writer)
            .append("createdAt", createdAt)
            .append("updatedAt", updatedAt)
            .toString();
    }
}
