package Bibliotheque.QT.BibliothequeProjet.repository;

import Bibliotheque.QT.BibliothequeProjet.model.Auteur;
import Bibliotheque.QT.BibliothequeProjet.model.Livre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AuteurRepository extends CrudRepository<Auteur, Long> {
    public Optional<Auteur> findAuteurByName(String name);
    public Auteur deleteAuteurById(Long id);
}
