package example.controller;

import example.model.UserResponseDTO;
import example.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService service;

    public UserController (UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserResponseDTO> showAllUsers() {
        return service.showAllUsers();
    }
}
