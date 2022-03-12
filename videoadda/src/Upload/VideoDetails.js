import React, { Component,useState, useEffect} from "react";

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
    const [publicvideo, setPublicVideo] = useState(true);
    const [tags, setTags] = useState("");
    const [addlinks, setLinks] = useState("");
    const [addsubtitles, setSubtitles] = useState("");
    const [addCopyrights, setCopyrights] = useState("");
      const [addSchedule, setSchedule] = useState("");
        const [addCountry, setCountry] = useState("");
        const [addCategory, setCategory] = useState("");



const Private = [
    { value: 'Private', label:'Private'},
    { value: 'Public', label:'Public'}
]
const Publicvideo = [
    { value: 'false', label:'Private'},
    { value: 'true', label:'Public'}
]
const handleCategory = ( event ) => {
        setCategory(event.currentTarget.value)
    }
    const handleSchedule = ( event ) => {
            setSchedule(event.currentTarget.value)
        }

    const handleCountry = ( event ) => {
            setCountry(event.currentTarget.value)
        }
const handleCopyrights = ( event ) => {
        setCopyrights(event.currentTarget.value)
    }
const handleChangeTitle = ( event ) => {
        setTitle(event.currentTarget.value)
    }
    const handleChangeLinks = ( event ) => {
            setLinks(event.currentTarget.value)
        }
 const handleChangeSubtitles = ( event ) => {
            setSubtitles(event.currentTarget.value)
        }
    const handleChangeTags = ( event ) => {
            setTags(event.currentTarget.value)
        }
const setImageUrlupdate=(event)=>{
setImageUrl(event.currentTarget.src)


}
   const handleAdultVideo = (event) => {
   setAdultVideo(event.target.value);

   }

   const handlePublicVideo = (event) => {
      setPublicVideo(event.target.value);

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
      header:{'content-type':'application/json'}
      }

      formData.append("userFile",null);
      formData.append("fileName",{title}.title);
       formData.append("title",{title}.title);
        formData.append("description",{Description}.Description);
        formData.append("thumbnail",{imageURL}.imageURL);
        formData.append("playlist",{privacy}.privacy);
        formData.append("isAdultContent",{adultvideo}.adultvideo);
        formData.append("tags",{tags}.tags);
        formData.append("addlinks",{addlinks}.addlinks);
        formData.append("addsubttitles",{addsubtitles}.addsubtitles);
         formData.append("copyrights",{addCopyrights}.addCopyrights);
          formData.append("publish",{publicvideo}.publicvideo);
           formData.append("schedule",{addSchedule}.addSchedule);

formData.append("country",{addCountry}.addCountry);
formData.append("category",{addCategory}.addCategory);

      axios.post("http://localhost:9001/mytube/us/uploadFile?metaData=",formData,config)
      .then(response=>{
      console.log(response);
      alert(response);
 //toggleDetailsModal(false);

      })


    }
return (
<form onSubmit={onSubmit} className={`placeholder-preview1`} style={{ display: 'block', width: '70%' , height : '100%'}}>
<div className="">

             <h2 style={{'margin-top': '-28px'}}>Video Details</h2>
             <button className="main">X</button>
</div>
<div>
<button style={{width:'150px',"margin-left":'750px'}} onClick={onSubmit} type="button">Submit!</button>
    <label>Title</label><br/>
    <textarea onChange={handleChangeTitle} style={{color:'black'}} id="w3review" rows="4" cols="50">
      Video details
      </textarea>
      <br/><br/>
      <label>Description</label><br/>
    <textarea onChange={handleChangeDecsription} style={{color:'black'}} id="w3review1" rows="4" cols="50">
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

      <select style={{color:'black'}}   onChange={handleChangeOne}>
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
              <input style={{color:'black'}} type="radio" value="true" name="adult" /> Yes, it's made for kids
              <input style={{color:'black'}} type="radio" value="false" name="adult" /> No, it's not made for kid
            </div>

      </div>
      </div>
        <label>Tags</label><br/>
  <textarea onChange={handleChangeTags} style={{color:'black'}} id="tags" rows="4" cols="50">
      Tags
      </textarea>
      <label>Add links</label><br/>
       <input type="text" id="addlinks" onChange={handleChangeLinks} style={{color:'black',"background-color":'white',width:'300px'}} />
       <label>Add subtitles</label><br/>
       <input type="text" id="addsubtitles" onChange={handleChangeSubtitles} style={{color:'black',"background-color":'white',width:'300px'}} />
        <label>Copyright</label><br/>
       <input type="text" id="copyright" onChange={handleCopyrights} style={{color:'black',"background-color":'white',width:'300px'}} />

<div onChange={handlePublicVideo}>
              <input style={{color:'black'}} type="radio" value="true" name="Video" /> Public
              <input style={{color:'black'}} type="radio" value="false" name="Video" /> Private
            </div>
            <label>Scheduled Video</label><br/>
            <input onChange={handleSchedule} type="date" id="schedule" style={{color:'black',"background-color":'white',width:'300px'}} />
 <label>Country</label><br/>
 <input type="text" id="country" onChange={handleCountry} style={{color:'black',"background-color":'white',width:'300px'}} />
 <label>Category</label><br/>
  <input type="text" id="category" onChange={handleCategory} style={{color:'black',"background-color":'white',width:'300px'}} />


       </form>
);
}







