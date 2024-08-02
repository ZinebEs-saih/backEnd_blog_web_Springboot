package com.codeEss.blogServer.controller;

import com.codeEss.blogServer.dto.PostDto;
import com.codeEss.blogServer.entity.Post;
import com.codeEss.blogServer.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostService postService;
    @PostMapping
    public ResponseEntity<?> createPost(PostDto postDto, @RequestParam("img") MultipartFile file){
        try {
            Post createdPost=postService.savePost(postDto,file);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
        }catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts());
        }catch(Exception ex){
            ex.printStackTrace();
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        try {
            byte[] image = postService.getImgPost(id);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/{postId}")
    public  ResponseEntity<?>getPostById(@PathVariable Long postId){
        try {
            Post post=postService.getPostById(postId);
            return ResponseEntity.ok().body(post);

        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
    @PutMapping("/{postId}/like")
    public ResponseEntity<?> likePost(@PathVariable Long postId){
        try {
            postService.likePost(postId);
            return ResponseEntity.ok(new String[]{"Post liked successfully"});

        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
    @GetMapping("search/{name}")
    public ResponseEntity<?>SearchByName(@PathVariable String name){
        try {
            return ResponseEntity.ok(postService.searchByName(name));
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }







}
