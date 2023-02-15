package example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "quotes")
public class Quote {

    @Id
    int id;

    String username;

    String text;

    long votes;

    public Quote (int id, String username, String text) {
        this.id = id;
        this.username = username;
        this.text = text;
    }

    public Quote (int id, String username, String text, long votes) {
        this.id = id;
        this.username = username;
        this.text = text;
        this.votes = votes;
    }

    public Quote() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }
}
