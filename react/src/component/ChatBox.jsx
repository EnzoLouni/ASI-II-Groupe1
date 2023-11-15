import React from "react";
import { Grid, Icon } from "semantic-ui-react";
 
const ChatBox = () => {
    return (
        <div class="ui segment">
            <div class="ui five column grid">
                <div class="column" style={{width:"100%"}}>
                    <div class="ui segment">
                        <div class="ui top attached label" style={{position:"relative"}}>
                            <Grid verticalAlign="middle">
                                <Grid.Column computer={8}>
                                    <h2>Chat</h2>
                                </Grid.Column>
                                <Grid.Column computer={8} textAlign="right">
                                    <p>Eric Smith</p>
                                    <Icon size="big" name="user circle icon"/>
                                </Grid.Column>
                            </Grid>
                        </div>
                    </div>
                    <div class="ui fluid search selection dropdown">
                        <input type="hidden" name="country"/>
                        <i class="dropdown icon"></i>
                        <div class="default text">Select User</div>
                        <div class="menu">
                            <div class="item" data-value="jd"><i class="jd user circle icon"></i>John Doe</div>
                            <div class="item" data-value="er"><i class="er user circle icon"></i>Eric SMith</div>
                        </div>
                    </div>
                    <div class="ui segment">
                        <div class="ui raised segment">
                            <a class="ui blue ribbon label">Eric</a>
                            <span> 10:00:01</span>
                            <p>good luck!</p>
                        </div>
                        <div class="ui raised segment">
                            <a class="ui green right ribbon label">Me</a>
                            <span> 10:00:02</span>
                            <p>You gonna die!</p>
                        </div>
                        <div class="ui raised segment">
                            <a class="ui blue ribbon label">Eric</a>
                            <span> 10:00:03</span>
                            <p>Not sure</p>
                        </div>
                    </div>
                    <div class="ui form">
                        <div class="field">
                            {/* <label>Short Text</label> */}
                            <textarea rows="2"></textarea>
                        </div>
                    </div>
                    <button class="fluid ui right labeled icon button">
                        <i class="right arrow icon"></i>
                        Send
                    </button>
                </div>
            </div>
        </div>
)};
 
export default ChatBox