import React, {useState} from "react";

const AddAuteur = (props) => {
    const [name, setName] = useState('');
    const [genre, setGenre] = useState('');
    const [image, setImage] = useState('');
    const [nationalite, setNationalite] = useState('');

    function handleSubmit(event) {
        event.preventDefault()
        const auteur = {
            name,
            genre,
            image,
            nationalite
        };

        fetch('http://localhost:8080/bqt/auteurs', {
            method: 'POST',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            body: JSON.stringify(auteur)
        }).then(() => {
            console.log(JSON.stringify(auteur));
            console.log("Auteur Added !")
        });
    }

    return (
        <div className="row">
            <form className="col s11" onSubmit={handleSubmit}>
                <h5 className="">Informations de l'auteur : </h5>
                <div className="row">
                    <div className="input-field col s6">
                        <input id="first_name" type="text" className="validate" value={name} onChange={(e) => {
                            setName(e.target.value)
                        }}/>
                        <label htmlFor="first_name">Nom de l'auteur</label>
                    </div>
                    <div className="input-field col s6">
                        <input id="last_name" type="text" className="validate" value={nationalite} onChange={(e) => {
                            setNationalite(e.target.value)
                        }}/>
                        <label htmlFor="last_name">Nationalité de l'auteur</label>
                    </div>
                </div>
                <div className="row">
                    <div className="input-field col s6">
                        <input id="first_name" type="text" className="validate" value={genre} onChange={(e) => {
                            setGenre(e.target.value)
                        }}/>
                        <label htmlFor="first_name">Genre (Homme/Femme) </label>
                    </div>
                    <div className="input-field col s6">
                        <input id="last_name" type="text" className="validate" value={image} onChange={(e) => {
                            setImage(e.target.value)
                        }}/>
                        <label htmlFor="last_name">Lien de l'image</label>
                    </div>
                </div>
                <button className="btn waves-effect waves-light color" type="submit" name="action">Ajouter
                    <i className="material-icons right">send</i>
                </button>
            </form>
        </div>
    )
}

export default AddAuteur;