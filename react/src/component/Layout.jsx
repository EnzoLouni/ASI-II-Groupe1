import React, { useEffect } from "react";
import Header from "./Header";
import { Grid } from "semantic-ui-react";
import { useNavigate } from "react-router-dom";
import { useCookies } from "react-cookie";
 
const Layout = ({ children }) => {
    const [userCookies, setUserCookies] = useCookies(['user']);
    const navigate = useNavigate();

    useEffect(()=>{
        if(!userCookies.id) navigate("/login")
    },[userCookies,navigate])

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
