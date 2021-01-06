import axios from "axios";

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

const boardStore = {
    namespaced: true,
    state: {
        items: [],
        item: {},
    },
    getters: {
        getItems(state) { return state.items; },
        getItem(state) { return state.item; },
    },
    mutations: {
        setItems(state, payload) {
            state.items = payload;
        },
        setItem(state, payload) {
            state.item = payload;
        },
    },
    actions: {
        reqGeneralList(context) {
            return axios
            .get(`${SERVER_URL}board/general`)
            .then((response) => {
                context.commit("setItems", response.data);
            })
        },
        reqNoticeList(context) {
            return axios
            .get(`${SERVER_URL}board/notice`)
            .then((response) => {
                context.commit("setItems", response.data);
            })
        },
        // insert
        reqInsertList(context, item) {
            return axios
                .post(`${SERVER_URL}board`, item)
                .then((response) => {
                    return response.data;
                })
        },
        // selectOne
        reqItem(context, no) {
            return axios
                .get(`${SERVER_URL}board/item/` + no)
                .then((response) => {
                    context.commit("setItem", response.data);
                })
        },
        // modify
        reqModify(context, item) {
            return axios
                .put(`${SERVER_URL}board`, item)
                .then((response => { 
                    return response.data;
                }))
        },
        // delete
        reqDelete(context, no) {
            return axios
                .delete(`${SERVER_URL}board/` + no)
                .then((response) => {
                    return response.data;
                })
        }
    }
}

export default boardStore;