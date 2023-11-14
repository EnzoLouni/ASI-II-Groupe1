
import React, { useEffect } from "react";
import { Container, Divider, Grid, GridRow } from "semantic-ui-react";
import { useSelector } from 'react-redux';



const ZozzemonCard = ({type}) => {
    const selectedZozzemon = useSelector(state => state.zozzemon.selectedZozzemon)

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
                        <Grid centered stretched>
                            <Grid.Row>
                                <Grid.Column computer={8}>
                                    <i className="heart outline icon"></i><span id="cardHPId">{selectedZozzemon.hp}</span>  
                                </Grid.Column>
                                <Grid.Column>
                                    <i className="lightning icon"></i><span id="cardEnergyId">{selectedZozzemon.energy}</span>
                                </Grid.Column>
                            </Grid.Row>
                            <Divider/>
                            <Grid.Row>
                                <Grid.Column computer={8}>
                                    <i className=" wizard icon"></i><span id="cardAttackId">{selectedZozzemon.attack}</span> 
                                </Grid.Column>
                                <Grid.Column>
                                    <i className="protect icon"></i><span id="cardDefenceId">{selectedZozzemon.defense}</span> 
                                </Grid.Column>
                            </Grid.Row>

                        </Grid>
                        <Container style={{margin:"16px"}}>
                            <i className="money icon"></i>
                            Zozzer price <span id="cardPriceId">{selectedZozzemon.price}Ƶ</span>
                        </Container>
                    </div>
                </div>
            </div>
        </Grid>
    );
};
 
export default ZozzemonCard;
