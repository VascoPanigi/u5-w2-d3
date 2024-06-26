package vascopanigi.u5_w2_d3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "BlogPosts")
public class BlogPost {
    @Id
    @GeneratedValue
    private UUID id;
    private String category;
    private String title;
    private String cover;
    private String content;
    private int readTime;

    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;

    public BlogPost(String title, String content) {
        this.title = title;
        this.content = content;
    }
}