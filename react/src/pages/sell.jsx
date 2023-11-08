import { Container, Grid } from "semantic-ui-react";
import Layout from "../component/Layout";
import React, { useEffect, useState } from "react";
import ZozzemonBoard from "../component/ZozzemonBoard";
import axios from "axios";
const tmpZozzemons = require('./zozzemons.json');

const Sell = () => {
    const [zozzemons,setZozzemons] = useState()

    useEffect(()=>{
        async function fetchZozzemons(){
            try {
                // await axios.get(process.env.REACT_APP_RPROXY+"cardsapi")
                setZozzemons(tmpZozzemons)
            } catch (error) {
                console.log(error)
            }
        }
        fetchZozzemons()
    },[])

    useEffect(()=>{
        console.log(zozzemons)
    },[zozzemons])
    
    return (
        <Layout>
            <Container>
                <h2 as="h2">My Zozzemons</h2>
                <Grid>
                    <Grid.Column>
                        <ZozzemonBoard zozzemons={zozzemons}/>
                    </Grid.Column>
                    <Grid.Column>
                    </Grid.Column>
                </Grid>
            </Container>
        </Layout>
    );
};
 
export default Sell;
