import React, { useEffect, useState } from "react";
import { Grid, Image, Label } from "semantic-ui-react";
import { useCookies } from 'react-cookie';
import { Link, useLocation } from "react-router-dom";
 
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
        <Grid style={{borderBottom: "1px solid black",width:"100%"}} centered verticalAlign="middle">
            <Grid.Row>
                <Grid.Column floated="left">
                    <Label size="massive">{cookies.zollex}Ƶ</Label>
                </Grid.Column>
                <Grid.Column verticalAlign="middle">
                    <Grid>
                        <Link to="/" style={{display:"inline"}}>
                            <Image src="/image/zozzemon.jpg" size="small"/>
                        </Link>
                        <Label as="h1" style={{fontSize:"46px"}}>
                            {locationName}
                        </Label>
                    </Grid>
                </Grid.Column>
                <Grid.Column floated="right">
                    <Grid centered verticalAlign="middle">
                        <Image src="/image/user.jpeg" size="small" style={{height:"100px",objectFit:"cover"}}/>
                        <Label size="massive" style={{margin:"auto"}}>{cookies.name}</Label>
                    </Grid>
                </Grid.Column>
            </Grid.Row>
        </Grid>
    );
};
 
export default Header;
