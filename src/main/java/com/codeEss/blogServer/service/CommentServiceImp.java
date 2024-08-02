package com.codeEss.blogServer.service;

import com.codeEss.blogServer.entity.Comment;
import com.codeEss.blogServer.entity.Post;
import com.codeEss.blogServer.repository.CommentRepository;
import com.codeEss.blogServer.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImp implements CommentService{
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository  postRepository;

    public Comment createComment(Long postId, String Content,String postedBy){
        Optional<Post> optionalPost=postRepository.findById(postId);
        if(optionalPost.isPresent()){
            Comment comment=new Comment();
            comment.setPost(optionalPost.get());
            comment.setContent(Content);
            comment.setPostedBy(postedBy);
            comment.setCreatedAt(new Date());
            return commentRepository.save(comment);

        }
        throw  new EntityNotFoundException("post Not Found");

    }

    public List<Comment>getCommentByPostId(Long postId){
        return commentRepository.findByPostId(postId);
    }
}
