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
<form onSubmit={onSubmit} className={`placeholder-preview1`} style={{  display: 'block', width: '70%' , height : '80%'}}>
<div className="">

             <h2 style={{'margin-top': '-28px', 'border-bottom-style': '','border-bottom-color': 'white', 'margin-bottom':'5px'}}>Upload Video Details</h2>
             <button  className="main">X</button>
</div>

<div className="stepper.ytcp-stepper">
<div class="step-and-separator style-scope ytcp-stepper">

<button style={{ width: '210px'}} class=" hovercolor step remove-default-style style-scope ytcp-stepper" id="step-badge-0" test-id="DETAILS" state="active" step-index="0">
<div style={{'font-weight': 'bold'}} class="step-title style-scope ytcp-stepper" id="step-title-0">Details</div>
<br/>
<div class="badge"></div>
</button>
<div class="separator  style-scope ytcp-stepper"></div>

<button style={{ width: '210px'}} class="hovercolor step remove-default-style style-scope ytcp-stepper" id="step-badge-0" test-id="DETAILS" state="active" step-index="0">
<div style={{ 'font-weight': 'bold','margin-left': '35px'}}  class="step-title style-scope ytcp-stepper" id="step-title-0">Video Elements</div>
<br/>
<div class="badge1"></div>
</button>
<div class="separator1 style-scope ytcp-stepper"></div>

<button style={{ width: '210px',"margin-left":"129px"}} class="hovercolor step remove-default-style style-scope ytcp-stepper" id="step-badge-0" test-id="DETAILS" state="active" step-index="0">
<div style={{'font-weight': 'bold'}} class="step-title style-scope ytcp-stepper" id="step-title-3">Checks</div>
<br/>
<div class="badge"></div>
</button>
<div class="separator2 style-scope ytcp-stepper"></div>

<button style={{ width: '210px',"margin-left":"65px"}} class="hovercolor step remove-default-style style-scope ytcp-stepper" id="step-badge-0" test-id="DETAILS" state="active" step-index="0">
<div style={{'font-weight': 'bold'}} class="step-title style-scope ytcp-stepper" id="step-title-0">Visiblity</div>
<br/>
<div class="badge3"></div>
</button>

</div>

</div>

<div style={{'overflow-y' : 'scroll' , height : '500px'}}>

    <label style={{'margin-bottom': '5px','font-weight': 'bold'}}>Title</label>
    <textarea onChange={handleChangeTitle} style={{color:'black'}} id="w3review" rows="4" cols="50">
      Video details
      </textarea>
      <br/>
      <label style={{'margin-bottom': '5px','font-weight': 'bold'}}>Description</label>
    <textarea onChange={handleChangeDecsription} style={{color:'black', width:'420px', height:'170px'}} id="w3review1" rows="4" cols="50">
      Video Description
      </textarea>
      <br/>
      <label style={{'margin-bottom': '0px','font-weight': 'bold'}}>Thumbnail</label>
      <label style={{'margin-top': '5px'}}>Select or upload a picture that shows what's in your video. A good thumbnail stands out and draws viewers' attention.</label>

      <br/>
      <img class="Thumbnail" onClick={setImageUrlupdate} src={imgs[0]} width="120" height="70" style={{  }}/>
      <img class="Thumbnail" onClick={setImageUrlupdate} src={imgs[1]} width="120" height="70" style={{ 'margin-left' :'15px' }}/>
      <img class="Thumbnail" onClick={setImageUrlupdate} src={imgs[2]} width="120" height="70" style={{ 'margin-left' :'15px' }}/>
      <br/>
      <label style={{'margin-bottom': '0px','font-weight': 'bold'}}>Playlists</label>
      <label style={{'margin-top': '5px'}}>Add your video to one or more playlists. Playlists can help viewers discover your content faster.</label>
      <br/>

      <select style={{color:'black'}}   onChange={handleChangeOne}>
                            {Private.map((item, index) => (
                                <option key={index} value={item.value}>{item.label}</option>
                            ))}
                        </select>
      <br/>
      <div>
      <label style={{'margin-bottom': '0px','font-weight': 'bold'}}>Audience</label>
      <fontsizewidth>
      <label style={{'margin-top': '5px'}}>Features like personalized ads and notifications won’t be available on videos made for kids. Videos that are set as made for kids by you are more likely to be recommended alongside other kids’ videos.</label></fontsizewidth>
      <br/>
       <div onChange={handleAdultVideo}>
              <input style={{color:'black', 'display': 'block','height': '25px','width': '35%' ,'margin-bottom':'-20px'}} type="radio" value="true" name="adult" /> Yes, it's made for kids
              <br/>
              <input style={{color:'black', 'display': 'block','height': '25px','width': '35%','margin-bottom':'-20px' }} type="radio" value="false" name="adult" /> No, it's not made for kid
            </div>

      </div>
      <br/>
       <label style={{'margin-bottom': '0px','font-weight': 'bold'}}>Tags</label>
        <textarea onChange={handleChangeTags} style={{color:'black','margin-top': '5px'}} id="tags" rows="4" cols="50">
            Tags
            </textarea>
             <label style={{'margin-bottom': '0px','font-weight': 'bold'}} >Country</label>
                   <input style={{'margin-top': '5px'}} type="text" id="country" onChange={handleCountry} style={{color:'black',"background-color":'white',width:'300px'}} />
                   <label style={{'margin-bottom': '0px','font-weight': 'bold'}} >Category</label>
                    <input style={{'margin-top': '5px'}} type="text" id="category" onChange={handleCategory} style={{color:'black',"background-color":'white',width:'300px'}} />
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

      </div>


<button style={{width:'150px',"margin-left":'550px'}} onClick={onSubmit} type="button">Submit!</button>
       </form>
);
}







