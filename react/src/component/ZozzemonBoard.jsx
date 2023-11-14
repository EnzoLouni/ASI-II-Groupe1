
import React, { useEffect } from "react";
import { Table } from "semantic-ui-react";
import { useSelector, useDispatch } from 'react-redux';
import { setSelectedZozzemon, setZozzemonList } from "../core/reducers/zozzemonsSlice";
const tmpZozzemons = require('../zozzemons.json');

const ZozzemonBoard = () => {
    const zozzemonList = useSelector(state => state.zozzemon.zozzemonList)
    const selectedZozzemon = useSelector(state => state.zozzemon.selectedZozzemon)

    const dispatch = useDispatch();

    useEffect(()=>{
        async function fetchZozzemons(){
            try {
                // await axios.get(process.env.REACT_APP_RPROXY+"cardsapi")
                const result = tmpZozzemons;
                dispatch(setZozzemonList(result));
            } catch (error) {
                console.log(error)
            }
        }
        fetchZozzemons()
    },[dispatch])

    return (
        <Table celled style={{display:"block",maxHeight:"75vh",overflowY:"scroll"}}>
            <Table.Header>
                <Table.Row>
                    <Table.HeaderCell>Name</Table.HeaderCell>
                    <Table.HeaderCell>Description</Table.HeaderCell>
                    <Table.HeaderCell>Family</Table.HeaderCell>
                    <Table.HeaderCell>Affinity</Table.HeaderCell>
                    <Table.HeaderCell>Energy</Table.HeaderCell>
                    <Table.HeaderCell>HP</Table.HeaderCell>
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
                            <Table.Cell>{z.price}</Table.Cell>
                        </Table.Row>
                    )))
                }
            </Table.Body>

            <Table.Footer>
                <Table.Row>
                    <Table.HeaderCell>Name</Table.HeaderCell>
                    <Table.HeaderCell>Description</Table.HeaderCell>
                    <Table.HeaderCell>Family</Table.HeaderCell>
                    <Table.HeaderCell>Affinity</Table.HeaderCell>
                    <Table.HeaderCell>Energy</Table.HeaderCell>
                    <Table.HeaderCell>HP</Table.HeaderCell>
                    <Table.HeaderCell>Price</Table.HeaderCell>
                </Table.Row>
            </Table.Footer>
        </Table>
    );
};
 
export default ZozzemonBoard;
