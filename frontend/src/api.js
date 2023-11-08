import axios from "axios";
import Cookies from "universal-cookie";

const cookies = new Cookies();

// create an axios client
const PORT = 8080;
const api = axios.create({
  headers: {
    "Content-Type": "application/json",
    // Authorization: "Bearer ".concat(cookies.get("access_token")),
  },
  //baseURL: `http://localhost:${PORT}`
});

// define an error handler for the axios client
api.interceptors.response.use(
  // any status code within range of 2xx triggers this
  function (response) {
    console.log("API request succesful!");
    return response;
  },
  // any status code outside the range 2xx triggers this
  function (error) {
    let res = error.response;
    console.error("Looks like there was a problem. Status Code: " + res.status);
    return Promise.reject(error);
  }
);

// assign axios methods to all endpoints per the API reference document
export const FrontendAPI = {
  // @@@@@@@@@@@@
  // GET methods
  // @@@@@@@@@@@@
  request: async function (parameters) {
    return api.request(parameters);
  },

  get: async function () {
    const response = await api.request({
      url: `/`,
      method: "GET",
    });

    return response.data;
  },

  getAccounts: async function() {
    const response = await api.request({
      url: "/api/accounts",
      method: "GET"
    })

    return response.data;
  },

  getAccountsMe: async function() {
    const response = await api.request({
      url: "/api/accounts/me",
      method: "GET"
    })

    return response.data;
  },

  getSearchByNameOrTag: async function (query) {
    const response = await api.request({
      url: `/api/animals/search?query=${query}`,
      method: "GET",
    });

    return response.data;
  },

  getAnimalById: async function (id) {
    const response = await api.request({
      url: `/api/animals/${id}`,
      method: "GET",
    });
    return response.data;
  },

  getAnimal: async function () {
    const response = await api.request({
      url: `/api/animals`,
      method: "GET",
    });
    return response.data;
  },

  // getAdmin: async function () {
  //   const response = await api.request({
  //     url: `/admin`,
  //     method: "GET",
  //   });
  //   return response.data;
  // },

  getAudit: async function () {
    const response = await api.request({
      url: "/api/audit",
      method: "GET"
    })
    
    return response.data;
  },

  getPosts: async function () {
    const response = await api.request({
      url: `api/posts`,
      method: "GET",
    });
    return response.data;
  },

  // @@@@@@@@@@@@
  // POST methods
  // @@@@@@@@@@@@

  postLogin: async function (body) {
    const response = await api.request({
      url: `/api/login`,
      method: "POST",
      data: body,
    });

    return response;
  },

  postAnimal: async function (animal) {
    const response = await api.request({
      url: `/api/animals`,
      method: "POST",
      data: {
        name: animal.name,
        dob: animal.birthDate,
        weight: animal.weight,
        tag: animal.tag,
        breed: animal.breed,
        notes: animal.notes,
      },
    });

    return response;
  },

  postComment: async function (post_id, body) {
    const response = await api.request({
      url: `/api/comments`,
      method: "POST",
      data: {
        body: body,
        postId: post_id,
      },
    });

    return response;
  },

  postPost: async function (post) {
    const response = await api.request({
      url: `/api/posts`,
      method: "POST",
      data: { body: post.body, title: post.title, announcement: post.a },
    });

    return response;
  },

  postRegister: async function (body) {
    const repsonse = await api.request({
      url: `/api/register`,
      method: "POST",
      data: body,
    });
  },

  // @@@@@@@@@@@@
  // PUT methods
  // @@@@@@@@@@@@

  // @@@@@@@@@@@@
  // PATCH methods
  // @@@@@@@@@@@@

  // @@@@@@@@@@@@
  // DELETE methods
  // @@@@@@@@@@@@

  deletePost: async function (id) {
    const response = await api.request({
      url: `/api/posts/${id}`,
      method: "DELETE",
    });
  },

  deleteComment: async function (id) {
    const response = await api.request({
      url: `/api/comments/${id}`,
      method: "DELETE",
    });
  },

  deleteAccount: async function (id) {
    const response = await api.request({
      url: `/api/accounts/${id}`,
      method: "DELETE"
    })
  },
};
