import axios from "axios"

// create an axios client
const PORT = 8080
const api = axios.create ({
    baseURL: "http://localhost:" + PORT
})

// define an error handler for the axios client
api.interceptors.response.use(
    // any status code within range of 2xx triggers this
    function (response) {
        console.log("API request succesful!")
        return response
    },
    // any status code outside the range 2xx triggers this
    function (error) {
        let res = error.response
        console.error("Looks like there was a problem. Status Code: " + res.status)
         return Promise.reject(error)
    }
  );

// assign axios methods to all endpoints per the API reference document
export const FrontendAPI = {
    // @@@@@@@@@@@@
    // GET methods
    // @@@@@@@@@@@@

    get: async function () {
        const response = await api.request({
            url: `/`,
            method: "GET",
        })
        return response.data
    },

    getLogin: async function () {
        const response = await api.request({
            url: `/login`,
            method: "GET",
        })
        return response.data
    },

    getProfile: async function () {
        const response = await api.request({
            url: `/profile`,
            method: "GET",
        })
        return response.data
    },

    getUser: async function () {
        const response = await api.request({
            url: `/user`,
            method: "GET",
        })
        return response.data
    },

    getUserId: async function (id) {
        const response = await api.request({
            url: `/user/${id}`,
            method: "GET",
        })
        return response.data
    },

    getDashboard: async function () {
        const response = await api.request({
            url: `/dashboard`,
            method: "GET",
        })
        return response.data
    },

    getAdmin: async function () {
        const response = await api.request({
            url: `/admin`,
            method: "GET",
        })
        return response.data
    },

    getEntity: async function () {
        const response = await api.request({
            url: `/entity`,
            method: "GET",
        })
        return response.data
    },

    getEntityId: async function (id) {
        const response = await api.request({
            url: `/entity/${id}`,
            method: "GET",
        })
        return response.data
    },

    getEntityIdFeature: async function (id) {
        const response = await api.request({
            url: `/entity/${id}/feature`,
            method: "GET",
        })
        return response.data
    },

    getEntityIdFeatureKey: async function (id, key) {
        const response = await api.request({
            url: `/entity/${id}/feature/${key}`,
            method: "GET",
        })
        return response.data
    },

    getEntityFeatureKey: async function (key) {
        const response = await api.request({
            url: `/entity/feature/${key}`,
            method: "GET",
        })
        return response.data
    },

    getFeature: async function () {
        const response = await api.request({
            url: `/feature`,
            method: "GET",
        })
        return response.data
    },

    getFeatureKey: async function (key) {
        const response = await api.request({
            url: `/feature/${key}`,
            method: "GET",
        })
        return response.data
    },

    getSearch: async function (id, key) {
        const response = await api.request({
            url: `/search?key=${key}&entity=${id}`,
            method: "GET",
        })
        return response.data
    },
    
    // @@@@@@@@@@@@
    // POST methods
    // @@@@@@@@@@@@
    postUserId: async function (id, body) {
        const response = await api.request({
            url: `/users/${id}`,
            method: "POST",
            data: body
        })
        return response.data;
    },
    
    postEntityId: async function (id, body) {
        const response = await api.request({
            url: `/entity/${id}`,
            method: "POST",
            data: body
        })
    },

    postEntityIdFeature: async function (id, body) {
        const response = await api.request({
            url: `/entity/${id}/feature`,
            method: "POST",
            data: body
        })
    }, 

    postEntityIdFeatureKey: async function (id, key, body) {
        const response = await api.request({
            url: `/entity/${id}/feature/${key}`,
            method: "POST",
            data: body
        })
    }, 

    postEntityFeatureKey: async function (key, body) {
        const response = await api.request({
            url: `/entity/feature/${key}`,
            method: "POST",
            data: body
        })
    },

    postFeature: async function (body) {
        const response = await api.request({
            url: `/feature`,
            method: "POST",
            data: body
        })
    },

    postFeatureID: async function (id, body) {
        const response = await api.request({
            url: `/feature/${id}`,
            method: "POST",
            data: body
        })
    },

    postSearch: async function (id, key, body) {
        const response = await api.request({
            url: `/search?key=${key}&entity=${id}`,
            method: "POST",
            data: body
        })
    },

    // @@@@@@@@@@@@
    // PUT methods
    // @@@@@@@@@@@@
    putUserId: async function (id, body) {
        const response = await api.request({
            url: `/users/${id}`,
            method: "PUT",
            data: body
        })
    },
    
    putEntityId: async function (id, body) {
        const response = await api.request({
            url: `/entity/${id}`,
            method: "PUT",
            data: body
        })
    },

    putEntityIdFeature: async function (id, body) {
        const response = await api.request({
            url: `/entity/${id}/feature`,
            method: "PUT",
            data: body
        })
    }, 

    putEntityIdFeatureKey: async function (id, key, body) {
        const response = await api.request({
            url: `/entity/${id}/feature/${key}`,
            method: "PUT",
            data: body
        })
    }, 

    putEntityFeatureKey: async function (key, body) {
        const response = await api.request({
            url: `/entity/feature/${key}`,
            method: "PUT",
            data: body
        })
    },

    putFeature: async function (body) {
        const response = await api.request({
            url: `/feature`,
            method: "PUT",
            data: body
        })
    },

    putFeatureID: async function (id, body) {
        const response = await api.request({
            url: `/feature/${id}`,
            method: "PUT",
            data: body
        })
    },

    putSearch: async function (id, key, body) {
        const response = await api.request({
            url: `/search?key=${key}&entity=${id}`,
            method: "PUT",
            data: body
        })
    },

    // @@@@@@@@@@@@
    // PATCH methods
    // @@@@@@@@@@@@
    patchUserId: async function (id, body) {
        const response = await api.request({
            url: `/users/${id}`,
            method: "PATCH",
            data: body
        })
    },
    
    patchEntityId: async function (id, body) {
        const response = await api.request({
            url: `/entity/${id}`,
            method: "PATCH",
            data: body
        })
    },

    patchEntityIdFeature: async function (id, body) {
        const response = await api.request({
            url: `/entity/${id}/feature`,
            method: "PATCH",
            data: body
        })
    }, 

    patchEntityIdFeatureKey: async function (id, key, body) {
        const response = await api.request({
            url: `/entity/${id}/feature/${key}`,
            method: "PATCH",
            data: body
        })
    }, 

    patchEntityFeatureKey: async function (key, body) {
        const response = await api.request({
            url: `/entity/feature/${key}`,
            method: "PATCH",
            data: body
        })
    },

    patchFeature: async function (body) {
        const response = await api.request({
            url: `/feature`,
            method: "PATCH",
            data: body
        })
    },

    patchFeatureID: async function (id, body) {
        const response = await api.request({
            url: `/feature/${id}`,
            method: "PATCH",
            data: body
        })
    },

    patchSearch: async function (id, key, body) {
        const response = await api.request({
            url: `/search?key=${key}&entity=${id}`,
            method: "PATCH",
            data: body
        })
    },

    // @@@@@@@@@@@@
    // DELETE methods
    // @@@@@@@@@@@@
    deleteUserId: async function (id) {
        const response = await api.request({
            url: `/users/${id}`,
            method: "DELETE",
        })
    },
    
    deleteEntityId: async function (id) {
        const response = await api.request({
            url: `/entity/${id}`,
            method: "DELETE",
        })
    },

    deleteEntityIdFeature: async function (id) {
        const response = await api.request({
            url: `/entity/${id}/feature`,
            method: "DELETE",
        })
    }, 

    deleteEntityIdFeatureKey: async function (id, key) {
        const response = await api.request({
            url: `/entity/${id}/feature/${key}`,
            method: "DELETE",
        })
    }, 

    deleteEntityFeatureKey: async function (key) {
        const response = await api.request({
            url: `/entity/feature/${key}`,
            method: "DELETE",
        })
    },

    deleteFeature: async function () {
        const response = await api.request({
            url: `/feature`,
            method: "DELETE",
        })
    },

    deleteFeatureID: async function (id) {
        const response = await api.request({
            url: `/feature/${id}`,
            method: "DELETE",
        })
    },

    deleteSearch: async function (id, key) {
        const response = await api.request({
            url: `/search?key=${key}&entity=${id}`,
            method: "DELETE",
        })
    },
}