import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { FrontendAPI } from "../api";
const api = FrontendAPI;

const LivestockDetail = () => {
  const { id } = useParams();
  const [animal, setAnimal] = useState(null);
  const [isEditing, setIsEditing] = useState(false);
  const [editedData, setEditedData] = useState({});
  const [isDobEditing, setIsDobEditing] = useState(false); // Track editing state for Date of Birth

  useEffect(() => {
    fetchData();
  }, [id]);

  const fetchData = async () => {
    const data = await api.getAnimalById(id);
    setAnimal(data);
    setEditedData(data);
  };

  const handleEdit = () => {
    setIsEditing(true);
  };

  const handleCancel = () => {
    setIsEditing(false);
    setEditedData(animal);
  };

  const handleEditDob = () => {
    setIsDobEditing(true);
  };

  const handleCancelDob = () => {
    setIsDobEditing(false);
    setEditedData((prevData) => ({
      ...prevData,
      dob: animal.dob,
    }));
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setEditedData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSave = async () => {
    await api.updateAnimalById(id, editedData);
    fetchData();
    setIsEditing(false);
    setIsDobEditing(false);
  };

  return (
    <div className="App-header">
      <h1>Livestock Detail</h1>
      {animal ? (
        <div>
          {isEditing ? (
            <div>
              <span>Name: </span>
              <input
                type="text"
                name="name"
                value={editedData.name}
                onChange={handleInputChange}
              />
            </div>
          ) : (
            <div>
              <span>Name: {animal.name}</span>
            </div>
          )}
          <div>
            <span>Date of Birth: </span>
            {isEditing ? (
              isDobEditing ? (
                <input
                  type="date"
                  name="dob"
                  value={editedData.dob}
                  onChange={handleInputChange}
                />
              ) : (
                <span onClick={handleEditDob}>{editedData.dob}</span>
              )
            ) : (
              <span>{animal.dob}</span>
            )}
            {isDobEditing && (
              <button onClick={handleCancelDob}>Cancel</button>
            )}
          </div>
          <div>
            <span>Weight: </span>
            {isEditing ? (
              <input
                type="number"
                name="weight"
                value={editedData.weight}
                onChange={handleInputChange}
              />
            ) : (
              <span>{animal.weight}</span>
            )}
          </div>
          <div>
            <span>Tag: </span>
            {isEditing ? (
              <input
                type="text"
                name="tag"
                value={editedData.tag}
                onChange={handleInputChange}
              />
            ) : (
              <span>{animal.tag}</span>
            )}
          </div>
          <div>
            <span>Breed: </span>
            {isEditing ? (
              <input
                type="text"
                name="breed"
                value={editedData.breed}
                onChange={handleInputChange}
              />
            ) : (
              <span>{animal.breed}</span>
            )}
          </div>
          {isEditing ? (
            <div>
              <button onClick={handleSave}>Save</button>
              <button onClick={handleCancel}>Cancel</button>
            </div>
          ) : (
            <button onClick={handleEdit}>Edit</button>
          )}
        </div>
      ) : (
        <p>...</p>
      )}
    </div>
  );
};

export default LivestockDetail;