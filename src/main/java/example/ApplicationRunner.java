package example;

import example.entity.User;
import example.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public ApplicationRunner(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {
        String username = "alex";
        String email = "alex@mail.com";
        String password = encoder.encode("0000");
        Date date = new Date();
        User user = new User(username,password,email,date);
        userRepository.save(user);
    }
}
