package com.porfolio.blogs.controller;

import com.porfolio.blogs.SequenceGeneratorService;
import com.porfolio.blogs.model.Blog;
import com.porfolio.blogs.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class BlogController {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;


    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> retrieveAllBlogs() {

        List<Blog> listAllBlogs = blogRepository.findAll();

        if(listAllBlogs.isEmpty())
            return new ResponseEntity<>("no blogs found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<?>>(listAllBlogs, HttpStatus.OK);

    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> retrieveBlogById(@PathVariable("id") Long blogId) {

        Optional<Blog> foundBlog = blogRepository.findById(blogId);

        if (!foundBlog.isPresent())
            return new ResponseEntity<>("No blog found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<Blog>(foundBlog.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createBlogPost(@RequestBody @Valid Blog blogBody, @RequestParam("token") String token) {


        Blog blog = new Blog();
        this.populateBlog(blog, blogBody);

       blogRepository.save(blog);


        return new ResponseEntity<Blog>(blog, HttpStatus.CREATED);
    }

    public void populateBlog(Blog blog, Blog blogBody) {
        blog.setBlogContent(blogBody.getBlogContent());
        blog.setBlogName(blogBody.getBlogName());
        blog.setBlogId(sequenceGeneratorService.generateSequence(Blog.SEQUENCE_NAME));
        blog.setTimestamp(LocalDateTime.now());
    }
}
