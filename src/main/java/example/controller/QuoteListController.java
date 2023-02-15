package example.controller;

import example.model.QuoteResponseDTO;
import example.service.QuoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/list")
public class QuoteListController {

    private QuoteService service;

    public QuoteListController (QuoteService service) {
        this.service = service;
    }

    @GetMapping()
    public List<QuoteResponseDTO> gettingAllQuotes() {
        return service.gettingAllQuotes();
    }

    @GetMapping("/personal")
    public List<QuoteResponseDTO> gettingUsersQuotes(@RequestHeader ("auth-token") String authToken) {
        return service.gettingUsersQuotes(authToken);
    }
}
