package example.service;

import example.entity.Quote;
import example.entity.User;
import example.exceptions.QuoteException;
import example.exceptions.UnauthorizedUserException;
import example.model.QuoteResponseDTO;
import example.repository.QuoteRepository;
import example.repository.UserRepository;
import example.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuoteService {

    private QuoteRepository quoteRepository;
    private UserRepository userRepository;
    private TokenGenerator generator;

    public QuoteService (QuoteRepository quoteRepository,
                         UserRepository userRepository,
                         TokenGenerator generator) {
        this.quoteRepository = quoteRepository;
        this.userRepository = userRepository;
        this.generator = generator;
    }

    public ResponseEntity<String> createQuote (String text, String authToken) {
        User user = getUser(authToken);
        if (user == null) {
            throw new UnauthorizedUserException("Unauthorized user");
        }
        int id = (int) Math.round(100000 * Math.random());
        long vote = 0;
        Quote quote = new Quote(id, user.getUsername(),text, vote);
        quoteRepository.save(quote);
        return new ResponseEntity<>("New quote has been successfully created", HttpStatus.OK);
    }

    public ResponseEntity<String> updateQuote (int id, String text, String authToken) {
        User user = getUser(authToken);
        if (user == null) {
            throw new UnauthorizedUserException("Unauthorized user");
        }
        Quote quote = quoteRepository.findById(id);
        if (quote != null) {
            if (quote.getUsername().equals(user.getUsername())) {
                quoteRepository.update(id,text);
            } else {
                throw new QuoteException("Quote doesn't belongs to you");
            }
        } else {
            throw new QuoteException("Quote with chosen id didn't found");
        }
        return new ResponseEntity<>("Quote with id " + id + " was changed successfully", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteQuote (int id, String authToken) {
        User user = getUser(authToken);
        if (user == null) {
            throw new UnauthorizedUserException("Unauthorized user");
        }
        Quote quote = quoteRepository.findById(id);
        if (quote != null) {
            if (quote.getUsername().equals(user.getUsername())) {
                quoteRepository.deleteById(id);
            } else {
                throw new QuoteException("Quote doesn't belongs to you");
            }
        } else {
            throw new QuoteException("Quote with chosen id didn't found");
        }
        return new ResponseEntity<>("Quote with id " + id + " has been deleted", HttpStatus.OK);
    }

    public ResponseEntity<String> upvote (int id, String authToken) {
        User user = getUser(authToken);
        if (user == null) {
            throw new UnauthorizedUserException("Unauthorized user");
        }
        Quote quote = quoteRepository.findById(id);
        if (quote != null) {
            quoteRepository.upvote(id);
        } else {
            throw new QuoteException("Quote with chosen id didn't found");
        }
        return new ResponseEntity<>("You upvoted quote with id " + id, HttpStatus.OK);
    }

    public ResponseEntity<String> dislike (int id, String authToken) {
        User user = getUser(authToken);
        if (user == null) {
            throw new UnauthorizedUserException("Unauthorized user");
        }
        Quote quote = quoteRepository.findById(id);
        if (quote != null) {
            quoteRepository.dislike(id);
        } else {
            throw new QuoteException("Quote with chosen id didn't found");
        }
        return new ResponseEntity<>("You disliked quote with id " + id, HttpStatus.OK);
    }

    public List<QuoteResponseDTO> gettingAllQuotes () {
        return quoteRepository.findAllByOrderByVotesDesc().stream().limit(10)
                .map(q -> new QuoteResponseDTO(q.getId(),q.getUsername(),q.getText(), q.getVotes()))
                .collect(Collectors.toList());
    }

    public QuoteResponseDTO getRandomQuote (){
        List<QuoteResponseDTO> list = gettingAllQuotes();
        Random random = new SecureRandom();
        QuoteResponseDTO result = list.get(random.nextInt(list.size()));
        return result;
    }

    public List<QuoteResponseDTO> gettingUsersQuotes(String authToken) {
        User user = getUser(authToken);
        if (user == null) {
            throw new UnauthorizedUserException("Unauthorized user");
        }
        return quoteRepository.findAllByUsername(user.getUsername()).stream()
                .map(q -> new QuoteResponseDTO(q.getId(),q.getUsername(),q.getText(),q.getVotes()))
                .collect(Collectors.toList());
    }

    private User getUser(String authToken) {
        if (authToken.startsWith("Bearer ")) {
            authToken = authToken.substring(7);
        }
        final String username = generator.getUsernameFromToken(authToken);
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UnauthorizedUserException("Unauthorized user"));
    }
}
