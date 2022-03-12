import React, { useState,useCallback } from "react";
import Modal from "react-modal";
import {useDropzone} from 'react-dropzone'
import Dropzone from 'react-dropzone'
import './Style.css';
import { MdCloudUpload } from "react-icons/md";
import UploadButton from './Uploadbutton';

//npm install react-modal
//npm install --save react-dropzone
export default function UploadVideoDialog() {

  function test(acceptedFiles){
  console.log(acceptedFiles) ;
  const temp=new UploadButton();
temp.setIsOpen(false);

  }
  return (
    <div className="App">

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
  );
}