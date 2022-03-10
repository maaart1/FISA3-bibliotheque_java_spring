package Bibliotheque.QT.BibliothequeProjet.controller;

import Bibliotheque.QT.BibliothequeProjet.model.Auteur;
import Bibliotheque.QT.BibliothequeProjet.model.Bibliotheque;
import Bibliotheque.QT.BibliothequeProjet.repository.BibliothequeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("bibliotheques")
public class BibliothequeRessource {
    @Autowired
    private BibliothequeRepository bibliothequeRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bibliotheque> getAllBibliotheques(){return (List<Bibliotheque>) bibliothequeRepository.findAll();}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getById/{id}")
    public Optional<Bibliotheque> getBibliothequeById(@PathParam("id") long id){return bibliothequeRepository.findById(id);}

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addBibliotheque(Bibliotheque bibliotheque){
        bibliothequeRepository.save(bibliotheque);
    }

    @DELETE
    public void deleteAllBibliotheque(){
        bibliothequeRepository.deleteAll();
    }

}
