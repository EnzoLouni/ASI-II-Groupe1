
import React from "react";
import Layout from "../component/Layout";
import ChatBox from "../component/ChatBox";
import { Container, Grid } from "semantic-ui-react";
import GameShortCard from "../component/GameShortCard";
import GameFullCard from "../component/GameFullCard";
import GameUser from "../component/GameUser";
import BoardSide from "../component/BoardSide";
 
const Play = () => {
    return (
        <Layout>
            <Container>
                <div class="ui grid">
                    <ChatBox/>
                    <div class="twelve wide column">
                        <BoardSide/>
                        <div class="row">
                            <div class="ui grid" style={{alignItems:"center"}}>
                                <div class="three wide column">
                                    <button class="huge ui primary button">
                                        End turn
                                    </button>
                                </div>
                                <div class="five wide column">
                                    <h4 class="ui horizontal divider header">
                                        VS
                                    </h4>                                                        
                                </div>
                                <div class="two wide column">
                                    <button class="huge ui primary button">
                                        Attack
                                    </button>
                                </div>
                            </div>
                        </div>
                        <BoardSide/>
                    </div> 
                </div>
            </Container>
        </Layout>
    );
};
 
export default Play;
