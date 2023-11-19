
import React, { useEffect } from "react";
import { Table } from "semantic-ui-react";
import { useSelector, useDispatch } from 'react-redux';
import { setSelectedZozzemon, setZozzemonList } from "../core/reducers/zozzemonsSlice";
import axios from "../core/axiosMockInstance";
import { useCookies } from "react-cookie";

const ZozzemonBoard = ({variant}) => {
    const zozzemonList = useSelector(state => state.zozzemon.zozzemonList)
    const [userCookies, setUserCookies] = useCookies(['user']);
    const dispatch = useDispatch();
    let zozzemonEndPoint
    switch (variant) {
        case "buy":
            zozzemonEndPoint = "cardapi/public/cards_to_sell"
            break;
    
        case "sell":
            zozzemonEndPoint = "cardapi/public/cards/"+userCookies.id+"/user"
            break;

        default:
            zozzemonEndPoint = "cardapi/public/cards"
            break;
    }

    useEffect(()=>{
        dispatch(setSelectedZozzemon(null))
        async function fetchZozzemons(){
            try {
                const zozzemonListPr = await axios.get(process.env.REACT_APP_RPROXY+zozzemonEndPoint)
                dispatch(setZozzemonList(zozzemonListPr.data));
            } catch (error) {
                console.log(error)
            }
        }
        fetchZozzemons()
    },[dispatch])

    return (
        <Table celled style={{display:"block",maxHeight:"74vh",overflowY:"scroll"}}>
            <Table.Header>
                <Table.Row>
                    <Table.HeaderCell>Name</Table.HeaderCell>
                    <Table.HeaderCell>Description</Table.HeaderCell>
                    <Table.HeaderCell>Family</Table.HeaderCell>
                    <Table.HeaderCell>Affinity</Table.HeaderCell>
                    <Table.HeaderCell>Energy</Table.HeaderCell>
                    <Table.HeaderCell>HP</Table.HeaderCell>
                    <Table.HeaderCell>Attack</Table.HeaderCell>
                    <Table.HeaderCell>Defense</Table.HeaderCell>
                    <Table.HeaderCell>Price</Table.HeaderCell>
                </Table.Row>
            </Table.Header>

            <Table.Body>
                { zozzemonList &&
                    zozzemonList.map((z => (
                        <Table.Row key={z.id} className="zozzBoardRow" onClick={()=> dispatch(setSelectedZozzemon(z.id))}>
                            <Table.Cell>{z.name}</Table.Cell>
                            <Table.Cell>{z.description}</Table.Cell>
                            <Table.Cell>{z.family}</Table.Cell>
                            <Table.Cell>{z.affinity}</Table.Cell>
                            <Table.Cell>{z.energy}</Table.Cell>
                            <Table.Cell>{z.hp}</Table.Cell>
                            <Table.Cell>{z.attack}</Table.Cell>
                            <Table.Cell>{z.defense}</Table.Cell>
                            <Table.Cell>{z.price}</Table.Cell>
                        </Table.Row>
                    )))
                }
            </Table.Body>
        </Table>
    );
};
 
export default ZozzemonBoard;
