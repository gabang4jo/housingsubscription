package io.clroot.boilerplate.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.clroot.boilerplate.common.model.Id;
import io.clroot.boilerplate.post.domain.Post;
import io.clroot.boilerplate.post.domain.QPost;
import io.clroot.boilerplate.user.model.QUser;
import io.clroot.boilerplate.user.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public class PostRepositoryImpl implements PostRepositoryCustom {


    private final JPAQueryFactory jpaQueryFactory;
    private final QPost post = QPost.post;
    private final QUser user = QUser.user;

    public PostRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Optional<Post> findById(Id<Post, Long> postId, Id<User, Long> writerId) {
        Post findPost = jpaQueryFactory.select(post)
            .from(post)
            .join(post.user, user)
            .where(post.id.eq(postId.value()).and(post.user.id.eq(writerId.value()))).fetchOne();
        //단 건 조회, 없으면  null 반환

        return Optional.ofNullable(findPost);
    }

    @Override
    public List<Post> getList(Pageable pageable) {
        return jpaQueryFactory.select(post)
            .from(post)
            .join(post.user, user)
            .offset(pageable.getOffset())
            .limit(pageable.getPageNumber())
            .fetch(); //  리스트 조회, 데이터 없으면 빈 리스트 반환

    }

    @Override
    public void update(Post updatedPost) {
        jpaQueryFactory.update(QPost.post)
            .set(post.contents, updatedPost.getContents())
            .set(post.comments, updatedPost.getComments())
            .where(post.id.eq(updatedPost.getId()))
            .execute();
    }
}
