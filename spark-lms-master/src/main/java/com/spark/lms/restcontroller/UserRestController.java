package com.spark.lms.restcontroller;

import com.spark.lms.model.Member;
import com.spark.lms.model.User;
import com.spark.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
