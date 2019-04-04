package kr.hs.dgsw.web_0326.Controller;

import kr.hs.dgsw.web_0326.Domain.User;
import kr.hs.dgsw.web_0326.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/adduser")
    public User addUser(@RequestBody User user) {
        return this.userService.addUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public boolean deleteUser(@PathVariable  Long id){
        return this.userService.deleteUser(id);
    }

    @PutMapping("/modifyUser")
    public User modifyUser(@RequestBody User user){
        return this.userService.modifyUser(user);
    }

    @GetMapping("/getUser")
    public List<User> getUser(){
        return this.userService.getUser();
    }

    @GetMapping("/getOneUser/{id}")
    public User getUser(@PathVariable Long id){
        return this.userService.getOneUser(id);
    }
}
