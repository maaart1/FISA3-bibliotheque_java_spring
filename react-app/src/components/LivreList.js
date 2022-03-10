import React, {useState, useEffect} from "react";
import Livre from "./Model/Livre";

const LivreList = () => {
    //const {sort} = useParams();
    const [searchCard, setSearchCard] = useState('');
    const [data, setData] = useState([{
        titre: "",
        nbPages: "",
        auteur: {}
    }]);

    useEffect(() => {
        fetch('http://localhost:8080/bqt/livres')
            .then(async (response) => {
                const response_json = await response.json();
                setData(response_json);
            })
    }, []);

    return (
        <div className="App">
                <div className="nav-wrapper">
                    <form>
                        <div className="input-field">
                            <input inputMode="text" type="search" onChange={event => {
                                setSearchCard(event.target.value)}} required />
                            <label className="label-icon" htmlFor="search"><i
                                className="material-icons">search</i></label>
                            <i className="material-icons">close</i>
                        </div>
                    </form>
                </div>
            <div className="componentList">
                {
                    data.filter(item => {
                        if (searchCard === "") {
                            return item;
                        }else if (item.titre.toLowerCase().includes(searchCard.toLowerCase())) {
                            return item;
                        }
                    })
                        //.sort((l1, l2) => l1.titre > l2.titre ? 1 : -1)
                        .map((item) => (
                            <Livre item={item}/>
                        ))
                }
            </div>
        </div>
    )
}

export default LivreList;
