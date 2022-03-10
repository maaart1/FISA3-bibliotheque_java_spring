package Bibliotheque.QT.BibliothequeProjet.controller;

import Bibliotheque.QT.BibliothequeProjet.ObjectJsoner;
import Bibliotheque.QT.BibliothequeProjet.model.Auteur;
import Bibliotheque.QT.BibliothequeProjet.model.Bibliotheque;
import Bibliotheque.QT.BibliothequeProjet.model.Livre;
import Bibliotheque.QT.BibliothequeProjet.repository.AuteurRepository;
import Bibliotheque.QT.BibliothequeProjet.repository.BibliothequeRepository;
import Bibliotheque.QT.BibliothequeProjet.repository.LivreRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Path("livres")
public class LivreRessource {

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private AuteurRepository auteurRepository;

    @Autowired
    private BibliothequeRepository bibliothequeRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Livre createLivre(Livre l) {
        List<Auteur> auteurList = (List<Auteur>) auteurRepository.findAll();
        List<Bibliotheque> bibliothequeList = (List<Bibliotheque>) bibliothequeRepository.findAll();

        livreRepository.save(l);
        if (l.getAuteur().getName() == null){
            l.setAuteur(auteurRepository.findAuteurByName("Auteur inconnu"));
            auteurRepository.deleteById(l.getAuteur().getId());
        }else {
            auteurList.forEach(auteur -> {
                if(auteur.isSame(l.getAuteur())){
                    Auteur auteurARemplacer = l.getAuteur();

                    l.setAuteur(auteurRepository.findById(auteur.getId()));
                    livreRepository.save(l);
                    auteurRepository.deleteById(auteurARemplacer.getId());
                }
            });
        }
        bibliothequeList.forEach(bibliotheque -> {
            if (bibliotheque.isSame(l.getBibliotheque())) {
                Bibliotheque bibliothequeARemplacer = l.getBibliotheque();

                l.setBibliotheque(bibliothequeRepository.findById(bibliotheque.getId()));
                livreRepository.save(l);
                bibliothequeRepository.deleteById(bibliothequeARemplacer.getId());
            }
        });
        return l;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Livre> getAllLivres() {
        return (Collection<Livre>) livreRepository.findAll();
    }

    @GET
    @Path("/auteur/{auteurId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Livre> getByAuteur(@PathParam("auteurId") Long auteurId) {
        return livreRepository.findLivreByAuteurId(auteurId);
    }

    @GET
    @Path("/titre/{titre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Livre> getByTitre(@PathParam("titre") String titre) {
        return livreRepository.findLivreByTitre(titre);
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") Long id) {
        Optional<Livre> livre = livreRepository.findById(id);
        if (livre.isPresent()) {
            livreRepository.deleteById(id);
            return Response.ok(livre.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addMultipleLivres")
    public JSONObject addMultipleAuteur(List<Livre> livreList){
        livreList.forEach(l -> {
            List<Auteur> auteurList = (List<Auteur>) auteurRepository.findAll();
            List<Bibliotheque> bibliothequeList = (List<Bibliotheque>) bibliothequeRepository.findAll();
            livreRepository.save(l);
            auteurList.forEach(auteur -> {
                if(auteur.isSame(l.getAuteur())){
                    Auteur auteurARemplacer = l.getAuteur();

                    l.setAuteur(auteurRepository.findById(auteur.getId()));
                    livreRepository.save(l);
                    auteurRepository.deleteById(auteurARemplacer.getId());
                }
            });
            bibliothequeList.forEach(bibliotheque -> {
                if(bibliotheque.isSame(l.getBibliotheque())){
                    Bibliotheque bibliothequeARemplacer = l.getBibliotheque();

                    l.setBibliotheque(bibliothequeRepository.findById(bibliotheque.getId()));
                    livreRepository.save(l);
                    bibliothequeRepository.deleteById(bibliothequeARemplacer.getId());

                }
            });
        });
        return ObjectJsoner.createJsonObjectString("message", "Data is loaded :)");
    }

    @DELETE
    @Path("/deleteAll")
    public void deleteAll(){
        livreRepository.deleteAll();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllLivresOfAnAuthor/{id}")
    public List<Livre> getAllLivresOfAnAuthor(@PathParam("id") long id){
        List<Livre> livreList = new ArrayList<>();
        if(auteurRepository.findById(id).isPresent()){
            auteurRepository.findById(id).get().getLivreList().forEach(
                    livre -> livreList.add(livreRepository.findLivreById(livre))
            );
        }
        return livreList;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllLivresOfABibliotheque/{id}")
    public List<Livre> getAllLivresOfABibliotheque(@PathParam("id") long id){
        List<Livre> livreList = new ArrayList<>();
        if(bibliothequeRepository.findById(id).isPresent()){
            bibliothequeRepository.findById(id).get().getLivres().forEach(
                    livre -> livreList.add(livreRepository.findLivreById(livre))
            );
        }
        return livreList;
    }
}
