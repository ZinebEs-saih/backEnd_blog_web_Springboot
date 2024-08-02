package com.codeEss.blogServer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;
    private String postedBy;
    private String img;
    private Date date;
    private  int likeCount;
    private int viewCount;
    private List<String> tags;

}
