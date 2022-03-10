package Bibliotheque.QT.BibliothequeProjet.controller;

import Bibliotheque.QT.BibliothequeProjet.model.Auteur;
import Bibliotheque.QT.BibliothequeProjet.repository.AuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Path("auteurs")
public class AuteurRessource {

    @Autowired
    private AuteurRepository auteurRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Auteur> getAllAuteur(){
        /*auteurRepository.findAll().forEach(auteur -> {
            if (auteur.getName().equals("null")) auteurRepository.deleteById(auteur.getId());
        });
//        auteurRepository.findAll().forEach(auteur -> auteur.getLivreList().forEach(livre -> System.out.println(livre.getTitre())));
        List<Auteur> auteurList = new ArrayList<>();
        auteurRepository.findAll().forEach(auteur -> {
            if (!auteur.getName().equals("null")) auteurList.add(auteur);
        });
        return auteurList;*/
        return (Collection<Auteur>) auteurRepository.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Optional<Auteur> getAuteurById(@PathParam("id") long id){return auteurRepository.findById(id);}

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addAuteur(Auteur a){
        auteurRepository.save(a);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addMultipleAuteur")
    public void addMultipleAuteur(List<Auteur> auteurList){
        auteurList.forEach(a -> auteurRepository.save(a));
    }

    @DELETE
    @Path("/deleteAll")
    public void deleteAll(){
        auteurRepository.deleteAll();
    }
}
