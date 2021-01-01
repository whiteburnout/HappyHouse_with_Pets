import axios from "axios";

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

const reviewStore = {
    namespaced: true,
    state:{ 
        reviews: [],
    },
    mutations: {
        setreviews(state, payload) {
            state.reviews = [];
            state.reviews = payload.review;
            console.log(state.reviews);
        }
    },
    actions: {
        reviewList(context, payload) {
            return axios
                .get(`${SERVER_URL}house/review/${payload}`)
                .then((response) => {context.commit("setreviews", {review : response.data})})
                .catch((error)=> console.log(error))
        },
        reviewRegister(context, payload) {
            return axios
                .post(`${SERVER_URL}house/review`, payload)
                .then((response) => { console.log(response); })
                .catch((error)=> console.log(error))
        },
        reviewDelete(context, payload) { 
            console.log(payload);
            return axios
                .delete(`${SERVER_URL}house/review/${payload}`)
                .then((response) => { console.log(response); })
                .catch((erorr) => { console.log(erorr); })
        },
        reviewModify(context, payload) { 
            return axios
                .put(`${SERVER_URL}house/review`, payload)
                .then((response) => { console.log(response); })
                .catch((erorr) => { console.log(erorr); })
        }
    }
}

export default reviewStore;