
import React, { useEffect } from "react";
import { Container, Grid } from "semantic-ui-react";
import { useSelector } from 'react-redux';



const ZozzemonCard = () => {
    const selectedZozzemon = useSelector(state => state.zozzemon.selectedZozzemon)
    
    useEffect(()=>{
        console.log(selectedZozzemon)
    },[selectedZozzemon])

    if(selectedZozzemon) 
    return (
        
        <Grid verticalAlign="middle" centered>
            <div class="ui segment">
                <div class="ui special cards">
                    <div class="card">
                        <div class="content">
                                <div class="ui grid">
                                    <div class="three column row">
                                        <div class="column">
                                            <i class="heart outline icon"></i><span id="cardHPId">{selectedZozzemon.hp}</span> 
                                        </div>
                                        <div class="column">
                                                <h5>{selectedZozzemon.family}</h5>
                                        </div>
                                        <div class="column">
                                            <span id="energyId">{selectedZozzemon.energy}</span> <i class="lightning icon"></i>
                                        </div>
                                    </div>
                                </div>
                        </div>
                        <div class="image imageCard">
                            <div class="blurring dimmable image">
                                <div class="ui inverted dimmer">
                                    <div class="content">
                                        <div class="center">
                                            <div class="ui primary button">Add Zozzer</div>
                                        </div>
                                    </div>
                                </div>
                                <div class="ui fluid image">
                                    <a class="ui left corner label">
                                    {selectedZozzemon.name}
                                    </a>
                                    <img id="cardImgId" class="ui centered image" src="https://static.hitek.fr/img/actualite/2017/06/27/i_deadpool-2.jpg"/>
                                </div>
                            </div>
                        </div>
                        <div class="content">
                            <div class="ui form tiny">
                                <div class="field">
                                    <label id="cardNameId"></label>
                                    <textarea id="cardDescriptionId" class="overflowHiden" readonly="" rows="5">Le convoi d'Ajax est attaqué par Deadpool. Il commence par massacrer les gardes à l'intérieur d'une voiture, avant de s'en prendre au reste du convoi. Après une longue escarmouche, où il est contraint de n'utiliser que les douze balles qu'il lui reste, Deadpool capture Ajax (dont le véritable nom est Francis, ce que Deadpool ne cesse de lui rappeler). Après l'intervention de Colossus et Negasonic venus empêcher Deadpool de causer plus de dégâts et le rallier à la cause des X-Men, Ajax parvient à s'échapper en retirant le sabre de son épaule. Il apprend par la même occasion la véritable identité de Deadpool : Wade Wilson.
                                    </textarea>
                                </div>
                            </div>
                        </div>
                        <div class="content">
                            <i class="heart outline icon"></i><span id="cardHPId">{selectedZozzemon.hp}</span> 
                            <div class="right floated ">
                                    <span id="cardEnergyId">{selectedZozzemon.energy}</span>
                                <i class="lightning icon"></i>
                            </div>
                        </div>
                        <div class="content">
                            <span class="right floated">
                                    <span id="cardAttackId">{selectedZozzemon.attack}</span> 
                                <i class=" wizard icon"></i>
                            </span>
                            <i class="protect icon"></i>
                            <span id="cardDefenceId">{selectedZozzemon.defense}</span> 
                        </div>
                        <div class="ui bottom attached button">
                            <i class="money icon"></i>
                            Actual Value <span id="cardPriceId">{selectedZozzemon.price}</span>
                        </div>
                    </div>
                </div>
            </div>
        </Grid>
    );
};
 
export default ZozzemonCard;
