import React, { useEffect, useState } from "react";
import { Button, Grid, Image, Label } from "semantic-ui-react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { useCookies } from "react-cookie";
 
const Header = () => {
    const [locationName,setLocationName] = useState()
    const location = useLocation()
    const [userCookies, setUserCookies] = useCookies(['user']);
    const navigate = useNavigate();

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

    function disconnectUser(){
        setUserCookies('id',null)
        navigate("/login")
    }

    return (
        <Grid style={{borderBottom: "1px solid black",width:"100%"}} centered verticalAlign="middle">
            <Grid.Row>
                <Grid.Column floated="left">
                    <Label size="massive">{userCookies.wallet}Ƶ</Label>
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
                        <Label size="massive" style={{margin:"auto"}}>{userCookies.login}</Label>
                        <Button onClick={disconnectUser} style={{height:"52px",margin:"auto"}} negative icon="user close"/>
                    </Grid>
                </Grid.Column>
            </Grid.Row>
        </Grid>
    );
};
 
export default Header;
