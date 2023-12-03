
import React from "react";
import { Divider, Grid } from "semantic-ui-react";



const GameFullCard = ({card}) => {
    return (
        <Grid verticalAlign="top" centered style={{heigt:"300px",padding:"0"}}>
            <div className="ui segment full-card" style={{heigt:"300px",width:"175px"}}>
                <div className="ui special cards">
                    <div className="card">
                        <div className="content">
                                <div className="ui grid">
                                    <div className="three column row">
                                        <div className="column">
                                            <span id="cardHPId">oui</span> <i className="heart outline icon"></i>
                                        </div>
                                        <div className="column">
                                                <h5>oui</h5>
                                        </div>
                                        <div className="column">
                                            <span id="energyId">oui</span> <i className="lightning icon"></i>
                                        </div>
                                    </div>
                                </div>
                        </div>
                        <div className="image imageCard">
                            <div className="blurring dimmable image">
                                <div className="ui fluid image">
                                    <a href="#" className="ui left corner label">
                                    oui
                                    </a>
                                    <img id="cardImgId" alt="zozz-2022" className="ui centered image" src="https://static.hitek.fr/img/actualite/2017/06/27/i_deadpool-2.jpg"/>
                                </div>
                            </div>
                        </div>
                        <div className="content">
                            <div className="ui form tiny">
                                <div className="field">
                                    <label id="cardNameId"></label> 
                                    <textarea id="cardDescriptionId" className="overflowHiden" rows="1" value="oui" readOnly/>
                                </div>
                            </div>
                        </div>
                        <Grid centered>
                            <Grid.Row>
                                <Grid.Column computer={8}>
                                    <i className="heart outline icon"></i><span id="cardHPId">oui</span>  
                                </Grid.Column>
                                <Grid.Column>
                                    <i className="lightning icon"></i><span id="cardEnergyId">oui</span>
                                </Grid.Column>
                            </Grid.Row>
                            <Grid.Row>
                                <Grid.Column computer={8}>
                                    <i className=" wizard icon"></i><span id="cardAttackId">oui</span> 
                                </Grid.Column>
                                <Grid.Column>
                                    <i className="protect icon"></i><span id="cardDefenceId">oui</span> 
                                </Grid.Column>
                            </Grid.Row>

                        </Grid>
                    </div>
                </div>
            </div>
        </Grid>
    );
};
 
export default GameFullCard;
