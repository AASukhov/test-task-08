package example.test;

import com.google.gson.Gson;
import example.controller.QuoteController;
import example.controller.QuoteListController;
import example.controller.UserController;
import example.model.AuthRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    MockMvc mockMvc;

    private static final String WRONG_USERNAME = "wrongUser";
    private static final String RIGHT_USERNAME = "alex";
    private static final String PASSWORD = "0000";
    private static final String ENDPOINT_LOGIN = "/login";
    private static final String ENDPOINT_REGISTER = "/register";
    private static AuthRequestDTO VALID_REQUEST;
    private static AuthRequestDTO INVALID_REQUEST;
    private static final Gson gson = new Gson();

    @BeforeAll
    public static void beforeAll() {
        VALID_REQUEST = new AuthRequestDTO(RIGHT_USERNAME, PASSWORD);
       INVALID_REQUEST = new AuthRequestDTO(WRONG_USERNAME, PASSWORD);
    }

    @Test
    void testLoginSuccess() throws Exception {
        mockMvc.perform(post(ENDPOINT_LOGIN).contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(VALID_REQUEST))).andExpect(status().isOk());
    }

    @Test
    void testLoginFail() throws Exception {
        mockMvc.perform(post(ENDPOINT_LOGIN).contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(INVALID_REQUEST))).andExpect(status().isUnauthorized());
    }

    @Test
    void testRegistrationSuccess() throws Exception {
        mockMvc.perform(post(ENDPOINT_REGISTER).contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(INVALID_REQUEST))).andExpect(status().isOk());
    }

    @Test
    void QuoteControllerTest() {
        ServletContext servletContext = webApplicationContext.getServletContext();
        Assertions.assertNotNull(servletContext);
        Assertions.assertNotNull(webApplicationContext.getBean(QuoteController.class));
    }

    @Test
    void QuoteListControllerTest() {
        ServletContext servletContext = webApplicationContext.getServletContext();
        Assertions.assertNotNull(servletContext);
        Assertions.assertNotNull(webApplicationContext.getBean(QuoteListController.class));
    }

    @Test
    void UserControllerTest() {
        ServletContext servletContext = webApplicationContext.getServletContext();
        Assertions.assertNotNull(servletContext);
        Assertions.assertNotNull(webApplicationContext.getBean(UserController.class));
    }

}
