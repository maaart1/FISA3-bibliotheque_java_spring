package Bibliotheque.QT.BibliothequeProjet.repository;

import Bibliotheque.QT.BibliothequeProjet.model.Utilisateur;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {

    public Utilisateur findUtilisateurByLogin(String login);
    public List<Utilisateur> findUtilisateurByLoginAndMdp(String login, String mdp);

}


