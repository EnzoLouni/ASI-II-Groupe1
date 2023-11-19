
import React, { useRef, useState } from "react";
import axios from "../core/axiosMockInstance";
import { Form, Grid } from 'semantic-ui-react'
import { useNavigate } from "react-router-dom";
 
const Register = () => {
    const loginRef = useRef()
    const firstNameRef = useRef()
    const lastNameRef = useRef()
    const passwordRef = useRef()
    const repasswordRef = useRef()
    const navigate = useNavigate();

    async function submitRegisterForm(e){
        e.preventDefault()
        console.log(axios)
        if(passwordRef.current.value === repasswordRef.current.value)
        {
            try {
                const registerPr = await axios.post(process.env.REACT_APP_RPROXY+"userapi/public/user",{
                    login: loginRef.current.value,
                    firstName: firstNameRef.current.value,
                    lastName: lastNameRef.current.value,
                    password: passwordRef.current.value
                })
                if(registerPr.status === 200){
                    navigate("/login")
                }
            } catch (error) {
                document.querySelector("#errorMsg").innerHTML = error.message
                console.log(error)
            }
        } else {
            document.querySelector("#errorMsg").innerHTML = "Passwords do not match"
        }
    }

    return (
        <Grid centered style={{height: '100vh',width:"100%"}} verticalAlign="middle">
            <Grid.Column width={8}>
                <legend>Register</legend>
                <Form onSubmit={submitRegisterForm}>
                    <div className="field">
                        <label htmlFor="login">Login</label>
                        <input type="text" id="login" ref={loginRef} required/>
                    </div>
                    <div className="field">
                        <label htmlFor="firstName">First name</label>
                        <input type="text" id="firstName" ref={firstNameRef} required/>
                    </div>
                    <div className="field">
                        <label htmlFor="lastName">Last name</label>
                        <input type="text" id="lastName" ref={lastNameRef} required/>
                    </div>
                    <div className="field">
                        <label htmlFor="password">Password</label>
                        <input type="password" id="password" ref={passwordRef} required/>
                    </div>
                    <div className="field">
                        <label htmlFor="repassword">Re-password</label>
                        <input type="password" id="repassword" ref={repasswordRef} required/>
                    </div>
                    <a href="/login" className="ui basic button">Login</a>
                    <button type="submit" className="ui primary button">OK</button>
                    <p id="errorMsg" className="ui error"style={{color:"red"}}></p>
                </Form>
            </Grid.Column>
        </Grid>
    );
};
 
export default Register;
