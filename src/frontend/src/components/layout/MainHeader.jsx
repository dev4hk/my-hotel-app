import React from "react";

const MainHeader = () => {
  return (
    <header className="header-banner">
      <div className="overlay"></div>
      <div className="animated-texts overlay-content d-flex flex-column align-items-center">
        <h1>
          Welcome to <span className="hotel-color"> LakeSide Hotel</span>
        </h1>
        <h4>Experience the Best Hospitality in Town</h4>
      </div>
    </header>
  );
};

export default MainHeader;
