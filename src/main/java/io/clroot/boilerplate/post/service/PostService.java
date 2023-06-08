package io.clroot.boilerplate.post.service;


import static com.google.common.base.Preconditions.checkArgument;
import static java.time.LocalDateTime.*;
import static org.apache.commons.lang3.StringUtils.*;

import io.clroot.boilerplate.common.model.Id;
import io.clroot.boilerplate.post.controller.PostDto;
import io.clroot.boilerplate.post.controller.PostingRequest;
import io.clroot.boilerplate.post.domain.Post;
import io.clroot.boilerplate.post.repository.PostRepository;
import io.clroot.boilerplate.user.model.User;
import io.clroot.boilerplate.user.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;




    @Transactional
    public Post write(String username, PostingRequest postingRequest) {
        checkArgument(isNotEmpty(username), "username must be provided.");
        User user = findUser(username);
        Post post =  Post.builder()
            .user(user)
            .contents(postingRequest.getContents())
            .comments(0)
            .build();
        return postRepository.save(post);
    }



    @Transactional(readOnly = true)
    public Optional<Post> findById(Id<Post, Long> postId, Id<User, Long> writerId) {
        checkArgument(writerId != null, "writerId must be provided.");
        checkArgument(postId != null, "postId must be provided.");

        return postRepository.findById(postId, writerId);
    }


    @Transactional(readOnly = true)
    public List<Post> getList(int page, int size) {
        return postRepository.getList(getPageable(page,size));

    }


    private int checkPage(int page) {
        return Math.max(page, 0);
    }

    private int checkSize(int size) {
        return (size < 1 || size > 10) ? 10 : size;
    }

    public Pageable getPageable(int page, int size) {
        page = checkPage(page);
        size = checkSize(size);
        return PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
    }

    private User findUser(String username){
        return userRepository.findByUsername(username).orElseThrow(()->new IllegalArgumentException());
    }


    private void update(Post post) {
        postRepository.update(post);
    }


}
