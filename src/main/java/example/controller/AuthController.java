package example.controller;

import example.model.AuthRegistrationDTO;
import example.model.AuthRequestDTO;
import example.model.AuthResponseDTO;
import example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class AuthController {

    private AuthService service;

    @Autowired
    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login (@RequestBody AuthRequestDTO requestDTO) {
        return service.login(requestDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody AuthRegistrationDTO registrationDTO) {
        return service.register(registrationDTO);
    }
}
