package com.example.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/posts/{id}")
    public String posts_show(@PathVariable("id") Long id, Model model) {
        Post post = postService.postById(id);
        model.addAttribute("post", post);
        return "posts/show";
    }
}
