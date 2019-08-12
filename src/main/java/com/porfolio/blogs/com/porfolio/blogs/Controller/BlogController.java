package com.porfolio.blogs.com.porfolio.blogs.Controller;

import com.porfolio.blogs.com.porfolio.blogs.Model.Blog;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("blogs")
public class BlogController {

    public List<Blog> blogs = new ArrayList<>();

    @Value("${post.password}")
    private String passPhrase;


    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> retrieveAllBlogs() {

        if (blogs.isEmpty())
            return new ResponseEntity<>("no blogs found", HttpStatus.NOT_FOUND);


        return new ResponseEntity<List<?>>(blogs, HttpStatus.OK);

    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> retrieveBlogById(@PathVariable("id") String blogId) {

        Optional<Blog> theBlog = blogs.stream().filter(blog -> blog.getBlogId().equals(blogId)).findFirst();

        if (!theBlog.isPresent())
            return new ResponseEntity<>("blog not found", HttpStatus.NOT_FOUND);

        return new ResponseEntity<Blog>(theBlog.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createBlogPost(@RequestBody @Valid Blog blogBody,@RequestParam("token") String token) {

        if(!token.equals(passPhrase))
            return new ResponseEntity<>("Unauthorised to post blogs", HttpStatus.UNAUTHORIZED);

        Optional<Blog> theBlog = blogs.stream().filter(blog -> blog.getBlogId().equals(blogBody.getBlogId())).findFirst();

        if (theBlog.isPresent())
            return new ResponseEntity<>("blog already exists with Id", HttpStatus.CONFLICT);

        Blog blog = new Blog();
        blog.setBlogContent(blogBody.getBlogContent());
        blog.setBlogName(blogBody.getBlogName());
        blog.setBlogId(blogBody.getBlogId());
        blog.setTimestamp(LocalDateTime.now());

        blogs.add(blog);

        return new ResponseEntity<Blog>(blog, HttpStatus.CREATED);
    }
}
