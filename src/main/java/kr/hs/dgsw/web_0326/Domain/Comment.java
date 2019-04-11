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
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private String content;
    private String originalName;
    private String storedPath;

    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime modified;

    public Comment(Long userId, String content) {
        this.userId = userId;
        this.content = content;
    }

    public Comment(Long userId, String content, String originalName, String storedPath) {
        this.userId = userId;
        this.content = content;
        this.originalName = originalName;
        this.storedPath = storedPath;
    }

    public Comment(Comment c){
        this.id = c.getId();
        this.userId = c.getUserId();
        this.content = c.getContent();
        this.originalName = c.getOriginalName();
        this.storedPath = c.getStoredPath();
        this.created = c.getCreated();
        this.modified = c.getModified();
    }

}
