package io.clroot.boilerplate.post.repository;


import io.clroot.boilerplate.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> ,PostRepositoryCustom{

}
