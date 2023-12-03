
import React, { useEffect, useState } from "react";
import { useCookies } from "react-cookie";
import { Link } from "react-router-dom";
import { Button, Container, Dropdown, Grid, Header, Icon } from "semantic-ui-react";
import axios from "../core/axiosMockInstance";
 
const GameEmptyBoard = ({id}) => {
    const [userCookies, setUserCookies] = useCookies(['user']);
    const [zozzemonList, setZozzemonList] = useState([]);
    const [selecedZozzemon1, setSelecedZozzemon1] = useState();
    const [selecedZozzemon2, setSelecedZozzemon2] = useState();
    const [selecedZozzemon3, setSelecedZozzemon3] = useState();
    const [selecedZozzemon4, setSelecedZozzemon4] = useState();
    const [showModal, setShowModal] = useState(false);

    useEffect(()=>{
        async function fetchZozzemons(){
            try {
                setZozzemonList(await axios.get(process.env.REACT_APP_RPROXY+"cardapi/public/cards/"+userCookies.id+"/user"))
            } catch (error) {
                console.log(error)
            }
        }
        fetchZozzemons()
        console.log(zozzemonList)
    },[])

    function updateZozzemon1(e,data){
        setSelecedZozzemon1(data.value)
    }

    function updateZozzemon2(e,data){
        setSelecedZozzemon2(data.value)
    }

    function updateZozzemon3(e,data){
        setSelecedZozzemon3(data.value)
    }

    function updateZozzemon4(e,data){
        setSelecedZozzemon4(data.value)
    }

    const handleShow = event => {
        setShowModal(current => !current);
      };
    

    return (
        <Container>
            <Button class="ui button" style={{display:!showModal?"block":"none",height:"300px", width:"100%",fontSize:"100px"}} onClick={handleShow}>Join arena</Button>
            <Grid style={{display:showModal?"block":"none",position: "relative",backgroundColor: "white",height:"300px", width:"100%",zIndex: "10"}}>
                <Header as="h1">Pick your zozzemon</Header>
                <Grid.Column>
                    <Grid.Row>
                        <Dropdown placeholder='select zozzemon' search selection options={zozzemonList.map(zozzemon=>({key:zozzemon.id,text:zozzemon.name,value:zozzemon.id}))} onChange={updateZozzemon1}/>
                    </Grid.Row>
                    <Grid.Row>
                        <Dropdown placeholder='select zozzemon' search selection options={zozzemonList.map(zozzemon=>({key:zozzemon.id,text:zozzemon.name,value:zozzemon.id}))} onChange={updateZozzemon2}/>
                    </Grid.Row>
                </Grid.Column>
                <Grid.Column>
                    <Grid.Row>
                        <Dropdown placeholder='select zozzemon' search selection options={zozzemonList.map(zozzemon=>({key:zozzemon.id,text:zozzemon.name,value:zozzemon.id}))} onChange={updateZozzemon3}/>
                    </Grid.Row>
                    <Grid.Row>
                        <Dropdown placeholder='select zozzemon' search selection options={zozzemonList.map(zozzemon=>({key:zozzemon.id,text:zozzemon.name,value:zozzemon.id}))} onChange={updateZozzemon4}/>
                    </Grid.Row>
                </Grid.Column>
                <Grid.Row>
                    <Button class="ui button">Submit</Button>
                    <Button class="ui button" onClick={handleShow}>Cancel</Button>
                </Grid.Row>
            </Grid>
        </Container>
    );
};
 
export default GameEmptyBoard;
