import React from "react";
import { BrowserRouter, Routes,Route} from "react-router-dom";
import HomePage from "../pages/HomePage"
import SideBar from "../components/SideBar";
import UpdateCustomer from "../pages/UpdateCustomer";
import FindApplication from "../pages/FindApplication";


function App() {
  return (
    <BrowserRouter>
      <SideBar></SideBar>
        <Routes>
          <Route path="/" element={<HomePage/>} />
          <Route path="/update-customer" element={<UpdateCustomer/>} />
          <Route path="/find-application" element={<FindApplication/>}/>
        </Routes>
    </BrowserRouter>
  );
}

export default App;
