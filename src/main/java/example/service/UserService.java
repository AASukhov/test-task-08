package example.service;

import example.model.QuoteResponseDTO;
import example.model.UserResponseDTO;
import example.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private UserRepository repository;

    public UserService (UserRepository repository) {
        this.repository = repository;
    }

    public List<UserResponseDTO> showAllUsers() {
        return repository.findAll().stream().map(u -> new UserResponseDTO(u.getId(),
                u.getUsername(), u.getEmail(),u.getCreationDate())).collect(Collectors.toList());
    }
}
