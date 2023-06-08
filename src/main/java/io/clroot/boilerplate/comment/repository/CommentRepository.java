package io.clroot.boilerplate.comment.repository;

import io.clroot.boilerplate.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> ,CommentRepositoryCustom{

}
