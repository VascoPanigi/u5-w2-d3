package vascopanigi.u5_w2_d3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;
    private String avatar;

    @OneToMany(mappedBy = "author")
    private List<BlogPost> blogPostList;

    public Author(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}