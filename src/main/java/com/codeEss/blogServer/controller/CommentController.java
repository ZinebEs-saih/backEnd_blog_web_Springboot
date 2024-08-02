package com.codeEss.blogServer.controller;

import com.codeEss.blogServer.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("comment/create")
    public ResponseEntity<?>savePost(@RequestParam Long postId,@RequestBody String content,@RequestParam String postedBy){
        try{
            return ResponseEntity.ok(commentService.createComment(postId,content,postedBy));

        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
    @GetMapping("comments/{postId}")
    public ResponseEntity<?>getCommentByPostId(@PathVariable Long postId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentByPostId(postId));
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
