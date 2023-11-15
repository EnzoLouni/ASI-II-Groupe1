import React, { useEffect, useState } from "react";
import { Dropdown, Grid, Icon } from "semantic-ui-react";
import { io } from "socket.io-client";
import { socket } from "../socket";
import { useCookies } from "react-cookie";
 
const ChatBox = () => {
    const [chatUsers,setChatUsers] = useState([])
    const [messageList,setMessageList] = useState([])
    const [userCookies, setUserCookies] = useCookies(['user']);
    const [socketState, setSocketState] = useState();
    const [socketRoomName, setSocketRoomName] = useState();

    useEffect(()=>{
        const socket = io("localhost:3001");

        socket.on("connect", () => {
            console.log("socket.id");
            socket.emit("getUsers");    
        });

        socket.on("users", (users) => {
            console.log(users);
            setChatUsers([...users])
        });

        socket.on("room name", (roomName) => {
            console.log(roomName)
            setSocketRoomName(roomName)
        });

        socket.on('chat message', (msg) => {
            console.log("recieved",messageList,msg)
            setMessageList(messageList => [...messageList,msg]);
        })

        setSocketState(socket)
    },[])

    function updateTargetuser(e,data){
        console.log("update")
        socketState.emit("join",userCookies.id,data.value )
    }

    function sendMessage(){
        console.log("send",socketRoomName)
        const msg = {uid:userCookies.id,date:Date.now(),message:document.querySelector("#chatBoxInput").value,login:userCookies.login}
        socketState.emit("chat message",msg,socketRoomName )
        setMessageList([...messageList,msg])
    }



    return (
        <div class="four wide column">
            <div class="ui segment">
                <div class="ui five column grid">
                    <div class="column" style={{width:"100%"}}>
                        <div class="ui segment">
                            <div class="ui top attached label" style={{position:"relative"}}>
                                <Grid verticalAlign="middle">
                                    <Grid.Column computer={8}>
                                        <h2>Chat</h2>
                                    </Grid.Column>
                                    <Grid.Column computer={8} textAlign="right">
                                        <p>{userCookies.login}</p>
                                        <Icon size="big" name="user circle icon"/>
                                    </Grid.Column>
                                </Grid>
                            </div>
                        </div>
                        <Dropdown placeholder='User' search selection options={chatUsers.map(cu=>({key:cu.id,text:cu.login,value:cu.id}))} onChange={updateTargetuser}/>
                        <div class="ui segment" style={{maxHeight:"300px",minHeight:"300px",overflowY:"scroll"}}>
                            {messageList.map((msg =>(
                                <div class="ui raised segment" key={msg.date}>
                                    <a class={`ui ${msg.uid === userCookies.id ? "green" : "blue" } ribbon label`}>{msg.login}</a>
                                    <span style={{fontStyle:"italic",fontSize:"10px"}}> {new Date(msg.date).toUTCString()}</span>
                                <p style={{fontWeight:"bold"}}>{msg.message}</p>
                            </div>
                            )))}
                        </div>
                        <div class="ui form">
                            <div class="field">
                                {/* <label>Short Text</label> */}
                                <textarea id="chatBoxInput" rows="2"></textarea>
                            </div>
                        </div>
                        <button class="fluid ui right labeled icon button" onClick={sendMessage}>
                            <i class="right arrow icon"></i>
                            Send
                        </button>
                    </div>
                </div>
            </div>
        </div>
    )
};
 
export default ChatBox