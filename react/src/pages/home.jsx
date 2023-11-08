
import React from "react";
import HomeTile from "../component/HomeTile";
 
const Home = () => {
    return (
        <div className="column strech">
            <HomeTile url="/buy" icon="buy" title="Buy"/>
            <HomeTile url="/sell" icon="sell" title="Sell"/>
            <HomeTile url="/play" icon="play" title="Play"/>
        </div>
    );
};
 
export default Home;
