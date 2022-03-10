import React, {useState, useEffect} from "react";
import {useParams} from "react-router-dom";

const AddLivre = (props) => {

    const {id} = useParams();

    const [titre, setTitre] = useState('');
    const [nbPages, setNbPages] = useState();
    const [genre, setGenre] = useState('');
    const [image, setImage] = useState('');
    const [auteurId, setAuteurId] = useState(-1);
    const [auteur, setAuteur] = useState({
        name: "Auteur inconnu",
        genre: "Inconnu",
        image: "",
        nationalite: "Inconnu",
        dateNaissance: "",
        dateDeces: ""
    });
    const [bibliotheque, setBibliotheque] = React.useState({});
    const [auteurList, setAuteurList] = React.useState([]);

    useEffect(() => {
        fetch(`http://localhost:8080/bqt/bibliotheques/getById/${id}`)
            .then(async (response) => {
                const response_json = await response.json();
                delete response_json.livres;
                delete response_json.id;
                setBibliotheque(response_json);
            })

        fetch(`http://localhost:8080/bqt/auteurs`)
            .then(async (response) => {
                const response_json = await response.json();
                console.log(response_json);
                setAuteurList(response_json);
            })

        /*if(!window.location.hash) {
            window.location = window.location + '#loaded';
            window.location.reload();
        }*/
    }, []);

    function handleSubmit(event) {
        event.preventDefault()
        /*fetch(`http://localhost:8080/bqt/auteurs/${auteurId}`)
            .then(async (response) => {
                const response_json = await response.json();
                delete response_json.id;
                delete response_json.livreList;
                console.log(response_json)
                await setAuteur(response_json);
            })*/

        const livre = {
            titre,
            nbPages,
            genre,
            image,
            auteur: auteur,
            bibliotheque};

        fetch('http://localhost:8080/bqt/livres', {
            method: 'POST',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify(livre)
        }).then(() => {
            console.log(JSON.stringify(livre));
            console.log("Livre Added !")
        });
    }

    return (
        <div className="row">
            <form className="col s11" onSubmit={handleSubmit}>
                <h5 className="">Informations livre : </h5>
                <div className="row">
                    <div className="input-field col s6">
                        <input id="first_name" type="text" className="validate" value={titre} onChange={(e) => {
                            setTitre(e.target.value)
                        }}/>
                        <label htmlFor="first_name">Titre du livre</label>
                    </div>
                    <div className="input-field col s6">
                        <input id="last_name" type="text" className="validate" value={nbPages} onChange={(e) => {
                            setNbPages(parseInt(e.target.value))
                        }}/>
                        <label htmlFor="last_name">Nombre de pages</label>
                    </div>
                </div>
                <div className="row">
                    <div className="input-field col s6">
                        <input id="first_name" type="text" className="validate" value={genre} onChange={(e) => {
                            setGenre(e.target.value)
                        }}/>
                        <label htmlFor="first_name">Genre littéraire</label>
                    </div>
                    <div className="input-field col s6">
                        <input id="last_name" type="text" className="validate" value={image} onChange={(e) => {
                            setImage(e.target.value)
                        }}/>
                        <label htmlFor="last_name">Lien de l'image</label>
                    </div>
                </div>
                {
                    auteurList.map((item) => (
                        <p>
                            <label>
                                <input name="group1" type="radio" value={auteurId} onChange={(e) => {
                                    delete item.id
                                    delete item.livreList
                                    setAuteur(item)
                                    console.log(item)
                                }}/>
                                <span>{item.name}</span>
                            </label>
                        </p>
                    ))
                }

                <button className="btn waves-effect waves-light color" type="submit" name="action">Ajouter
                    <i className="material-icons right">send</i>
                </button>
            </form>
        </div>
    )
}
                /*<h5 className="">Informations de l'auteur : </h5>
                <div className="row">
                    <div className="input-field col s12">
                        <select>
                            <option value="" disabled selected>Auteur</option>
                        </select>
                    </div>
                </div>
                {<AuteurList />}
                <h6>Écrire le nom de l'auteur ou créer le !</h6>
                <div className="row">
                    <div className="input-field col s6">
                        <input id="first_name" type="text" className="validate" value={nomAuteur} onChange={(e) => {
                            setNomAuteur(e.target.value)
                        }}/>
                        <label htmlFor="first_name">Nom de l'auteur</label>
                    </div>
                    <div className="input-field col s6">
                        <input id="last_name" type="text" className="validate" value={nationalite} onChange={(e) => {
                            setNationalite(e.target.value)
                        }}/>
                        <label htmlFor="last_name">Nationalité</label>
                    </div>
                </div>
                <div className="row">
                    <div className="input-field col s6">
                        <input id="first_name" type="text" className="validate" value={genreAuteur} onChange={(e) => {
                            setGenreAuteur(e.target.value)
                        }}/>
                        <label htmlFor="first_name">Genre littéraire</label>
                    </div>
                    <div className="input-field col s6">
                        <input id="last_name" type="text" className="validate" value={imageAuteur} onChange={(e) => {
                            setImageAuteur(e.target.value)
                        }}/>
                        <label htmlFor="last_name">Lien de l'image</label>
                    </div>
                </div>*/


export default AddLivre;