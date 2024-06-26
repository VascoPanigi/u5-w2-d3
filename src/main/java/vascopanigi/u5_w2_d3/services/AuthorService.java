package vascopanigi.u5_w2_d3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vascopanigi.u5_w2_d3.entities.Author;
import vascopanigi.u5_w2_d3.exceptions.InvalidRequestException;
import vascopanigi.u5_w2_d3.exceptions.NotFoundException;
import vascopanigi.u5_w2_d3.repositories.AuthorRepository;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    // Gestione della get con pagination
    public Page<Author> getAuthors(int pageNum, int pageSize, String sortBy){
        if(pageSize>50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
        return authorRepository.findAll(pageable);
    }

    //gestione della save con controllo su db tramite repo

    public Author save(Author newAuthor){
        //prima di tutto controlli sul db
        this.authorRepository.findByEmail(newAuthor.getEmail()).ifPresent(
                user -> {
                    throw new InvalidRequestException("This email address: " + newAuthor.getEmail() + " is already used. Try again");
                }
        );

        //poi si aggiungono cose
        newAuthor.setAvatar("https://ui-avatars.com/api/?name=" + newAuthor.getName() + "+" + newAuthor.getSurname());
        Random random = new Random();
        int randomNum = random.nextInt(1, 90);
        newAuthor.setBirthDate(LocalDate.now().minusYears(randomNum));

        //infine si salva
        return authorRepository.save(newAuthor);
    }

    public Author findById(UUID authorId) {
        return this.authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));
    }

    public Author findByIdAndModify(UUID authorId, Author modifiedAuthor) {
        Author found = this.findById(authorId);
        found.setName(modifiedAuthor.getName());
        found.setSurname(modifiedAuthor.getSurname());
        found.setEmail(modifiedAuthor.getEmail());
        found.setBirthDate(modifiedAuthor.getBirthDate());
        found.setAvatar("https://ui-avatars.com/api/?name=" + modifiedAuthor.getName() + "+" + modifiedAuthor.getSurname());
        return this.authorRepository.save(found);
    }

    public void findByIdAndDelete(UUID userId) {
        Author found = this.findById(userId);
        this.authorRepository.delete(found);
    }

}
