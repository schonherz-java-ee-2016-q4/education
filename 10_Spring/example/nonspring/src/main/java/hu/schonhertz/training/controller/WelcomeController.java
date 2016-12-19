package hu.schonhertz.training.controller;

import hu.schonhertz.training.dao.BlogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ikant on 2016. 07. 26..
 */
@Controller
@RequestMapping("/")
public class WelcomeController {

    @Autowired
    private BlogDao blogDao;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loadPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("welcome");
        model.addAttribute("blogs", blogDao.getAllBlog());
        return modelAndView;
    }
}
