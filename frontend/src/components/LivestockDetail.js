import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

function LivestockDetail() {
  const { id } = useParams();
  const [livestock, setLivestock] = useState(null);
  const [isEditing, setIsEditing] = useState(false);
  const [editedData, setEditedData] = useState({});

  useEffect(() => {
    axios.get(`/api/animals/${id}`)
      .then(response => {
        setLivestock(response.data);
        setEditedData(response.data);
      })
      .catch(error => {
        console.error('API Request Error:', error);
      });
  }, [id]);

  const handleEdit = () => {
    setIsEditing(true);
  };

  const handleSave = () => {
    // Make an Axios request to update the data on the backend
    axios.put(`/update/${id}`, editedData)
      .then(response => {
        console.log('Data updated:', response.data);
        setIsEditing(false);
      })
      .catch(error => {
        console.error('API Request Error:', error);
      });
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setEditedData({ ...editedData, [name]: value });
  };

  if (!livestock) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h1>Livestock Detail</h1>
      <div>
        <div>
          <span>Name: </span>
          <input
            type="text"
            name="name"
            value={editedData.name}
            onChange={handleInputChange}
            readOnly={!isEditing}
          />
        </div>
        <div>
          <span>Sex: </span>
          <input
            type="text"
            name="sex"
            value={editedData.sex}
            onChange={handleInputChange}
            readOnly={!isEditing}
          />
        </div>
        <div>
          <span>Date of Birth: </span>
          <input
            type="text"
            name="dob"
            value={editedData.dob}
            onChange={handleInputChange}
            readOnly={!isEditing}
          />
        </div>
        <div>
          <span>Weight: </span>
          <input
            type="text"
            name="weight"
            value={editedData.weight}
            onChange={handleInputChange}
            readOnly={!isEditing}
          />
        </div>
        <div>
          <span>Tag: </span>
          <input
            type="text"
            name="tag"
            value={editedData.tag}
            onChange={handleInputChange}
            readOnly={!isEditing}
          />
        </div>
        <div>
          <span>Breed: </span>
          <input
            type="text"
            name="breed"
            value={editedData.breed}
            onChange={handleInputChange}
            readOnly={!isEditing}
          />
        </div>
        <div>
          <span>Notes: </span>
          <input
            type="text"
            name="notes"
            value={editedData.notes}
            onChange={handleInputChange}
            readOnly={!isEditing}
          />
        </div>
      </div>
      <div>
        {isEditing ? (
          <button onClick={handleSave}>Save</button>
        ) : (
          <button onClick={handleEdit}>Edit</button>
        )}
      </div>
    </div>
  );
}

export default LivestockDetail;
