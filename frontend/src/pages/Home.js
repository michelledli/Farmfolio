import sawyer_goat_1 from './sawyer_goat_1.jpg';
import { useState } from 'react';
import Cards from '../components/Cards';

export function Home() {
  const Images = [sawyer_goat_1, sawyer_goat_1, sawyer_goat_1, sawyer_goat_1, sawyer_goat_1, sawyer_goat_1, sawyer_goat_1,]
  const SCROLL_SPEED = 0.3;




    return (
      <div className='App-header'>
                <br></br>
                <br></br>
        <h1>About</h1>
          <div className='about'>Explore Sawyer's Farm Goat Catalog at Bret Harte High School:
Located at Bret Harte High School, Sawyer's Farm is a unique endeavor cared for by both students and faculty. Our catalog offers a range of high-quality goats and supplies, and we take pride in practicing responsible farming. Whether you're a student learning about agriculture or a faculty member with a passion for goats, Sawyer's Farm is here to support your goat-keeping journey. Join our school community in fostering a love for sustainable farming with Sawyer's Farm.</div>
        <div className='scroll-container'>
            { Images.map((image) => <Cards image={image} name='sawyer' age='2' gender='Male' />)}
        </div>
        <div>
        </div>
      </div>
    );
  }