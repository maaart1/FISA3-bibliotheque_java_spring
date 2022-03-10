import React, {useState,  useEffect} from "react";
import Bibliotheque from "./Model/Bibliotheque";

const BibliothequeList = () => {
    const [searchCard, setSearchCard] = useState('');
    const [data, setData] = useState([{
        name: "",
        description: "",
        image: ""
    }]);

    useEffect(() => {
        fetch('http://localhost:8080/bqt/bibliotheques')
            .then(async (response) => {
                const response_json = await response.json();
                console.log(response_json)
                setData(response_json)
            })
    }, []);

    return (
        <div className="App">
            <div className="nav-wrapper">
                <form>
                    <div className="input-field">
                        <input inputMode="text" id="search" type="search" onChange={event => {
                            setSearchCard(event.target.value)
                        }} required/>
                        <label className="label-icon" htmlFor="search"><i
                            className="material-icons">search</i></label>
                        <i className="material-icons">close</i>
                    </div>
                </form>
            </div>
            <div className="componentList">
                {data.filter(item => {
                    if (searchCard === "") {
                        return item;
                    } else if (item.name.toLowerCase().includes(searchCard.toLowerCase())) {
                        return item;
                    }
                }).map((item) => (
                    <Bibliotheque item={item}/>
                ))}
            </div>
        </div>
    )
}

export default BibliothequeList;
