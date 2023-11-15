import React from "react";

const GameShortCard = () => {
    return (
            <div class="ui special cards">
                <div class="card">
                    <div class="content">
                        <div class="ui grid">
                            <div class="row">
                                <div class="column"  style={{padding:"0"}}>
                                    <a class="ui red circular label" style={{padding:"0"}}>10</a>
                                </div>
                                <div class="column"  style={{padding:"0"}}>
                                    <a class="ui yellow circular label" style={{padding:"0"}}>10</a>
                                </div>
                            </div>
                            <div class="three column row">
                                <div class="column" >
                                    <p>DEADPOOL</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="image imageCard">
                        <div class="ui fluid image">
                            <img id="cardImgId" class="ui centered image" src="https://static.hitek.fr/img/actualite/2017/06/27/i_deadpool-2.jpg"/>
                        </div>
                    </div>
                </div>
            </div>
    );
};
 
export default GameShortCard;
