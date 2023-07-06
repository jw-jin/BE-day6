package com.example.beday6.web.dto;


import com.example.beday6.domain.blog.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
public class ArticleViewResponseDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createAt;

    public ArticleViewResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createAt = article.getCreateAt();
    }
}
