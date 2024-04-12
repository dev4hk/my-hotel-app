import React from "react";

const Header = ({ title }) => {
  return (
    <header
      className="header d-flex align-items-center"
      style={{ height: "500px" }}
    >
      <div className="overlay"></div>
      <div className="container d-flex justify-content-center">
        <h1
          className="header-title text-center text-white"
          style={{ zIndex: 1 }}
        >
          {title}
        </h1>
      </div>
    </header>
  );
};

export default Header;
