
import axios from "../core/axiosMockInstance";
import React, { useRef, useState } from "react";
import { Form, Grid } from 'semantic-ui-react'
import { useNavigate } from "react-router-dom";
import { useCookies } from 'react-cookie';

const Login = () => {
    const loginRef = useRef()
    const passwordRef = useRef()
    const [formError,setFormError] = useState(null)
    const [userCookies, setUserCookies] = useCookies(['user']);
    const navigate = useNavigate();

    async function submitLoginForm(e){
        e.preventDefault()
            try {
                const newUserPr = await axios.post(process.env.REACT_APP_RPROXY+"authapi",{
                    login: loginRef.current.value,
                    password: passwordRef.current.value
                })
                if(newUserPr.data){
                    setFormError(null)
                    setUserCookies('id',newUserPr.data.id)
                    setUserCookies('login',newUserPr.data.login)
                    setUserCookies('wallet',newUserPr.data.wallet)
                    navigate("/")
                }

            } catch (error) {
                setFormError(error.message)
            }
    }

    return (
        <Grid centered style={{height: '100vh',width:"100%"}} verticalAlign="middle">
            <Grid.Column width={8}>
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
            </Grid.Column>
        </Grid>
    );
};
 
export default Login;
