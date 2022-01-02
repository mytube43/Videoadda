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
      <Modal isOpen={isOpen} contentLabel="Upload Video" style={{ display: 'block', width: 500, padding: 30 }}>
      <button onClick={toggleModal}>X</button>
      <form >
        <h1 className="title">Upload An Video</h1>
         <div className={`placeholder-preview`}>
            <MdCloudUpload style={{ fontSize: 100, paddingTop: 70 }} />
             <Dropzone onDrop={acceptedFiles => test(acceptedFiles)}>
                    {({getRootProps, getInputProps}) => (
                      <section>
                        <div {...getRootProps()}>
                          <input {...getInputProps()} />
                          <p className="upload-container">Drag 'n' drop some files here, or click to select files</p>
                        </div>
                      </section>
                    )}
                  </Dropzone>
          </div>
       </form>
      </Modal>
      <Modal isOpen={isDetailsOpen} contentLabel="Upload Video" style={{ display: 'block', width: 500, padding: 30 }}>
            <button onClick={toggleDetailsModal}>X</button>
<VideoDetails/>
            </Modal>


    </div>
  );
}