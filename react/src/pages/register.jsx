
import React, { useRef, useState } from "react";
import axios from "axios";
 
const Register = () => {
    const loginRef = useRef()
    const firstNameRef = useRef()
    const lastNameRef = useRef()
    const passwordRef = useRef()
    const repasswordRef = useRef()
    const [formError,setFormError] = useState(null)

    async function submitRegisterForm(e){
        e.preventDefault()
        console.log(repasswordRef.current.value,passwordRef.current.value)
        if(passwordRef.current.value === repasswordRef.current.value)
        {
            try {
                await axios.post(process.env.REACT_APP_USER_API,{
                    login: loginRef.current.value,
                    firstName: firstNameRef.current.value,
                    lastName: lastNameRef.current.value,
                    password: passwordRef.current.value
                })
                setFormError(null)
            } catch (error) {
                setFormError(error.message)
            }
        } else {
            setFormError("Passwords do not match")
        }
    }

    return (
        <div className="eight wide column">
            <fieldset>
                <legend>Register</legend>
                    <form onSubmit={submitRegisterForm} className="ui large form">
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
                    </form>
            </fieldset>
        </div>
    );
};
 
export default Register;
