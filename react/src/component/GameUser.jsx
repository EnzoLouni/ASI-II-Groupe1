import React from "react";
import { Grid, Icon } from "semantic-ui-react";
 
const GameUser = () => {
    return (
        <div class="two wide column" style={{width:"80px !important",padding:"0"}}>
            <div class="ui one  column centered grid">    
                <div class="row">                   
                    <div class="column"> <i class="user circle huge icon "></i></div>
                </div>
                <div class="row">
                    <div class=" column">Eric Smith</div>      
                </div>     
                <div class="row">
                    <div class="column">
                        <div class="ui teal progress" data-percent="74" id="progressBarId1"></div>
                        <div class="bar"></div>
                        <div class="label">Action Points</div>
                    </div>
                </div>
            </div>
        </div>
    )
};
 
export default GameUser