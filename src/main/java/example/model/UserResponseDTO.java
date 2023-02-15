package example.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserResponseDTO {

    private int id;
    private String username;
    private String email;
    private Date creationDate;

    public UserResponseDTO (int id, String username, String email, Date creationDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.creationDate = creationDate;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
