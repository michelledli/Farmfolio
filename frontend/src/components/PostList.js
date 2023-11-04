import React from "react";
import { Link } from "react-router-dom";
import Post from "./Post";
import axios from "axios";

const PostList = ({posts}) => {
    console.log("Posts in PostList:", posts);

    const getComments = (postId) => {
      // axios.get("api/comments/posts", {
      //   params: {id: postId}
      // })
      // .then(response => {
      //   return response.data
      // }).catch(function (error) {
      //   console.log(error)
      // });
     return  [
        {
            "createdBy": "Harry Potter",
            "createdDate": "2023-11-03T01:53:22.483+00:00",
            "modifiedBy": "Harry Potter",
            "modifiedDate": "2023-11-03T01:53:22.483+00:00",
            "id": 2,
            "postId": 2,
            "accountId": 192,
            "body": "This is a comment for Post #2",
            "createdAt": null
        }
    ]
    }

    
  return (
    <div className="App-header">
      <h2>Posts</h2>
      <ul>
        {posts.map((post, index) => (
        post.announcement == false && <Post post={post} replies={getComments(post.id)} key={post.id}/>
        ))}
      </ul>
    </div>
  );
};

export default PostList;
