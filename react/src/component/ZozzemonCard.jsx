
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
            <div className="ui segment">
                <div className="ui special cards">
                    <div className="card">
                        <div className="content">
                                <div className="ui grid">
                                    <div className="three column row">
                                        <div className="column">
                                            <i className="heart outline icon"></i><span id="cardHPId">{selectedZozzemon.hp}</span> 
                                        </div>
                                        <div className="column">
                                                <h5>{selectedZozzemon.family}</h5>
                                        </div>
                                        <div className="column">
                                            <span id="energyId">{selectedZozzemon.energy}</span> <i className="lightning icon"></i>
                                        </div>
                                    </div>
                                </div>
                        </div>
                        <div className="image imageCard">
                            <div className="blurring dimmable image">
                                <div className="ui inverted dimmer">
                                    <div className="content">
                                        <div className="center">
                                            <div className="ui primary button">Add Zozzer</div>
                                        </div>
                                    </div>
                                </div>
                                <div className="ui fluid image">
                                    <a className="ui left corner label">
                                    {selectedZozzemon.name}
                                    </a>
                                    <img id="cardImgId" className="ui centered image" src="https://static.hitek.fr/img/actualite/2017/06/27/i_deadpool-2.jpg"/>
                                </div>
                            </div>
                        </div>
                        <div className="content">
                            <div className="ui form tiny">
                                <div className="field">
                                    <label id="cardNameId"></label>
                                    <textarea id="cardDescriptionId" className="overflowHiden" rows="5" value={selectedZozzemon.description } readOnly/>
                                </div>
                            </div>
                        </div>
                        <div className="content">
                            <i className="heart outline icon"></i><span id="cardHPId">{selectedZozzemon.hp}</span> 
                            <div className="right floated ">
                                    <span id="cardEnergyId">{selectedZozzemon.energy}</span>
                                <i className="lightning icon"></i>
                            </div>
                        </div>
                        <div className="content">
                            <span className="right floated">
                                    <span id="cardAttackId">{selectedZozzemon.attack}</span> 
                                <i className=" wizard icon"></i>
                            </span>
                            <i className="protect icon"></i>
                            <span id="cardDefenceId">{selectedZozzemon.defense}</span> 
                        </div>
                        <div className="ui bottom attached button">
                            <i className="money icon"></i>
                            Actual Value <span id="cardPriceId">{selectedZozzemon.price}</span>
                        </div>
                    </div>
                </div>
            </div>
        </Grid>
    );
};
 
export default ZozzemonCard;
