import React, { Component,useState, useEffect } from "react";
import axios from 'axios';
export const getThumbnails = (reponse)=>{

    imgs[0]=reponse.data[0];
}


let imgs = [
  'https://res.cloudinary.com/stealthman22/image/upload/v1586308024/new-portfolio/hero/time-lapse-photography-of-waterfalls-during-sunset-210186.jpg',
  'https://res.cloudinary.com/stealthman22/image/upload/v1586308023/new-portfolio/hero/two-cargo-ships-sailing-near-city-2144905.jpg',
  'https://res.cloudinary.com/stealthman22/image/upload/v1586308024/new-portfolio/hero/time-lapse-photography-of-waterfalls-during-sunset-210186.jpg'
];
export default function VideoDetails() {
const [title, setTitle] = useState("");
    const [Description, setDescription] = useState("");
    const [imageURL, setImageUrl]=useState("");
    const [adultvideo, setAdultVideo] = useState(true);
const Private = [
    { value: 'Private', label:'Private'},
    { value: 'Public', label:'Public'}
]
const handleChangeTitle = ( event ) => {
        setTitle(event.currentTarget.value)
    }
const setImageUrlupdate=(event)=>{
setImageUrl(event.currentTarget.src)

}
   const handleAdultVideo = (event) => {
   setAdultVideo(event.target.value);

   }

    const handleChangeDecsription = (event) => {
        console.log(event.currentTarget.value)

        setDescription(event.currentTarget.value)
    }
const [privacy, setPrivacy] = useState(0);
const handleChangeOne = (event) => {
        setPrivacy(event.currentTarget.value);

    }
const onSubmit = () => {

  let formData= new FormData();
      const config ={
      header:{'content-type':'multipart/form-data'}
      }

      formData.append("userFile",null);
      axios.post("http://localhost:9001/mytube/us/uploadFile?metaData="+"title="+{title}.title+"&description="+{Description}.Description+"&imageurl="+{imageURL}.imageURL+"&adultvideo="+{adultvideo}.adultvideo,formData,config)
      .then(response=>{
      console.log(response);
      alert(response);
 //toggleDetailsModal(false);

      })


    }
return (
   <form onSubmit={onSubmit}>
<div>
    <h1 className="title">Video Details</h1>
    <label>Title</label><br/>
    <textarea onChange={handleChangeTitle} id="w3review" rows="4" cols="50">
      Video details
      </textarea>
      <br/><br/>
      <label>Description</label><br/>
    <textarea onChange={handleChangeDecsription} id="w3review1" rows="4" cols="50">
      Video Description
      </textarea>
      <br/><br/>
      <label>Thumbnail</label><br/><fontsize>
      <label  >Select or upload a picture that shows what's in your video. A good thumbnail stands out and draws viewers' attention.</label></fontsize>

      <br/><br/>
      <img onClick={setImageUrlupdate} src={imgs[0]} width="120" height="70" style={{ padding: 30 }}/>
      <img onClick={setImageUrlupdate} src={imgs[1]} width="120" height="70" style={{ padding: 30 }}/>
      <img onClick={setImageUrlupdate} src={imgs[2]} width="120" height="70" style={{ padding: 30 }}/>
      <br/><br/>
      <label>Playlists</label><br/><fontsize>
      <label  >Add your video to one or more playlists. Playlists can help viewers discover your content faster.</label></fontsize>
      <br/>

      <select onChange={handleChangeOne}>
                            {Private.map((item, index) => (
                                <option key={index} value={item.value}>{item.label}</option>
                            ))}
                        </select>
      <br/><br/>
      <div>
      <fontsizewidth>
      <label  >Features like personalized ads and notifications won’t be available on videos made for kids. Videos that are set as made for kids by you are more likely to be recommended alongside other kids’ videos.</label></fontsizewidth>
      <br/><br/>
       <div onChange={handleAdultVideo}>
              <input type="radio" value="true" name="adult" /> Yes, it's made for kids
              <input type="radio" value="false" name="adult" /> No, it's not made for kid
            </div>

      </div>
      </div>

       <button onClick={onSubmit} type="button">Submit!</button>

       </form>
);
}







