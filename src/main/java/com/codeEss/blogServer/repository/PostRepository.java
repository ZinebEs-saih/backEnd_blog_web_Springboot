package com.codeEss.blogServer.repository;

import com.codeEss.blogServer.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.stream.events.Comment;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {


    List<Post> findAllByNameContaining(String name);
}
