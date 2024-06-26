package vascopanigi.u5_w2_d3.repositories;

import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vascopanigi.u5_w2_d3.entities.Author;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {

    //controllo se l'utente esiste nel DB tramite l'email
    Optional<Author> findByEmail(String email);
}
