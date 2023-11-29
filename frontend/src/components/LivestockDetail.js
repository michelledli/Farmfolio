import React, { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import axios from "axios";
import { FrontendAPI } from "../api";

function LivestockDetail() {
  const { id } = useParams();
  const [livestock, setLivestock] = useState(null);
  const [isEditing, setIsEditing] = useState(false);
  const [editedData, setEditedData] = useState({});
  const [selectedFile, setSelectedFile] = useState(null);
  const [imageData, setImageData] = useState(null);

  const date = new Date(editedData.dob);
  const formattedDate = `${
    date.getMonth() + 1
  }-${date.getDate()}-${date.getFullYear()}`;

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
    axios
      .put(`/api/animals/${id}`, editedData)
      .then((response) => {
        console.log("Data updated:", response.data);
        setIsEditing(false);
      })
      .catch((error) => {
        console.error("API Request Error:", error);
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
  };

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
  };

  const handleFileChange = (e) => {
    setSelectedFile(e.target.files[0]);
  };

  const handleImageUpload = () => {
    const formData = new FormData();
    formData.append("image", selectedFile);

    axios
      .post(`/api/images/${id}`, formData)
      .then((response) => {
        console.log("Image uploaded successfully!");
      })
      .catch((error) => {
        console.error("Image upload failed:", error);
      });

    setTimeout(() => {
      window.location.reload();
    }, 2000);
  };

  const handleCancel = () => {
    setEditedData(livestock);
    setIsEditing(false);
  };

  const handleDelete = () => {
    FrontendAPI.deleteAnimal(id);
    window.location.href = "/livestock";
  };

  useEffect(() => {
    if (editedData.imageId) {
      axios.get(`/api/images/${editedData.imageId}`).then((response) => {
        setImageData(response.data);
      });
    }
  }, [editedData.imageId]);

  if (!livestock) {
    return <div>Loading...</div>;
  }

  return (
    <>
      <div className="page__header">Livestock Details</div>
      <div className="detail">
        {!isEditing ? (
          <div>
            {imageData ? (
              <img
                src={`data:image/png;base64,${imageData}`}
                alt="Uploaded Image"
              />
            ) : (
              <>No image available</>
            )}
          </div>
        ) : null}
        <table className="detail__table">
          {isEditing ? (
            <tr>
              <td>Image Upload:</td>
              <td>
                <input type="file" onChange={handleFileChange} />
              </td>
            </tr>
          ) : null}
          <tr>
            <td>ID:</td>
            <td>{editedData.id}</td>
          </tr>
          <tr>
            <td>Name:</td>
            <td>
              {isEditing ? (
                <input
                  type="text"
                  name="name"
                  value={editedData.name}
                  onChange={handleInputChange}
                  readOnly={!isEditing}
                />
              ) : (
                editedData.name
              )}
            </td>
          </tr>
          <tr>
            <td>Sex:</td>
            <td>
              {isEditing ? (
                <input
                  type="text"
                  name="sex"
                  value={editedData.sex}
                  onChange={handleInputChange}
                  readOnly={!isEditing}
                />
              ) : (
                editedData.sex
              )}
            </td>
          </tr>
          <tr>
            <td>Date of Birth:</td>
            <td>
              {isEditing ? (
                <input
                  type="text"
                  name="dob"
                  value={formattedDate}
                  onChange={handleInputChange}
                  readOnly={!isEditing}
                />
              ) : (
                formattedDate
              )}
            </td>
          </tr>
          <tr>
            <td>Weight:</td>
            <td>
              {isEditing ? (
                <input
                  type="text"
                  name="weight"
                  value={editedData.weight}
                  onChange={handleInputChange}
                  readOnly={!isEditing}
                />
              ) : (
                editedData.weight
              )}
            </td>
          </tr>
          <tr>
            <td>Tag:</td>
            <td>
              {isEditing ? (
                <input
                  type="text"
                  name="tag"
                  value={editedData.tag}
                  onChange={handleInputChange}
                  readOnly={!isEditing}
                />
              ) : (
                editedData.tag
              )}
            </td>
          </tr>
          <tr>
            <td>Breed:</td>
            <td>
              {isEditing ? (
                <input
                  type="text"
                  name="breed"
                  value={editedData.breed}
                  onChange={handleInputChange}
                  readOnly={!isEditing}
                />
              ) : (
                editedData.breed
              )}
            </td>
          </tr>
          <tr>
            <td>Notes:</td>
            <td>
              {isEditing ? (
                <input
                  type="text"
                  name="notes"
                  value={editedData.notes}
                  onChange={handleInputChange}
                  readOnly={!isEditing}
                />
              ) : (
                editedData.notes
              )}
            </td>
          </tr>
          <tr>
            <td>Father:</td>
            <td>
              {!isEditing ? (
                editedData.father && editedData.father.id !== null ? (
                  <Link to={`/livestock/${editedData.father.id}`}>
                    {editedData.father.name}
                  </Link>
                ) : (
                  "No father"
                )
              ) : (
                <>
                  <input
                    type="text"
                    name="id"
                    placeholder="Father ID"
                    value={
                      editedData.father && editedData.father.id !== null
                        ? editedData.father.id
                        : ""
                    } // Use optional chaining here
                    onChange={handleFatherInput}
                    readOnly={!isEditing}
                  />
                </>
              )}
            </td>
          </tr>
          <tr>
            <td>Mother: </td>
            <td>
              {!isEditing ? (
                editedData.mother && editedData.mother.id !== null ? (
                  <Link to={`/livestock/${editedData.mother.id}`}>
                    {editedData.mother.name}
                  </Link>
                ) : (
                  "No mother"
                )
              ) : (
                <>
                  <input
                    type="text"
                    name="id"
                    placeholder="Mother ID"
                    value={
                      editedData.mother && editedData.mother.id !== null
                        ? editedData.mother.id
                        : ""
                    } // Use optional chaining here
                    onChange={handleMotherInput}
                    readOnly={!isEditing}
                  />
                </>
              )}
            </td>
          </tr>
        </table>
        {isEditing ? (
          <div>
            <button className="detail__button detail__cancel" onClick={handleCancel}>
              Cancel
            </button>
            <button className="detail__button detail__save" onClick={handleSave}>
              Save
            </button>
          </div>
        ) : (
          <div>
          <button className="detail__button detail__edit" onClick={handleEdit}>
            Edit
          </button>
          <button className="detail__button detail__delete" onClick={() => {if(window.confirm("You are about to permanently delete this animal!")){handleDelete()};}}>
            Delete
          </button>
          </div>
        ) }
      </div>
    </>
  );
}

export default LivestockDetail;
