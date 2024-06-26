package vascopanigi.u5_w2_d3.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class BlogPostPayload {
    //bisogna descrivere una classe con tutti gli attributi che vogliamo avere nel payload
    private String category;
    private String title;
    private String cover;
    private String content;
    private int readTime;
    private UUID authorId;
}
