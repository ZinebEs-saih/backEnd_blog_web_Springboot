package com.codeEss.blogServer.service;

import com.codeEss.blogServer.dto.PostDto;
import com.codeEss.blogServer.entity.Post;
import com.codeEss.blogServer.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostServiceImp implements PostService{

    @Autowired
    private PostRepository postRepository;

    public  Post savePost(PostDto postdto, MultipartFile File)throws IOException{
        Path path= Paths.get(System.getProperty("user.home"),"images","posts");
        if(!Files.exists(path)){
            Files.createDirectories(path);
        }
        String fileID=UUID.randomUUID().toString();
        Path filepath=Paths.get(System.getProperty("user.home"),"images","posts",fileID+".png");
        Files.copy(File.getInputStream(),filepath);
        Post post= Post.builder()
                .name(postdto.getName())
                .content(postdto.getContent())
                .tags(postdto.getTags())
                .postedBy(postdto.getPostedBy())
                .date(new Date())
                .likeCount(0)
                .viewCount(0)
                .img(filepath.toUri().toString()).build();

        return postRepository.save(post);

    }

    @Override
    public List<Post> getAllPosts() throws IOException {
        return postRepository.findAll();
    }


    @Override
    public byte[] getImgPost( Long id) throws IOException {
        Post post = postRepository.findById(id).get();
        String filePath = post.getImg();
        return Files.readAllBytes(Path.of(URI.create(filePath)));
    }

    public Post getPostById(Long postId){
        Optional<Post> optionalPost=postRepository.findById(postId);
        if(optionalPost.isPresent()){
            Post post=optionalPost.get();
            post.setViewCount((post.getViewCount() + 1));
            return  postRepository.save(post);
        }else{
            throw new EntityNotFoundException("Post not found");
        }
    }

    public  void  likePost(Long postId){
        Optional<Post> optionalPost=postRepository.findById(postId);
        if(optionalPost.isPresent()){
            Post post=optionalPost.get();
            post.setLikeCount(post.getLikeCount()+1);
            postRepository.save(post);
        }else{
            throw new EntityNotFoundException("Post not found");
        }
    }

    public List<Post>searchByName(String name){
        return postRepository.findAllByNameContaining(name);
    }





}
