
import React, { useEffect, useState } from "react";
import Layout from "../component/Layout";
import ChatBox from "../component/ChatBox";
import { Container, Grid } from "semantic-ui-react";
import GameBoardSide from "../component/GameBoardSide";
import { useCookies } from "react-cookie";
import { io } from "socket.io-client";
import GameEmptyBoard from "../component/GameEmptyBoard";

const Play = () => {
    const [playerSeat1, setPlayerSeat1] = useState();
    const [playerSeat2, setPlayerSeat2] = useState();
    // const [socketState, setSocketState] = useState();
    // const [userCookies, setUserCookies] = useCookies(['user']);


    // useEffect(()=>{
    //     const socket = io("localhost:3002");

    //     socket.on("connectedUser", (data) => {
    //         console.log(data)
    //         socket.emit("userConnect",userCookies.id)
    //     });

    //     socket.on("test", (data) => {
    //         console.log(data)
    //         socket.emit("userConnect",userCookies.id)   
    //     });

    //     socket.on("startGame", (data) => {
    //         console.log(data)      
    //     });

    //     socket.on("spectategame", (data) => {
    //         console.log(data)  
    //     });

    //     setSocketState(socket)
    // },[])

    return (
        <Layout>
            <Container>
                <div class="ui grid">
                    <ChatBox/>
                    <div class="twelve wide column">
                        { !playerSeat1 ? 
                        <GameBoardSide playerBoard={false} id={1} /> : 
                        <GameEmptyBoard id={1}/>
                        }
                        
                        <div class="row">
                            <div class="ui grid" style={{alignItems:"center",justifyContent:"center"}}>
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
                        { playerSeat2 ? 
                        <GameBoardSide isHero={false} boardId={2} /> : 
                        <GameEmptyBoard id={2}/>
                        }
                    </div> 
                </div>
            </Container>
        </Layout>
    );
};
 
export default Play;
