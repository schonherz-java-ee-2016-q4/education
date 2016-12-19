package hu.schonhertz.training.rest;

import hu.schonhertz.training.jdbctemplate.BlogJDBCTemplate;
import hu.schonhertz.training.pojo.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ikant on 2016. 07. 26..
 */
@RestController
@RequestMapping(path = "/api/blogs", produces = "application/json")
public class RestBlogController {


    @Autowired
    private BlogJDBCTemplate blogJDBCTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listBlogs() {
        List<Blog> blogs = blogJDBCTemplate.getAllBlog();
        return new ResponseEntity(blogs, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addBlog(@RequestBody Blog blog) {
        blogJDBCTemplate.createBlog(blog.getCreator(), blog.getTitle(), blog.getText());
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{blogId}")
    public ResponseEntity getBlog(@PathVariable("blogId") int blogId) {
        Blog blog = blogJDBCTemplate.getBlogById(blogId);
        return new ResponseEntity(blog, HttpStatus.OK);

    }
}
