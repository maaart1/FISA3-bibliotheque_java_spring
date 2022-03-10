import React, {useState} from "react";
import {useParams, useNavigate} from "react-router-dom";

const Login = (props) => {
    const {id} = useParams();
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    function handleSubmitAdmin(event) {
        event.preventDefault()
        /*
        event.preventDefault();
        var flag = false;
        data.map((user) => {
            if (user.login === login && user.mdp === password) {
                localStorage.setItem("currentAdmin", login);
                if (id == -1) window.location.href = `/addAuteur`;
                else window.location.href = `/addLivre/${id}`;
                flag = true;
            }

        })
        if (!flag) window.location.href = `/login/${id}`;
        */
        const user = {
            login,
            mdp: password
        };

             fetch('http://localhost:8080/bqt/utilisateurs/verif', {
                method: 'POST',
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(user)
            }).then(async (response) => {
                 const body = await response.json();
                 if (body.isOk) {
                     localStorage.setItem("currentAdmin", login);
                     if (id == -1) {
                         navigate("/addAuteur");
                         return
                     }
                     else {
                         navigate(`/addLivre/${id}`)
                         return
                     }
                 }
                 navigate(`/login/${id}`)
             }).catch((err)=> {
                 console.error(err)
             })

    }

    /*React.useEffect(() => {
        fetch(`http://localhost:8080/bqt/utilisateurs/login/${localStorage.getItem("currentAdmin")}`)
            .then(response => response.json())
            .then(response_json => {
                console.log(response_json)
                setData(response_json)
            })
    }, []);*/

    return (
        <div className="row center-text">
            <form className="col s11" onSubmit={handleSubmitAdmin}>
                <h5 className="">Authentification admin : </h5>
                <div className="row">
                    <div className="input-field col s6 offset-s3">
                        <input id="first_name" type="text" className="validate" value={login} onChange={(e) => {
                            setLogin(e.target.value);
                        }}/>
                        <label htmlFor="first_name">Login</label>
                    </div>
                </div>
                <div className="row">
                    <div className="input-field col s6 offset-s3">
                        <input id="last_name" type="password" className="validate" value={password}  onChange={(e) => {
                            setPassword(e.target.value);
                        }}/>
                        <label htmlFor="last_name">Password</label>
                    </div>
                </div>
                <button className="btn waves-effect waves-light color" type="submit">Ajouter
                    <i className="material-icons right">send</i>
                </button>
            </form>
        </div>
    )
}

export default Login;