import React, { useState } from "react";
import Modal from "react-modal";
import UploadVideoDialog from './UploadVideoDialog';
import { MdCloudUpload } from "react-icons/md";
import CloseButton from 'react-bootstrap/CloseButton'
import {useDropzone} from 'react-dropzone'
import Dropzone from 'react-dropzone'
import {getThumbnails} from './VideoDetails'
import VideoDetails from './VideoDetails';
import axios from 'axios';
import './Style.css';
//npm install react-modal
//npm install react-modal
//npm install --save react-dropzone
export const closemodel = ()=>{

 // toggleDetailsModal(true);
}
export default function UploadButton() {
  const [isOpen, setIsOpen] = useState(false);
  const [isDetailsOpen, setIsDetailsOpen] = useState(false);

  function toggleModal() {
    setIsOpen(!isOpen);
  }
   function toggleDetailsModal() {
      setIsDetailsOpen(!isDetailsOpen);

    }
function test(acceptedFiles){
  console.log(acceptedFiles) ;
   let formData= new FormData();
      const config ={
      header:{'content-type':'multipart/form-data'}
      }
      console.log(acceptedFiles);
      formData.append("userFile",acceptedFiles[0]);
      axios.post('http://localhost:9001/mytube/us/upload',formData,config)
      .then(response=>{
      console.log(response);
      // alert(response);
      getThumbnails(response);
      if(response.data[0]!=""){
      toggleModal(false);
         toggleDetailsModal(true);
      }
      else{
    //  alert("failed to upload a video");
      }
      })




  }
  return (
    <div >
      <h2>Upload Video </h2>
      <button onClick={toggleModal}>Upload Video</button>
      <Modal className={`placeholder-preview2`} isOpen={isOpen} contentLabel="Upload Video" style={{ display: 'block', width: 700 , height : 800 , padding: 30 }}>

      <form className={`placeholder-preview1`} style={{ display: 'block', width: '70%' , height : '80%'}}>
      <div className="">

             <h2 style={{'margin-top': '-28px'}}>Upload An Video</h2>
             <button className="main" onClick={toggleModal}>X</button>
</div>

         <div className={`placeholder-preview`}>


             <Dropzone onDrop={acceptedFiles => test(acceptedFiles)}>
                    {({getRootProps, getInputProps}) => (
                      <section>

                        <div {...getRootProps()}>
                        <MdCloudUpload style={{ fontSize: 100, paddingTop: 100, width: 200 , height : 200 }} />
                          <input {...getInputProps()} />
                          <p className="uploadtext">Drag and drop video files to upload</p>
                          <p className="uploadtext2">Your videos will be private until you publish them.</p>
                          <p className="uploadbutton">SELECT FILES</p>
                        </div>
                      </section>
                    )}

                  </Dropzone>
                  <p className="uploadtext1">By submitting your videos to VideoAdda, you acknowledge that you agree to VideoAdda Terms of Service and Community Guidelines.</p>
                  <p className="uploadtext3">Please be sure not to violate others copyright or privacy rights. Learn more </p>
          </div>
       </form>
      </Modal>
      <Modal className={`placeholder-preview2`} isOpen={isDetailsOpen} contentLabel="Upload Video" style={{ display: 'block', width: 700 , height : 800 , padding: 30 }}>
            <button className="main" onClick={toggleDetailsModal}>X</button>
<VideoDetails/>
            </Modal>


    </div>
  );
}