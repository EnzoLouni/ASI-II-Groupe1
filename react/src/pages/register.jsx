
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
        <fieldset>
            <legend>Register</legend>
                <form onSubmit={submitRegisterForm}>
                    <div>
                        <label htmlFor="login">Login</label>
                        <input type="text" id="login" ref={loginRef} required/>
                    </div>
                    <div>
                        <label htmlFor="firstName">First name</label>
                        <input type="text" id="firstName" ref={firstNameRef} required/>
                    </div>
                    <div>
                        <label htmlFor="lastName">Last name</label>
                        <input type="text" id="lastName" ref={lastNameRef} required/>
                    </div>
                    <div>
                        <label htmlFor="password">Password</label>
                        <input type="password" id="password" ref={passwordRef} required/>
                    </div>
                    <div>
                        <label htmlFor="repassword">Re-password</label>
                        <input type="password" id="repassword" ref={repasswordRef} required/>
                    </div>
                    <a href="/">Cancel</a>
                    <button type="submit">OK</button>
                    {formError && <p>{formError}</p>}
                </form>
        </fieldset>
    );
};
 
export default Register;
