import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import axios from 'axios';
import { FrontendAPI } from '../api';

function LivestockDetail() {
  const { id } = useParams();
  const [livestock, setLivestock] = useState(null);
  const [isEditing, setIsEditing] = useState(false);
  const [editedData, setEditedData] = useState({});
  const [selectedFile, setSelectedFile] = useState(null);
  const [imageData, setImageData] = useState(null);

  const date = new Date(editedData.dob);
  const formattedDate = `${date.getMonth() + 1}-${date.getDate()}-${date.getFullYear()}`;

  useEffect(() => {
    async function fetchData() {
      let response = await FrontendAPI.getAnimalById(id);
      setLivestock(response);
      setEditedData(response);
    }
  
    fetchData();
  }, [id]);

  const handleEdit = () => {
    setIsEditing(true);
  };

  const handleSave = () => {
    // Make an Axios request to update the data on the backend
    if (selectedFile) handleImageUpload();
    axios.put(`/api/animals/${id}`, editedData)
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

  const handleFatherInput = (e) => {
    const { name, value } = e.target;
    setEditedData((prevData) => ({
      ...prevData,
      father: {
        ...prevData.father,
        id: value,
      },
    }));

    setTimeout(() => {
      window.location.reload();
    }, 2000);
  }

  const handleMotherInput = (e) => {
    const { name, value } = e.target;
    setEditedData((prevData) => ({
      ...prevData,
      mother: {
        ...prevData.mother,
        id: value,
      },
    }));

    setTimeout(() => {
      window.location.reload();
    }, 2000);
  }

  const handleFileChange = (e) => {
    setSelectedFile(e.target.files[0]);
  };

  const handleImageUpload = () => {
    const formData = new FormData();
    formData.append("image", selectedFile);

    axios.post(`/api/images/${id}`, formData)
        .then((response) => {
          console.log('Image uploaded successfully!');
        })
        .catch((error) => {
          console.error('Image upload failed:', error);
        });

    setTimeout(() => {
      window.location.reload();
    }, 2000);
  };

  const handleCancel = () => {
    setEditedData(livestock);
    setIsEditing(false);
  };

  useEffect(() => {
    if (editedData.imageId) {
      axios.get(`/api/images/${editedData.imageId}`).then((response) => {
        setImageData(response.data)
      });
    }
  }, [editedData.imageId]);

  if (!livestock) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h1>{editedData.name}</h1>
      <div className="center-detail-image">
        {imageData ? (
            <img
                src={`data:image/png;base64,${imageData}`}
                alt="Uploaded Image"
            />
        ) : (
            <span>No image available</span>
        )}
      </div >
      <div>
        <div>
          {isEditing ? (
                  <div>
                    <span className="detail-fields-title">Image Upload: </span>
                    <input type="file" onChange={handleFileChange} />
                  </div>)
              : <span> </span>}

        </div>
      </div>
        <div >

        <div>
          <span className="detail-fields-title">ID:</span> {editedData.id}
        </div>
        <div >
          <span className="detail-fields-title">Name: </span>
            {isEditing ? (
                <input
                type="text"
                name="name"
                value={editedData.name}
                onChange={handleInputChange}
                readOnly={!isEditing}
            />) : (
                <span>{editedData.name}</span>
            )}
        </div>
        <div>
          <span className="detail-fields-title">Sex: </span>
          {isEditing ? (
              <input
              type="text"
              name="sex"
              value={editedData.sex}
              onChange={handleInputChange}
              readOnly={!isEditing}
          />) :
          (
              <span>{editedData.sex}</span>
          )}

        </div>
        <div className="detail-fields">
          <span className="detail-fields-title">Date of Birth: </span>
          {isEditing ? (
              <input
              type="text"
              name="dob"
              value={formattedDate}
              onChange={handleInputChange}
              readOnly={!isEditing}
          />) : (
              <span>{formattedDate}</span>)
          }

        </div>
        <div className="detail-fields">
          <span className="detail-fields-title">Weight: </span>
          {isEditing ? (
            <input
            type="text"
            name="weight"
            value={editedData.weight}
            onChange={handleInputChange}
            readOnly={!isEditing}
            />) : (
                <span>{editedData.weight} lbs</span>
          )}

        </div>
        <div>
          <span className="detail-fields-title">Tag: </span>
          {isEditing ? (
              <input
              type="text"
              name="tag"
              value={editedData.tag}
              onChange={handleInputChange}
              readOnly={!isEditing}
          />) :(
              <span>{editedData.tag}</span>
          )}
        </div>
        <div>
          <span  className="detail-fields-title">Breed: </span>
          {isEditing ? (
              <input
              type="text"
              name="breed"
              value={editedData.breed}
              onChange={handleInputChange}
              readOnly={!isEditing}
          />) : (
              <span>{editedData.breed}</span>
          )}

        </div>
        <div >
          <span className="detail-fields-title">Notes: </span>
          {isEditing ? (
              <input
              type="text"
              name="notes"
              value={editedData.notes}
              onChange={handleInputChange}
              readOnly={!isEditing}/> ) :
              (
                  <span>{editedData.notes}</span>
              )
          }
        </div>

        <div>
          <span className="detail-fields-title">Father: </span>
          {!isEditing ? (
              editedData.father && editedData.father.id !== null ? (
                  <Link to={`/livestock/${editedData.father.id}`}>{editedData.father.name}</Link>
              ) : (
                  "No father"
              )
          ) : (
              <div>
                <span>Enter new father id: </span>
                <input
                    type="text"
                    name="id"
                    value={editedData.father && editedData.father.id !== null ? editedData.father.id : ""} // Use optional chaining here
                    onChange={handleFatherInput}
                    readOnly={!isEditing}
                />
              </div>
          )}
        </div>

        <div>
          <span className="detail-fields-title">Mother: </span>
          {!isEditing ? (
              editedData.mother && editedData.mother.id !== null ? (
                  <Link to={`/livestock/${editedData.mother.id}`}>{editedData.mother.name}</Link>
              ) : (
                  "No mother"
              )
          ) : (
              <div>
                <span>Enter new mother id: </span>
                <input
                    type="text"
                    name="id"
                    value={editedData.mother && editedData.mother.id !== null ? editedData.mother.id : ""} // Use optional chaining here
                    onChange={handleMotherInput}
                    readOnly={!isEditing}
                />
              </div>
          )}
        </div>
      </div>
      <div>
        {isEditing ? (
          <div>
            <button onClick={handleSave}>Save</button>
            <button onClick={handleCancel}>Cancel</button>
          </div>
        ) : (
          <button onClick={handleEdit}>Edit</button>
        )}
      </div>
      <Link to={`/livestock`}>Back to livestock</Link>
    </div>
  );
}

export default LivestockDetail;
