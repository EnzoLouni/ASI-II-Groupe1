import React, { useEffect } from "react";
import Header from "./Header";
import { Grid } from "semantic-ui-react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
 
const Layout = ({ children }) => {
    const currentUser = useSelector(state => state.user.currentUser)
    const navigate = useNavigate();

    useEffect(()=>{
        if(!currentUser.id) navigate("/login")
    },[currentUser])

    return (
        <Grid style={{height: "100vh",width:"100%"}}>
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
