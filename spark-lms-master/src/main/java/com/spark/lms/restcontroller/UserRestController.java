package com.spark.lms.restcontroller;

import com.spark.lms.model.Category;
import com.spark.lms.model.Member;
import com.spark.lms.model.User;
import com.spark.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/rest/User")
public class UserRestController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = {"/", "/list"},  method = RequestMethod.GET)
    public String showMembersPage(Model model) {
        model.addAttribute("user", userService.getAllUsers());
        return "/User/user";
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addCategoryPage(Model model) {
        model.addAttribute("user", new User());
        return "/User/form";
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editCategoryPage(@PathVariable(name = "id") Long id, Model model) {
        User user = userService.getById(id);
        if( user != null ) {
            model.addAttribute("user", user);
            return "/User/form";
        } else {
            return "redirect:/rest/User/add";
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCategory(@Valid User user, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {

        if( user.getId() == null ) {
            userService.addNew(user);
            redirectAttributes.addFlashAttribute("successMsg", "'" + user.getUsername() + "' is added as a new category.");
            return "redirect:/rest/User/add";
        } else {
            User updateCategory = userService.addNew(user);
            redirectAttributes.addFlashAttribute("successMsg", "Changes for '" + user.getUsername() + "' are saved successfully. ");
            System.out.println(user);
            return "redirect:/rest/User/add";
        }
    }
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String removeCategory(@PathVariable(name = "id") Long id, Model model) {
        User user = userService.getById( id );
        if( user != null ) {

                userService.delete(id);

        }
        return "redirect:/User/user";
    }

}
