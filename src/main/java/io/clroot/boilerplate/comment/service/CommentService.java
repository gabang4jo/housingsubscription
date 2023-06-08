package io.clroot.boilerplate.comment.service;

import static com.google.common.base.Preconditions.checkArgument;
import static java.time.LocalDateTime.*;
import static java.util.Collections.emptyList;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import io.clroot.boilerplate.common.model.Id;
import io.clroot.boilerplate.comment.controller.CommentRequest;
import io.clroot.boilerplate.comment.domain.Comment;
import io.clroot.boilerplate.post.domain.Post;
import io.clroot.boilerplate.comment.repository.CommentRepository;
import io.clroot.boilerplate.post.repository.PostRepository;
import io.clroot.boilerplate.user.model.User;
import io.clroot.boilerplate.user.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final CommentRepository commentRepository;


    @Transactional
    public Comment write(Id<Post, Long> postId, Id<User, Long> postWriterId, String username,
        CommentRequest comment) {
        checkArgument(comment != null, "comment must be provided.");
        checkArgument(postId != null, "postId must be provided.");
        checkArgument(postWriterId != null, "postId must be provided.");
        checkArgument(isNotEmpty(username), "username must be provided.");

        return findPost(postId, postWriterId)
            .map(post -> {
                post.incrementAndGetComments();
                postRepository.update(post);
                User user = findUser(username);
                return commentRepository.save(
                    Comment.builder().user(user).post(post).contents(comment.getContents()).build());
            })
            .orElseThrow(IllegalArgumentException::new);
    }

    @Transactional(readOnly = true)
    public List<Comment> findAll(Id<Post, Long> postId, Id<User, Long> postWriterId) {
        return findPost(postId, postWriterId)
            .map(post -> commentRepository.getList(post.getId()))
            .orElse(emptyList());
    }


    private Optional<Post> findPost(Id<Post, Long> postId, Id<User, Long> postWriterId) {
        checkArgument(postId != null, "postId must be provided.");
        checkArgument(postWriterId != null, "postWriterId must be provided.");

        return postRepository.findById(postId, postWriterId);
    }

    private User findUser(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException());
    }

}
