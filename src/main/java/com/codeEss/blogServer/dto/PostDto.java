package com.codeEss.blogServer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private String name;
    private String content;
    private String postedBy;
    private Date date;
    private  int likeCount;
    private int viewCount;
    private List<String> tags;
}
