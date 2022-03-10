import React, {useState} from "react";
import {Link, useNavigate} from "react-router-dom";

const NavBar = (props) => {
    const navigate = useNavigate();
    const [data, setData] = useState([]);

    function handleAddAuteur() {
        fetch(`http://localhost:8080/bqt/utilisateurs/`)
            .then(async (response) => {
                const response_json = await response.json();
                delete response_json.password;
                setData(response_json);
            })
        // TODO
        data.map((user) => {
            if (localStorage.getItem("currentAdmin") === user.login) {
                navigate(`/addAuteur`);
            }else {
                navigate(`/login/-1`);
            }
        })
    }

    return (
            <div className="container">
                <ul id="slide-out" className="sidenav lighten-1 menu">
                    <li>
                        <div className="user-view">
                            <div className="background">
                                <img src="https://images.pexels.com/photos/5483071/pexels-photo-5483071.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=100&w=610" />
                            </div>
                            <a href="https://fr.linkedin.com/in/martin-thibaut-00b309198?trk=people-guest_people_search-card"><img className="circle right" src="https://media-exp1.licdn.com/dms/image/C4E03AQEw-dPYsHyYow/profile-displayphoto-shrink_400_400/0/1637922622962?e=1643241600&v=beta&t=l4030wSlE8lqMgufp3HrSekTODLz7TvJvUKoRX_KYsI" alt="Martin"/></a>
                            <a href="https://fr.linkedin.com/in/basile-thiry-6222951aa"><img className="circle" src="https://media-exp1.licdn.com/dms/image/C4D03AQFQ7962oSA1XQ/profile-displayphoto-shrink_200_200/0/1612270212947?e=1643846400&v=beta&t=4E9uzyf5SK-p4pMshMADoqJzeINIwQ-lQFNIfaEXwAk"  alt="Basile"/></a>
                            <a href="mailto:basile.thiry@etu.uphf.fr"><span className="white-text " >Basile Thiry</span></a>
                            <a href="mailto:martin.thibaut@etu.uphf.fr"><span className="white-text right " >Martin Thibaut</span></a>
                        </div>
                    </li>

                    <section className='center-text white-text '>
                        <h5>Projet Java-Spring</h5>
                    </section>
                    <div className="divider"/>
                    <li>
                        <Link className="white-text" to="/bibliotheques">
                            <i className="material-icons white-text">library_books</i>
                            Bibliothéques
                        </Link>
                    </li>
                    <li>
                        <Link className="white-text" to="/livres">
                            <i className="material-icons white-text">book</i>
                            Livres
                        </Link>
                    </li>
                    <li>
                        <Link className="white-text" to="/auteurs">
                            <i className="material-icons white-text">person</i>
                            Auteurs
                        </Link>
                    </li>
                    <div className="divider"/>
                    <li>
                        <a className="white-text" onClick={handleAddAuteur}>
                            <i className="material-icons white-text">person_add</i>
                            Ajouter un auteur
                        </a>
                    </li>
                </ul>
            </div>
    )
}

export default NavBar;
                    /*<li>
                    <section className='center-text white-text '>
                        <h6>Trier par</h6>
                    </section>
                        <a className="white-text">
                            <label>
                                <input type="checkbox" className="filled-in" onChange={(event) => {
                                    if (event.target.checked)
                                    {
                                        //window.location.pathname = "/livres/1"
                                        <Link className="white-text" to="/livres/1" />
                                    }
                                }}/>
                                <span className="white-text">Ordre alphabétique </span>
                            </label>
                        </a>
                    </li>*/

