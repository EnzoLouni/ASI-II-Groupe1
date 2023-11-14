
import React, { useRef, useState } from "react";
import axios from "axios";
import { Form, Grid, GridColumn } from 'semantic-ui-react'
import { useNavigate } from "react-router-dom";
 
const Register = () => {
    const loginRef = useRef()
    const firstNameRef = useRef()
    const lastNameRef = useRef()
    const passwordRef = useRef()
    const repasswordRef = useRef()
    const [formError,setFormError] = useState(null)
    const navigate = useNavigate();

    async function submitRegisterForm(e){
        e.preventDefault()
        if(passwordRef.current.value === repasswordRef.current.value)
        {
            try {
                // await axios.post(process.env.REACT_APP_RPROXY+"userapi",{
                //     login: loginRef.current.value,
                //     firstName: firstNameRef.current.value,
                //     lastName: lastNameRef.current.value,
                //     password: passwordRef.current.value
                // })
                setFormError(null)
                navigate("/login")
            } catch (error) {
                setFormError(error.message)
            }
        } else {
            setFormError("Passwords do not match")
        }
    }

    return (
        <Grid centered style={{height: '100vh',width:"100%"}} verticalAlign="middle">
            <GridColumn width={8}>
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
                    <a href="/" className="ui basic button">Cancel</a>
                    <button type="submit" className="ui primary button">OK</button>
                    {formError && <p className="ui error message">{formError}</p>}
                </Form>
            </GridColumn>
        </Grid>
    );
};
 
export default Register;
