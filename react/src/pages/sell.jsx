import { Button, Container, Grid, Loader } from "semantic-ui-react";
import Layout from "../component/Layout";
import React, { useState } from "react";
import ZozzemonBoard from "../component/ZozzemonBoard";
import ZozzemonCard from "../component/ZozzemonCard";
import axios from "../core/axiosMockInstance";
import { useSelector } from "react-redux";
import { useCookies } from "react-cookie";
import { notifSocket } from "../socket";
import { useNavigate } from "react-router-dom";

const Sell = () => {
    const selectedZozzemon = useSelector(state => state.zozzemon.selectedZozzemon)
    const [userCookies, setUserCookies] = useCookies(['user']);
    const [isWaitingfForNotif, setIsWaitingfForNotif] = useState(false);
    const navigate = useNavigate();

    function sellCard(){
        try {
            setIsWaitingfForNotif(true)
            axios.post(process.env.REACT_APP_RPROXY+"storeapi/sell",{
                cardId: selectedZozzemon.id,
                userId: userCookies.id
            })
            notifSocket.on("notification",()=> {
                setIsWaitingfForNotif(false)
                setUserCookies('wallet',useCookies.wallet+selectedZozzemon.price)
                navigate("/sell")
            })
        } catch (error) {
            console.log(error)
        }
    }

    return (
        <Layout>
            <Loader  active={isWaitingfForNotif}>Loading</Loader>
            <Container>
                <h2 as="h2">My Zozzemons</h2>
                <Grid>
                    <Grid.Row>
                        <Grid.Column computer={10}>
                            <ZozzemonBoard variant={"sell"}/>
                        </Grid.Column>
                        {selectedZozzemon && 
                        <Grid.Column computer={6} verticalAlign="middle">
                            <Grid verticalAlign="middle" centered>
                                <ZozzemonCard/>
                                <Button onClick={sellCard} style={{width:"320px",marginTop:"32px"}}>Sell</Button>
                            </Grid>
                        </Grid.Column>
                        }
                    </Grid.Row>
                </Grid>
            </Container>
        </Layout>
    );
};
 
export default Sell;
