package Bibliotheque.QT.BibliothequeProjet.controller;

import Bibliotheque.QT.BibliothequeProjet.Hashing;
import Bibliotheque.QT.BibliothequeProjet.ObjectJsoner;
import Bibliotheque.QT.BibliothequeProjet.model.Utilisateur;
import Bibliotheque.QT.BibliothequeProjet.repository.UtilisateurRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.List;

@Path("utilisateurs")
public class UtilisateurRessource {

    @Autowired
    public UtilisateurRepository utilisateurRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Utilisateur> getAllUtilisateur(){
        return (Collection<Utilisateur>)  utilisateurRepository.findAll();
    }

    @GET
    @Path("/login/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateur getUtilisateurByLogin(@PathParam("login") String login){
        return utilisateurRepository.findUtilisateurByLogin(login);
    }

    @POST
    @Path("/verif")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject checkUserPassword(Utilisateur utilisateur) {
        Utilisateur utilisateurCheck = getUtilisateurByLogin(utilisateur.getLogin());
        if (userExist(utilisateur)) {
            String password = utilisateur.getMdp();
            String passwordCheck = Hashing.hashPassWord(password);
            return ObjectJsoner.createJsonObjectBoolean("isOk", passwordCheck.equals(utilisateurCheck.getMdp()));
        }
        return ObjectJsoner.createJsonObjectBoolean("isOk", false);
    }

    @GET
    public boolean userExist(Utilisateur utilisateur) {
        return getUtilisateurByLogin(utilisateur.getLogin()) != null;
    }

    @GET
    @Path("/getByLoginMdp/{login}{mdp}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utilisateur> getUtilisateurByLoginAndMdp(@PathParam("login") String login, @PathParam("mdp") String mdp){
        List<Utilisateur> users = utilisateurRepository.findUtilisateurByLoginAndMdp(login,mdp);
        return users;
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateur addUtilisateur(Utilisateur utilisateur){
        if(getUtilisateurByLogin(utilisateur.getLogin()) == null){
            utilisateur.setMdp(Hashing.hashPassWord(utilisateur.getMdp()));
            utilisateurRepository.save(utilisateur);
            return utilisateur;
        }
        return null;
    }

}
