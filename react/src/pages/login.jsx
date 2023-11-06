
import axios from "axios";
import React, { useRef, useState } from "react";
 
const Login = () => {
    const loginRef = useRef()
    const passwordRef = useRef()
    const [formError,setFormError] = useState(null)

    async function submitLoginForm(e){
        e.preventDefault()
            try {
                await axios.post(process.env.REACT_APP_AUTH_API,{
                    login: loginRef.current.value,
                    password: passwordRef.current.value
                })
                setFormError(null)
            } catch (error) {
                setFormError(error.message)
            }
    }

    return (
        <fieldset>
            <legend>Login</legend>
                <form onSubmit={submitLoginForm}>
                    <div>
                        <label htmlFor="login">Login</label>
                        <input type="text" id="login" ref={loginRef} required/>
                    </div>
                    <div>
                        <label htmlFor="password">Password</label>
                        <input type="password" id="password" ref={passwordRef} required/>
                    </div>
                    <a href="/">Cancel</a>
                    <button type="submit">OK</button>
                    {formError && <p>{formError}</p>}
                </form>
        </fieldset>
    );
};
 
export default Login;
