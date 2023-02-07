package AccioJob.Book_My_Show_Backend.Controllers;

import AccioJob.Book_My_Show_Backend.DTOs.UserRequestDto;
import AccioJob.Book_My_Show_Backend.Models.User;
import AccioJob.Book_My_Show_Backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public String addUser(@RequestBody UserRequestDto userRequestDto){
        return userService.createUser(userRequestDto);
    }

    //Find user by name
    @GetMapping("/find_by_name")
    public User findUserByName(@RequestParam String name){
        return userService.findByUserName(name);
    }

    //Find all users
    public List<User> findAllUser(){
        return userService.findAllUser();
    }
}
