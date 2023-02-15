package example.controller;

import example.model.QuoteResponseDTO;
import example.service.QuoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/quote")
public class QuoteController {

    private QuoteService service;

    public QuoteController (QuoteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> createQuote (@RequestHeader("auth-token") String authToken,
                                               @RequestParam("text") String text) {
        return service.createQuote(text,authToken);
    }

    @PutMapping
    public ResponseEntity<String> editQuote (@RequestHeader ("auth-token") String authToken,
                                             @RequestParam("id") int id,
                                             @RequestParam("text") String text) {
        return service.updateQuote(id,text,authToken);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteQuote (@RequestHeader("auth-token") String authToken,
                                               @RequestParam("id") int id) {
        return service.deleteQuote(id,authToken);
    }

    @GetMapping ("/upvote")
    public ResponseEntity<String> upvote (@RequestHeader("auth-token") String authToken,
                                          @RequestParam("id") int id) {
        return service.upvote(id, authToken);
    }

    @GetMapping ("/dislike")
    public ResponseEntity<String> dislike (@RequestHeader ("auth-token") String authToken,
                                           @RequestParam("id") int id) {
        return service.dislike(id, authToken);
    }

    @GetMapping("/random")
    public QuoteResponseDTO getRandomQuote() {
        return service.getRandomQuote();
    }
}
