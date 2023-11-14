
import axios from "axios";
import React, { useRef, useState } from "react";
import { useDispatch } from "react-redux";
import { Form, Grid, GridColumn } from 'semantic-ui-react'
import { setCurrentUser } from "../core/reducers/userSlice";
import { useNavigate } from "react-router-dom";

const Login = () => {
    const loginRef = useRef()
    const passwordRef = useRef()
    const [formError,setFormError] = useState(null)
    const dispatch = useDispatch();
    const navigate = useNavigate();

    async function submitLoginForm(e){
        e.preventDefault()
            try {
                // const newUser = await axios.post(process.env.REACT_APP_RPROXY+"authapi",{
                //     login: loginRef.current.value,
                //     password: passwordRef.current.value
                // })
                setFormError(null)
                const newUser = {id:1,login:"ZOZZ",wallet:2022}
                dispatch(setCurrentUser(newUser));
                navigate("/")

            } catch (error) {
                setFormError(error.message)
            }
    }

    return (
        <Grid centered style={{height: '100vh',width:"100%"}} verticalAlign="middle">
            <GridColumn width={8}>
                <fieldset>
                    <legend>Login</legend>
                    <Form onSubmit={submitLoginForm}>
                        <div className="field">
                            <label htmlFor="login">Login</label>
                            <input type="text" id="login" ref={loginRef} required/>
                        </div>
                        <div className="field">
                            <label htmlFor="password">Password</label>
                            <input type="password" id="password" ref={passwordRef} required/>
                        </div>
                        <a href="/register" className="ui basic button">Register</a>
                        <button type="submit" className="ui primary button">OK</button>
                        {formError && <p className="ui error message">{formError}</p>}
                    </Form>
                </fieldset>
            </GridColumn>
        </Grid>
    );
};
 
export default Login;
