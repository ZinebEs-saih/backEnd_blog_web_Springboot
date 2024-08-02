package com.codeEss.blogServer.service;

import com.codeEss.blogServer.dto.PostDto;
import com.codeEss.blogServer.entity.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PostService {
    Post savePost(PostDto post, MultipartFile File)throws IOException;
    List<Post> getAllPosts()throws IOException ;
    public byte[] getImgPost(Long id) throws IOException;
    Post getPostById(Long postId);
    void  likePost(Long postId);
    List<Post>searchByName(String name);
}
