package io.clroot.boilerplate.comment.repository;

import io.clroot.boilerplate.comment.domain.Comment;
import java.util.List;

public interface CommentRepositoryCustom {

    List<Comment> getList(Long postId);

}
