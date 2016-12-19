package hu.schonhertz.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import hu.schonhertz.training.jdbctemplate.BlogJDBCTemplate;
import hu.schonhertz.training.pojo.Blog;


@Controller
@RequestMapping("/blog")
public class BlogController {
  
  @Autowired
  private BlogJDBCTemplate blogJDBCTemplate;
  
  @RequestMapping(method = RequestMethod.GET)
  public String listBlogs(Model model) {
    List<Blog> blogs = blogJDBCTemplate.getAllBlog();
    model.addAttribute("blogs", blogs);
    return "blog/blogindex";
  }
  
  /*
  @RequestMapping(path = "/details", method = RequestMethod.GET)
  public String blogDetails(@RequestParam(name="id") int id, Model model) {
    Blog blog = blogJDBCTemplate.getBlogById(id);
    model.addAttribute("blog", blog);
    return "blog/details";
  }*/
  
  @RequestMapping(path = "/details/{id}", method = RequestMethod.GET)
  public String blogDetails(@PathVariable int id, Model model) {
    Blog blog = blogJDBCTemplate.getBlogById(id);
    model.addAttribute("blog", blog);
    return "blog/details";
  }
  
  @RequestMapping(path = "/create", method = RequestMethod.GET)
  public ModelAndView newBlog(Model model) {
    return new ModelAndView("blog/create", "command", new Blog());
  }
  
  @RequestMapping(path = "/create", method = RequestMethod.POST)
  public String newBlog(@ModelAttribute Blog blog, Model model) {
    System.out.println(blog.getId());
    System.out.println(blog.getTitle());
    System.out.println(blog.getCreator());
    System.out.println(blog.getText());
    blogJDBCTemplate.createBlog(blog.getCreator(), blog.getTitle(), blog.getText());
    return "redirect:/blog";
  }
  
}
