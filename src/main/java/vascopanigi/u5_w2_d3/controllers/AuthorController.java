package vascopanigi.u5_w2_d3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vascopanigi.u5_w2_d3.entities.Author;
import vascopanigi.u5_w2_d3.services.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {
@Autowired
private AuthorService authorService;

    //1. GET
    @GetMapping
    public Page<Author> getAuthors(@RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "id") String sortBy){
        return this.authorService.getAuthors(pageNum, pageSize, sortBy);
    }

    //2. POST
    @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
    public Author saveAuthor(@RequestBody Author body){
        return authorService.save(body);
    }

}
