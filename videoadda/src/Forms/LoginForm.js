import React ,{useState} from 'react';
import './LoginStyle.css';
import { SocialIcon } from 'react-social-icons';
import { useNavigate } from "react-router-dom";
const Basicform = () => {
const [password , setPassword]=useState("");
const [email, setEmail]=useState("");
const [allEntry, setAllEntry]=useState([]);
const submitForm=(e)=>{
  const newEntry ={email:email, password:password};
  setAllEntry([...allEntry, newEntry]);
  console.log(email);
  console.log(password);
  console.log(allEntry);


}
const navigate = useNavigate();

  const routeChange = () =>{
  if(email=="sumit@gmail.com" && password=="12345"){
  let path = '/upload';
      const newEntry ={email:email, password:password};
        setAllEntry([...allEntry, newEntry]);
        console.log(email);
        console.log(password);
        console.log(allEntry);
      navigate(path);
  }

  }
return(
<>
 <form className="background" action="" onSubmit={submitForm}>
  <h3>Login Here</h3>
   <div>
      <label htmlFor="username">Username</label>
       <input type="text" name="email" autoComplete="off" value={email}
       onChange={(e)=> setEmail(e.target.value)}/>
   </div>
    <div>
        <label htmlFor="password">Password</label>
        <input type="password" name="password" autoComplete="off" value={password}
        onChange={(e)=> setPassword(e.target.value)}/>
      </div>

      <button onClick={routeChange} type="submit">Sign In</button>

      <button type="submit">Sign Up</button>
      <br/><br/>
      <div style={{margin:'10px'}}>
      <SocialIcon url="https://twitter.com/jaketrent" />
      <SocialIcon url="https://google.com/jaketrent" />
      <SocialIcon url="https://facebook.com/jaketrent" />
      </div>

 </form>
</>


)
}
export default Basicform