package com.example.beday6.service;


import com.example.beday6.domain.blog.Article;
import com.example.beday6.repository.BlogRepository;
import com.example.beday6.web.dto.AddArticleRequestDto;
import com.example.beday6.web.dto.UpdateArticleRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    //save
    public Article save(AddArticleRequestDto requestDto) {
        return blogRepository.save(requestDto.toEntity());

    }
    // all read

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("article not exist: " + id));
    }

    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

    // 수정 하는 코드
    // 서비스 레이어는 작업의 흐름과 처리의 순서를 설정 및 명세
    @Transactional
    public Article update(Long id, UpdateArticleRequestDto requestDto) {
        // step 1 데이터 조회
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("article not exist: " + id));
        // step 2 서비스단에서 조회된 데이터 업데이트 코드
        article.update(requestDto.getTitle(), requestDto.getContent());
        return article;
    }

}
