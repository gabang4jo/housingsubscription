package io.clroot.boilerplate.comment.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import io.clroot.boilerplate.comment.domain.Comment;
import io.clroot.boilerplate.comment.domain.QComment;
import io.clroot.boilerplate.user.model.QUser;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom{




    private final JPAQueryFactory jpaQueryFactory;

    private final QComment comment = QComment.comment;
    private final QUser user = QUser.user;


    @Override
    public List<Comment> getList(Long postId) {
        return jpaQueryFactory.selectFrom(comment)
            .join(comment.user,user)
            .where(comment.post.id.eq(postId)).fetch();
    }
}
