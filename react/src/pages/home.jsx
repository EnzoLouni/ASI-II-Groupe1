
import React from "react";
import HomeTile from "../component/HomeTile";
import { Grid } from "semantic-ui-react";
import Layout from "../component/Layout";
 
const Home = () => {
    return (
        <Layout>
            <Grid style={{height: '100%', alignContent:"center", gap:"50px", width:"100%"}}>
                <Grid.Row columns={2} centered>
                    <Grid.Column computer={5}>
                        <HomeTile url="/buy" icon="shopping cart" title="Buy"/>
                    </Grid.Column>
                    <Grid.Column computer={5}>
                        <HomeTile url="/sell" icon="money bill alternate" title="Sell"/>
                    </Grid.Column>
                </Grid.Row>
                <Grid.Row columns={1} centered>
                    <Grid.Column computer={5}>
                        <HomeTile url="/play" icon="gamepad" title="Play"/>
                    </Grid.Column>
                </Grid.Row>
            </Grid>
        </Layout>
    );
};
 
export default Home;
