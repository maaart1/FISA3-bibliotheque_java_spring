import React, {useState, useEffect} from "react";

const Select = () => {
    const [auteurList, setAuteurList] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/bqt/auteurs")
            .then(async (response) => {
                const response_json = await response.json();
                console.log(response_json);
                setAuteurList(response_json);
            })
    }, []);

    return (
        <div className="row">
            <div className="col s12">
                <select>
                    <option value="" disabled selected>Auteur</option>
                    {
                        auteurList.map((item) => {
                            console.log(item.name)
                            return <option value={item.id}>{item.name}</option>
                        })
                    }
                </select>
            </div>
        </div>
    )
}

export default Select;