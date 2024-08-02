package com.codeEss.blogServer.service;

import com.codeEss.blogServer.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Long postId, String Content, String postedBy);
    List<Comment> getCommentByPostId(Long postId);
}
