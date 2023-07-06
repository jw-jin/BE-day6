package com.example.beday6.web;


import com.example.beday6.domain.blog.Article;
import com.example.beday6.service.BlogService;
import com.example.beday6.web.dto.ArticleListViewResponseDto;
import com.example.beday6.web.dto.ArticleViewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleListViewResponseDto> articles = blogService.findAll()
                .stream()
                .map(ArticleListViewResponseDto::new)
                .collect(Collectors.toList());
        model.addAttribute("articles", articles);
        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleListViewResponseDto(article));

        return "article";
    }

    @GetMapping("/newarticle/") // 글 작성 및 수정을 동시에 처리하게 해주는 메서드
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if(id == null) {
            model.addAttribute("article", new ArticleViewResponseDto());
        }
        else {
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponseDto(article));
        }

        return "newArticle";
    }


}
