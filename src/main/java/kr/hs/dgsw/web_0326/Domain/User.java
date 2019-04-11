package kr.hs.dgsw.web_0326.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String email;
    private String password;
    private String originalName;
    private String storedPath;

    @CreationTimestamp
    private LocalDateTime joined;
    @UpdateTimestamp
    private LocalDateTime modified;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User(String username, String email, String password, String originalName, String storedPath, LocalDateTime joined, LocalDateTime modified) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.originalName = originalName;
        this.storedPath = storedPath;
        this.joined = joined;
        this.modified = modified;
    }
}
