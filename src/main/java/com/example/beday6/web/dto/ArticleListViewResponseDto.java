package com.example.beday6.web.dto;


import com.example.beday6.domain.blog.Article;
import lombok.Getter;

@Getter
public class ArticleListViewResponseDto {
    private Long id;
    private String title;
    private String content;

    public ArticleListViewResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
