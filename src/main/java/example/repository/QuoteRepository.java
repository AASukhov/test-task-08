package example.repository;

import example.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Integer> {

    List<Quote> findAllByOrderByVotesDesc();
    List<Quote> findAllByUsername(String username);
    Quote findById(int id);

    @Modifying
    @Query("update Quote q set q.text = :text where q.id = :id")
    void update (@Param("id") int id, @Param("text") String text);

    @Modifying
    @Query("update Quote q set q.votes = (q.votes + 1) where q.id = :id")
    void upvote (@Param ("id") int id);

    @Modifying
    @Query("update Quote q set q.votes = (q.votes - 1) where q.id = :id")
    void dislike (@Param ("id") int id);

}
