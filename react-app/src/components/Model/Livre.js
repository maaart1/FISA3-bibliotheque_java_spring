import * as React from "react";

const Livre = (props) => {

    return (
        <div className="componentThumbnail" >
            <div className="card blue-grey lighten-1 waves-effect waves-block waves-light">
                <div className="card-image componentImage">
                    <img className="responsive-img" src={props.item.image} alt="Bibliothéque image" />
                </div>
                <div className="card-content content-card white-text">
                    <span className="card-title">{props.item.titre}</span>
                    <p><u><em>Nombres de pages :</em></u> {props.item.nbPages}</p>
                    <p><u><em>Auteur :</em></u> {props.item.auteur.name}</p>
                </div>
            </div>
        </div>

    )
}

export default Livre;