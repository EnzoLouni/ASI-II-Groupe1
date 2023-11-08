
import React from "react";
 
const HomeTile = ({url,title,icon}) => {
    return (
        <a className="ui label" href={url}>
            {icon ==="buy" && <i className= "massive shopping cart icon"/>}
            {icon ==="sell" && <i className= "massive money bill alternate icon"/>}
            {icon ==="play" && <i className= "massive gamepad icon"/>}
            <h1 className="ui label">{title}</h1>
        </a>
    );
};
 
export default HomeTile;
