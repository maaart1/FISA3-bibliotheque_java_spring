package Bibliotheque.QT.BibliothequeProjet.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

@Entity
public class Livre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titre;
    private int nbPages;
    private String image;
    private String genre;
    private Date dateParution;
    private String resume;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Auteur auteur;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Bibliotheque bibliotheque;

    public Livre(String titre, Auteur auteur, int nbpages, String image) {
        this.titre = titre;
        this.auteur = auteur;
        this.nbPages = nbpages;
        this.image = image;
    }

    public Livre() {

    }


    public Bibliotheque getBibliotheque() {
        return bibliotheque;
    }



    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getDateParution() {
        return dateParution;
    }

    public void setDateParution(Date dateParution) {
        this.dateParution = dateParution;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getNbPages() {
        return nbPages;
    }

    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Optional<Auteur> auteur) {
        this.auteur = auteur.orElse(null);
    }

    public void setBibliotheque(Optional<Bibliotheque> bibliotheque) {
        this.bibliotheque = bibliotheque.orElse(null);
    }
}
