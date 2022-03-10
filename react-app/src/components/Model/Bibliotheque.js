import React, {useState, useEffect} from "react";
import {Link , useNavigate} from "react-router-dom";

const Bibliotheque = (props) => {
    const [data, setData] = useState([])
    const navigate = useNavigate();

    // TODO
    function handleAddLivre() {
        fetch(`http://localhost:8080/bqt/utilisateurs/`)
            .then(async (response) => {
                const response_json = await response.json();
                delete response_json.password;
                setData(response_json);
            })
        // TODO
        data.map((user) => {
            if (localStorage.getItem("currentAdmin") === user.login) {
                navigate(`/addLivre/${props.item.id}`);
            }else {
                navigate(`/login/${props.item.id}`);
            }
        })
    }

    return (
        <div className="componentThumbnail " >
            <div className="card blue-grey lighten-1 waves-effect waves-block waves-light content-card">
                <div className="card-image componentImage">
                    <img className="responsive-img" src={props.item.image} alt="Bibliothéque image" />
                </div>
                <div className="card-content content-card white-text">
                    <span><h6>{props.item.name}</h6></span>
                    <p><u><em>Horaires d'ouverture :</em></u> {props.item.description}</p>
                </div>
                <div className="card-action color">
                    <a className="color" >
                        <Link className="color text-action-card" to={`/bibliothequeList/${props.item.id}`}>
                            Voir
                        </Link>
                    </a>
                    <a className="right text-action-card color" onClick={handleAddLivre}>Ajouter livre</a>
                </div>
            </div>
        </div>
    )
}

export default Bibliotheque;