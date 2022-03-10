import * as React from "react";
import {Link} from "react-router-dom";

const Auteur = (props) => {

    return (
            <div className="componentThumbnail" >
                <div className="card blue-grey lighten-1 waves-effect waves-block waves-light">
                    <div className="card-image componentImage">
                        <img className="responsive-img" src={props.item.image} alt="Bibliothéque image" />
                    </div>
                    <div className="card-content content-card white-text">
                        <span className="card-title">{props.item.name}</span>
                        <p><u><em>Genre :</em></u> {props.item.genre}</p>
                        <p><u><em>Nationalité :</em></u> {props.item.nationalite}</p>
                    </div>
                </div>
            </div>
    )
}

export default Auteur;