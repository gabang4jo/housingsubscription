package io.clroot.boilerplate.comment.controller;

import io.clroot.boilerplate.comment.domain.Comment;
import io.clroot.boilerplate.post.domain.Post;
import io.clroot.boilerplate.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentRequest {


    private String contents;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("contents", contents)
            .toString();
    }

}
