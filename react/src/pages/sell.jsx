import { Button, Container, Grid } from "semantic-ui-react";
import Layout from "../component/Layout";
import React from "react";
import ZozzemonBoard from "../component/ZozzemonBoard";
import ZozzemonCard from "../component/ZozzemonCard";

const Sell = () => {
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
                            </Grid>
                        </Grid.Column>
                    </Grid.Row>
                </Grid>
            </Container>
        </Layout>
    );
};
 
export default Sell;
