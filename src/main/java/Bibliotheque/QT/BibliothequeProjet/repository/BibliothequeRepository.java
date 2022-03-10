package Bibliotheque.QT.BibliothequeProjet.repository;

import Bibliotheque.QT.BibliothequeProjet.model.Auteur;
import Bibliotheque.QT.BibliothequeProjet.model.Bibliotheque;
import org.springframework.data.repository.CrudRepository;

public interface BibliothequeRepository extends CrudRepository<Bibliotheque, Long> {
    public Bibliotheque findBibliothequeById(Long id);
    public void deleteById(Long id);
}
