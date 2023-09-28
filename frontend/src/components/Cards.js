import React from 'react'
import "../App.css";

const Cards = ({image, name, age, gender}) => {
  return (
    <div>
        <img src={image} className="App-logo" alt="logo" />
        <div className='card'>Name: {name}</div>
        <div className='card'>Age: {age}</div>
        <div className='card'>Gender: {gender}</div>
    </div>
  )
}

export default Cards