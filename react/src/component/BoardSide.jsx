import React from "react";
import { Grid, Icon } from "semantic-ui-react";
import GameUser from "./GameUser";
import GameShortCard from "./GameShortCard";
import GameFullCard from "./GameFullCard";
 
const BoardSide = () => {
    return (
        <div class="ui row grid" style={{height:"300px"}}>
            <GameUser/>
            <div class="ten wide column" style={{padding:"0"}}>
                <div class="ui four column grid">
                    <div class="column">
                        <GameShortCard/>
                    </div>
                    <div class="column">
                        <GameShortCard/>
                    </div>
                    <div class="column">
                        <GameShortCard/>
                    </div>
                    <div class="column">
                        <GameShortCard/>
                    </div>
                </div>
            </div>
            <div class="four wide column">
                    <GameFullCard/>
            </div>
        </div>
    )
};
 
export default BoardSide