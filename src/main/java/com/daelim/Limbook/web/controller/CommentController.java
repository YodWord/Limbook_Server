package com.daelim.Limbook.web.controller;

import com.daelim.Limbook.domain.Comment;
import com.daelim.Limbook.domain.User;
import com.daelim.Limbook.web.SessionConst;
import com.daelim.Limbook.web.argumentResolver.Login;
import com.daelim.Limbook.web.controller.dto.CommentDTO.CreateCommentDTO;
import com.daelim.Limbook.web.controller.dto.CommentDTO.UpdateCommentDTO;
import com.daelim.Limbook.web.service.comments.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RequestMapping("/comments")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    /**
     *   댓글 작성
     *
     * */
    @PostMapping
    public ResponseEntity<Object> createComment (@RequestBody @Validated CreateCommentDTO createCommentDTO, BindingResult bindingResult,
                                                @Login User user
                                                  /*@SessionAttribute(value = SessionConst.LOGIN_USER, required = false) User user*/) throws Exception{

        HashMap<String, Object> response = new HashMap<>();

        if(user == null){
            response.put("result", "로그인이 필요합니다.");
            return ResponseEntity.badRequest().body(response);
            //throw new Exception("로그인이 필요합니다.");
        }
        if(bindingResult.hasErrors()){
            response.put("result", "입력값 확인 필요");
            return ResponseEntity.badRequest().body(response);
        }

        Comment comment = new Comment(createCommentDTO, user);
        Comment saveComment = commentService.createComment(comment, user);

        response.put("result","성공");
        response.put("comment", saveComment);


        return ResponseEntity.ok().body(response);
    }

    /**
     *   댓글 수정
     *
     * */
    @PatchMapping("/{commentId}")
    public ResponseEntity<Object> updateComment(@RequestBody @Validated UpdateCommentDTO updateCommentDTO,
                                                 @PathVariable Integer commentId, BindingResult bindingResult,
                                                 @Login User user
                                                 /*@SessionAttribute(name = SessionConst.LOGIN_USER, required = false)User user*/) throws Exception{

        HashMap<String, Object> response = new HashMap<>();

        if(user == null){
            response.put("result", "로그인이 필요합니다.");
            return ResponseEntity.badRequest().body(response);
            //throw new Exception("로그인이 필요합니다.");
        }

        if(bindingResult.hasErrors()){
            response.put("result", "입력값 확인 필요");
            return ResponseEntity.badRequest().body(response);
        }


        Comment updateComment = commentService.updateComment(commentId, updateCommentDTO, user);

        response.put("result", "성공");
        response.put("comment", updateComment);

        return ResponseEntity.ok().body(response);
    }

    /**
     *   댓글 삭제
     *
     * */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Object> deleteComment (@PathVariable Integer commentId,
                                                  @Login User user
                                                  /*@SessionAttribute(name = SessionConst.LOGIN_USER, required = false)User user*/) throws Exception{

        HashMap<String, Object> response = new HashMap<>();

        if(user == null){
            response.put("result", "로그인이 필요합니다.");
            return ResponseEntity.badRequest().body(response);
            //throw new Exception("로그인이 필요합니다.");
        }

        Comment deleteComment = commentService.deleteComment(commentId, user);

        response.put("result", "성공");
        response.put("comment", deleteComment);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<Object> findCommentByBoardId(@PathVariable Integer boardId) throws Exception {
        HashMap<String, Object> response = new HashMap<>();

        List<Comment> commentList = commentService.findByBoardId(boardId);

        response.put("result","성공");
        response.put("commentList", commentList);

        return ResponseEntity.ok().body(response);
   }


}
