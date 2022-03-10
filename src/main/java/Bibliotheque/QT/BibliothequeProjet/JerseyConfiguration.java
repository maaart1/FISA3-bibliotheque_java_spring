package Bibliotheque.QT.BibliothequeProjet;


import Bibliotheque.QT.BibliothequeProjet.controller.AuteurRessource;
import Bibliotheque.QT.BibliothequeProjet.controller.BibliothequeRessource;
import Bibliotheque.QT.BibliothequeProjet.controller.LivreRessource;
import Bibliotheque.QT.BibliothequeProjet.controller.UtilisateurRessource;
import Bibliotheque.QT.BibliothequeProjet.model.Utilisateur;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("bqt")
@Configuration
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {

        register(AuteurRessource.class);
        register(LivreRessource.class);
        register(BibliothequeRessource.class);
        register(UtilisateurRessource.class);
        register(CorsFilter.class);
    }

}