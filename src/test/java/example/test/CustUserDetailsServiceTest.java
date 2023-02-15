package example.test;

import example.repository.UserRepository;
import example.service.CustUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CustUserDetailsServiceTest {

    @InjectMocks
    private CustUserDetailsService service;

    @Mock
    private UserRepository userRepository;

    public static final String USERNAME = "alex";
    public static final String PASSWORD = "0000";
    public static final String EMAIL = "alex@mail.com";
    public static final example.entity.User USER_1 = new example.entity.User(USERNAME,PASSWORD,EMAIL,new Date());

    public static final UserDetails USER_DETAILS = User.builder()
            .username(USERNAME)
            .password(PASSWORD)
            .authorities(new ArrayList<>())
            .build();

    @BeforeEach
    void setUp() {
        Mockito.when(userRepository.findUserByUsername(USERNAME)).thenReturn(Optional.of(USER_1));
    }

    @Test
    void loadUserByUsername() {
        assertEquals(USER_DETAILS, service.loadUserByUsername(USERNAME));
    }
}
