package com.jungle.spring_study_forrest1398.controller;

import com.jungle.spring_study_forrest1398.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// class level 맵핑
@RequestMapping("/post")
public class PostController {

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }
    private final PostService postService;


}
