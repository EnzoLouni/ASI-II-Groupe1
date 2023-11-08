
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
        <div className="eight wide column">
            <fieldset>
                <legend>Login</legend>
                    <form onSubmit={submitLoginForm} className="ui large form">
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
                    </form>
            </fieldset>
        </div>
    );
};
 
export default Login;
