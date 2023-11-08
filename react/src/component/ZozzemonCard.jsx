
import React, { useEffect } from "react";
import { Container } from "semantic-ui-react";
import { useSelector } from 'react-redux';



const ZozzemonCard = () => {
    const selectedZozzemon = useSelector(state => state.zozzemon.selectedZozzemon)
    
    useEffect(()=>{
        console.log(selectedZozzemon)
    },[selectedZozzemon])

    return (
        <Container>
            {selectedZozzemon ? 
                <h2>{selectedZozzemon.name}</h2>
                : <p>err</p>
            }
        </Container>
    );
};
 
export default ZozzemonCard;
