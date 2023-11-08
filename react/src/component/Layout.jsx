import React from "react";
import Header from "./Header";
import { Grid } from "semantic-ui-react";
 
const Layout = ({ children }) => {
    return (
        <Grid style={{height: "100vh"}}>
            <Grid.Row style={{height: "150px"}}>
                <Header />
            </Grid.Row>
            <Grid.Row style={{height: "calc(100% - 150px)"}}>
                { children }
            </Grid.Row>
        </Grid>
    );
};
 
export default Layout;
