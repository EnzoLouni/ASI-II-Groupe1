import { Button, Container, Grid } from "semantic-ui-react";
import Layout from "../component/Layout";
import React from "react";
import ZozzemonBoard from "../component/ZozzemonBoard";
import ZozzemonCard from "../component/ZozzemonCard";
import axios from "axios";
import { useSelector } from "react-redux";

const Sell = () => {
    const selectedZozzemon = useSelector(state => state.zozzemon.selectedZozzemon)
    const currentUser = useSelector(state => state.user.currentUser)
    
    async function sellCard(e){
        await axios.post(process.env.REACT_APP_RPROXY+"storeapi",{
            cardId: selectedZozzemon,
            userId: currentUser.id
        })
    }

    return (
        <Layout>
            <Container>
                <h2 as="h2">My Zozzemons</h2>
                <Grid>
                    <Grid.Row>
                        <Grid.Column computer={10}>
                            <ZozzemonBoard/>
                        </Grid.Column>
                        <Grid.Column computer={6} verticalAlign="middle">
                            <Grid verticalAlign="middle" centered>
                                <ZozzemonCard/>
                                <Button onClick={sellCard} style={{width:"320px",marginTop:"32px"}}>Sell</Button>
                            </Grid>
                        </Grid.Column>
                    </Grid.Row>
                </Grid>
            </Container>
        </Layout>
    );
};
 
export default Sell;
