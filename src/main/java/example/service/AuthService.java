package example.service;

import example.entity.User;
import example.model.AuthRegistrationDTO;
import example.model.AuthRequestDTO;
import example.model.AuthResponseDTO;
import example.repository.UserRepository;
import example.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Service
public class AuthService {

    private AuthenticationManager manager;
    private TokenGenerator generator;
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public AuthService(AuthenticationManager manager,
                       TokenGenerator generator,
                       UserRepository userRepository) {
        this.manager = manager;
        this.generator = generator;
        this.userRepository = userRepository;
    }

    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO requestDTO) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = generator.generateToken(authentication);
        AuthResponseDTO responseDTO = new AuthResponseDTO(token);
        if (responseDTO.getToken() == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<String> register(@RequestBody AuthRegistrationDTO registrationDTO) {
        if (userRepository.existsByUsername(registrationDTO.getUsername())) {
            return new ResponseEntity<>("That name has been taken", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        int id =  (int) Math.round(100 * Math.random());
        user.setId(id);
        user.setUsername(registrationDTO.getUsername());
        user.setPassword(encoder.encode(registrationDTO.getPassword()));
        user.setEmail(registrationDTO.getEmail());
        user.setCreationDate(new Date());
        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
