import React, { useEffect, useState } from "react";
import { Grid, GridColumn } from "semantic-ui-react";
import { useCookies } from 'react-cookie';
import { useLocation } from "react-router-dom";
 
const Header = () => {
    const [cookies, setCookie] = useCookies(['user']);
    const [locationName,setLocationName] = useState()
    const location = useLocation()

    useEffect(()=>{
        switch (location.pathname) {
            case "/":
                setLocationName("HOME")
                break;
            case "/play":
                setLocationName("PLAY")
                break;
            case "/buy":
                setLocationName("BUY")
                break;
            case "/sell":
                setLocationName("SELL")
                break;
            default:
                break;
        }
    },[location])

    return (
        <Grid style={{borderBottom: "1px solid black",width:"100%"}}>
            <Grid.Row>
                <GridColumn floated="left">
                    {cookies.zollex}
                </GridColumn>
                <GridColumn>
                <h1>{locationName}</h1>
                </GridColumn>
                <GridColumn floated="right">
                    {cookies.name}
                </GridColumn>
            </Grid.Row>
        </Grid>
    );
};
 
export default Header;
