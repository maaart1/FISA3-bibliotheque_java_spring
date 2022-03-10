package Bibliotheque.QT.BibliothequeProjet.repository;

import Bibliotheque.QT.BibliothequeProjet.model.Livre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LivreRepository extends CrudRepository<Livre, Long> {
    List<Livre> findLivreByAuteurId(Long auteurId);
    List<Livre> findLivreByTitre(String titre);
    Livre findLivreById(long id);
}
