
import React from "react";
import { Link } from "react-router-dom";
import { Grid, Header, Icon } from "semantic-ui-react";
 
const HomeTile = ({url,title,icon}) => {
    return (
        <Grid as={Link} className="ui label" to={url} style={{height: "200px",alignContent:"center"}} centered verticalAlign="middle">
            <Header as='h2' size="huge" style={{fontSize:"50px"}}>
                <Icon size="huge" style={{marginRight:"32px"}} name={icon}/>
                {title}
            </Header>
        </Grid>
    );
};
 
export default HomeTile;
