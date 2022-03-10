package Bibliotheque.QT.BibliothequeProjet.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bibliotheque implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String image;

    @OneToMany(mappedBy = "bibliotheque", fetch = FetchType.EAGER)
    private List<Livre> livres = new ArrayList<>();

    public Bibliotheque(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Bibliotheque() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Long> getLivres() {
        List<Long> idLivres = new ArrayList<>();
        livres.forEach(livre -> idLivres.add(livre.getId()));
        return idLivres;
//        return livres;
    }

    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }

    public boolean isSame(Bibliotheque bibliotheque){
        return this.name.equals(bibliotheque.name);
    }
}
