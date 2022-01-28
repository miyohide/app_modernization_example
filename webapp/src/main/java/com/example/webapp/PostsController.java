package com.example.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PostsController {
    private PostService postService;

    public PostsController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts/")
    public String posts_index(Model model) {
        List<Post> posts = postService.allPosts();
        model.addAttribute("posts", posts);
        return "posts/index";
    }
}
