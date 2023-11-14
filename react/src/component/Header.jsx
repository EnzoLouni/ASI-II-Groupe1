import React, { useEffect, useState } from "react";
import { Button, Grid, Image, Label } from "semantic-ui-react";
import { Link, useLocation } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { setCurrentUser } from "../core/reducers/userSlice";
 
const Header = () => {
    const [locationName,setLocationName] = useState()
    const location = useLocation()
    const currentUser = useSelector(state => state.user.currentUser)
    const dispatch = useDispatch();

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
        dispatch(setCurrentUser({id: null}));
    }

    return (
        <Grid style={{borderBottom: "1px solid black",width:"100%"}} centered verticalAlign="middle">
            <Grid.Row>
                <Grid.Column floated="left">
                    <Label size="massive">{currentUser.wallet}Ƶ</Label>
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
                        <Label size="massive" style={{margin:"auto"}}>{currentUser.login}</Label>
                        <Button onClick={disconnectUser} style={{height:"52px",margin:"auto"}} negative icon="user close"/>
                    </Grid>
                </Grid.Column>
            </Grid.Row>
        </Grid>
    );
};
 
export default Header;
