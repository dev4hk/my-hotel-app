import "./App.css";
import Home from "./components/home/Home";
import AddRoom from "./components/room/AddRoom";
import EditRoom from "./components/room/EditRoom";
import ExistingRooms from "./components/room/ExistingRooms";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import NavBar from "./layout/Navbar";
import Footer from "./layout/Footer";

function App() {
  return (
    <>
      <main>
        <Router>
          <NavBar />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/edit-room/:roomId" element={<EditRoom />} />
            <Route path="/existing-rooms" element={<ExistingRooms />} />
            <Route path="/add-room" element={<AddRoom />} />
            <Route></Route>
          </Routes>
          <Footer />
        </Router>
      </main>
    </>
  );
}

export default App;
