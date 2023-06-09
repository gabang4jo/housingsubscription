package io.clroot.boilerplate.comment.controller;

import static io.clroot.boilerplate.common.controller.ApiResult.OK;

import io.clroot.boilerplate.comment.service.CommentService;
import io.clroot.boilerplate.common.controller.ApiResult;
import io.clroot.boilerplate.common.model.Id;
import io.clroot.boilerplate.post.domain.Post;
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
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {


    private final CommentService commentService;

    @PostMapping(path = "post/{postId}/user/{userId}/comment")
    public ApiResult<CommentDto> comment(
        @AuthenticationPrincipal SecuredUserDto securedUserDto, @PathVariable Long userId,
        @PathVariable Long postId, @RequestBody CommentRequest request) {
        return OK(
            new CommentDto(
                commentService.write(
                    Id.of(Post.class, postId),
                    Id.of(User.class, userId),
                    securedUserDto.getUsername(),
                    request)
            )
        );
    }

    @GetMapping(path = "post/{postId}/user/{userId}/comment/list")
    public ApiResult<List<CommentDto>> comments(
        @PathVariable Long userId,
        @PathVariable Long postId) {
        return OK(
            commentService.findAll(Id.of(Post.class, postId), Id.of(User.class, userId)).stream().map(CommentDto::new).toList()
        );
    }
}
