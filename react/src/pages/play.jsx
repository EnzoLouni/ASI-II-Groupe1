
import React from "react";
import Layout from "../component/Layout";
import ChatBox from "../component/ChatBox";
import { Container, Grid } from "semantic-ui-react";
 
const Play = () => {
    return (
        <Layout>
            <Container>
                <Grid style={{width:"100%"}}>
                    <Grid.Column computer={4}>
                        <ChatBox/>
                    </Grid.Column>
                    <Grid.Column computer={12}>

                    </Grid.Column>
                </Grid>
            </Container>
        </Layout>
    );
};
 
export default Play;
