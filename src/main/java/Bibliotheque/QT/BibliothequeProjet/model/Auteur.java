package Bibliotheque.QT.BibliothequeProjet.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class Auteur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String genre;
    private Date dateNaissance;
    private Date dateDeces;
    private String image;
    private String nationalite;

    @OneToMany(mappedBy = "auteur", fetch = FetchType.EAGER)
    private Collection<Livre> livreList = new ArrayList<>();

    public Auteur(String name, String genre, Date dateNaissance, Date dateDeces, String image, String nationalite) {
        this.name = name;
        this.genre = genre;
        this.dateNaissance = dateNaissance;
        this.dateDeces = dateDeces;
        this.image = image;
        this.nationalite = nationalite;
    }

    public Auteur() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Date getDateDeces() {
        return dateDeces;
    }

    public void setDateDeces(Date dateDeces) {
        this.dateDeces = dateDeces;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public List<Long> getLivreList() {
        List<Long> idLivres = new ArrayList<>();
        livreList.forEach(livre -> idLivres.add(livre.getId()));
        return idLivres;
    }

    public void setLivreList(Collection<Livre> livreList) {
        this.livreList = livreList;
    }
    
    public boolean isSame(Auteur auteur){
        return this.name.equals(auteur.name);
    }
}
