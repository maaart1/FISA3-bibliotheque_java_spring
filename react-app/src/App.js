import './App.css';
import {BrowserRouter, Routes, Route} from 'react-router-dom';

import LivreList from "./components/LivreList";
import AuteurList from "./components/AuteurList";
import BibliothequeList from "./components/BibliothequeList";
import NavBar from "./components/NavBar";
import LivreListOfBibliotheque from "./components/LivreListOfBibliotheque";
import AddLivre from "./components/AddLivre";
import AddAuteur from "./components/AddAuteur";
import Login from "./components/Login";

function App() {
  return (

    <BrowserRouter>
        <NavBar />
        <Routes>
            <Route path="/bibliotheques" exact element={<BibliothequeList />}/>
            <Route path="/livres" exact element={<LivreList />}/>
            <Route path="/auteurs" exact element={<AuteurList />}/>
            <Route path="/bibliothequeList/:id" exact element={<LivreListOfBibliotheque />}/>
            <Route path="/addLivre/:id" exact element={<AddLivre  />}/>
            <Route path="/login/:id" exact element={<Login  />}/>
            <Route path="/addAuteur" exact element={<AddAuteur  />}/>
            <Route path="/" exact element={<BibliothequeList />}/>
        </Routes>
    </BrowserRouter>

  );
}

export default App;
