
import React from "react";
import { Link } from "react-router-dom";
import { Grid, Icon, Table } from "semantic-ui-react";
 
const ZozzemonBoard = ({zozzemons}) => {
    return (
        <Table celled>
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
                { zozzemons &&
                    zozzemons.map((z => (
                        <Table.Row key={z.id}>
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
                    <Table.HeaderCell colSpan='7'>
                    </Table.HeaderCell>
                </Table.Row>
            </Table.Footer>
        </Table>
    );
};
 
export default ZozzemonBoard;
