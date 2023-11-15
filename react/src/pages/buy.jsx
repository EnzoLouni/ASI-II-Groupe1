import { Button, Container, Grid } from "semantic-ui-react";
import Layout from "../component/Layout";
import React from "react";
import ZozzemonBoard from "../component/ZozzemonBoard";
import ZozzemonCard from "../component/ZozzemonCard";
import axios from "../core/axiosMockInstance";
import { useSelector } from "react-redux";
import { useCookies } from "react-cookie";

const Buy = () => {
    const selectedZozzemon = useSelector(state => state.zozzemon.selectedZozzemon)
    const [userCookies, setUserCookies] = useCookies(['user']);
    
    async function buyCard(){
        try {
            await axios.post(process.env.REACT_APP_RPROXY+"storeapi",{
                cardId: selectedZozzemon.id,
                userId: userCookies.id
            })
        } catch (error) {
            console.log(error)
        }
    }

    return (
        <Layout>
            <Container>
                <h2 as="h2">Store's Zozzemons</h2>
                <Grid>
                    <Grid.Row>
                        <Grid.Column computer={10}>
                            <ZozzemonBoard/>
                        </Grid.Column>
                        {selectedZozzemon && 
                        <Grid.Column computer={6} verticalAlign="middle">
                            <Grid verticalAlign="middle" centered>
                                <ZozzemonCard/>
                                <Button onClick={buyCard} style={{width:"320px",marginTop:"32px"}}>Buy</Button>
                            </Grid>
                        </Grid.Column>
                        }
                    </Grid.Row>
                </Grid>
            </Container>
        </Layout>
    );
};
 
export default Buy;
