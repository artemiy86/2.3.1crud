package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserServiceImpl;

@Controller
@RequestMapping
public class UserController {

    @Autowired
    private UserServiceImpl service;

    @GetMapping("/")
    public String getRoot(ModelMap model){
        model.addAttribute("users",service.getAllUsers());
        return "index";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(value = "id") long id){
        service.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String addUserForm(ModelMap model){
        User user = new User();
        model.addAttribute("action","add");
        model.addAttribute("user", user);
        return "form";
    }

    @GetMapping("/edit")
    public String editUserForm(@RequestParam(value = "id") long id,
                           ModelMap model){
        model.addAttribute("action","edit");
        User user = service.getUserById(id);
        model.addAttribute("user",user);
        return "form";
    }

    @PostMapping("/edit")
    public String editUser(@RequestParam(value = "id") long id,
                           @RequestParam(value = "name") String name,
                           @RequestParam(value = "surname") String surname,
                           @RequestParam(value = "age") int age,
                           @RequestParam(value = "email") String email){
        service.editUser(id, name, surname, age, email);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String createUser(@RequestParam(value = "name") String name,
                             @RequestParam(value = "surname") String surname,
                             @RequestParam(value = "age") int age,
                             @RequestParam(value = "email") String email){
        service.createUser(name, surname, age, email);
        return "redirect:/";
    }
}
