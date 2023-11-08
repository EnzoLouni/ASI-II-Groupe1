import { Container, Grid } from "semantic-ui-react";
import Layout from "../component/Layout";
import React, { useEffect, useState } from "react";
import ZozzemonBoard from "../component/ZozzemonBoard";
import axios from "axios";

const Sell = () => {
    return (
        <Layout>
            <Container>
                <h2 as="h2">My Zozzemons</h2>
                <Grid>
                    <Grid.Column>
                        <ZozzemonBoard/>
                    </Grid.Column>
                    <Grid.Column>
                    </Grid.Column>
                </Grid>
            </Container>
        </Layout>
    );
};
 
export default Sell;
