package io.clroot.boilerplate.post.repository;


import io.clroot.boilerplate.common.model.Id;
import io.clroot.boilerplate.post.domain.Post;
import io.clroot.boilerplate.user.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    Optional<Post> findById(Id<Post, Long> postId, Id<User, Long> writerId);
    List<Post> getList(Pageable pageable);


    void update(Post post);

}
