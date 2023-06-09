package io.clroot.boilerplate.post.controller;


import static io.clroot.boilerplate.common.controller.ApiResult.OK;

import io.clroot.boilerplate.comment.controller.CommentDto;
import io.clroot.boilerplate.comment.controller.CommentRequest;
import io.clroot.boilerplate.common.config.support.Pageable;
import io.clroot.boilerplate.common.controller.ApiResult;
import io.clroot.boilerplate.common.exception.NotFoundException;
import io.clroot.boilerplate.common.model.Id;
import io.clroot.boilerplate.post.domain.Post;
import io.clroot.boilerplate.comment.service.CommentService;
import io.clroot.boilerplate.post.service.PostService;
import io.clroot.boilerplate.security.dto.SecuredUserDto;
import io.clroot.boilerplate.user.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @PostMapping(path = "/post")
    public ApiResult<PostDto> posting(
        @AuthenticationPrincipal SecuredUserDto securedUserDto,
        @RequestBody PostingRequest request
    ) {
        return OK(
            new PostDto(
                postService.write(securedUserDto.getUsername(), request))
        );
    }

    @GetMapping("/post/{postId}/user/{userId}")
    public ApiResult<PostDto> getOne(@PathVariable Long userId, @PathVariable Long postId) {
        return OK(postService.findById(Id.of(Post.class, postId), Id.of(User.class, userId)).map(
            PostDto::new).orElseThrow(() -> new NotFoundException("post id or user id Error")));
    }

    @GetMapping(path = "/post/list")
    public ApiResult<List<PostDto>> posts(Pageable pageable) {
        return OK(postService.getList(pageable.page(), pageable.size()).stream().map(PostDto::new)
            .toList()
        );
    }


}
