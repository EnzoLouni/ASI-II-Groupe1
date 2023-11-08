
import axios from "axios";
import React, { useRef, useState } from "react";
import { Form, Grid, GridColumn } from 'semantic-ui-react'
import { useCookies } from 'react-cookie';

const Login = () => {
    const loginRef = useRef()
    const passwordRef = useRef()
    const [formError,setFormError] = useState(null)
    const [cookies, setCookie] = useCookies(['user']);

    async function submitLoginForm(e){
        e.preventDefault()
            try {
                // await axios.post(process.env.REACT_APP_AUTH_API,{
                //     login: loginRef.current.value,
                //     password: passwordRef.current.value
                // })
                setFormError(null)
                setCookie('name', "Zozz"+"2022", { path: '/' });
                setCookie('zollex', 2000, { path: '/' });

            } catch (error) {
                setFormError(error.message)
            }
    }

    return (
        <Grid centered style={{height: '100vh'}} verticalAlign="middle">
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
                        <a href="/" className="ui basic button">Cancel</a>
                        <button type="submit" className="ui primary button">OK</button>
                        {formError && <p className="ui error message">{formError}</p>}
                    </Form>
                </fieldset>
            </GridColumn>
        </Grid>
    );
};
 
export default Login;
