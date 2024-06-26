package vascopanigi.u5_w2_d3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vascopanigi.u5_w2_d3.entities.BlogPost;
import vascopanigi.u5_w2_d3.services.BlogPostPayload;
import vascopanigi.u5_w2_d3.services.BlogPostService;


@RestController
@RequestMapping("/blogposts")
public class BlogPostController {
    @Autowired
    private BlogPostService blogPostService;

    //1. GET
    @GetMapping
    private Page<BlogPost> geetBlogPosts(@RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy){
        return this.blogPostService.getBlogPosts(pageNum, pageSize, sortBy);
    }

    //2. POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private BlogPost save(@RequestBody BlogPostPayload body){
        return this.blogPostService.save(body);
    }
}
