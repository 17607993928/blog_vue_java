import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        token: localStorage.getItem('token'),
        userInfo: JSON.parse(sessionStorage.getItem("userInfo")),
    },
    mutations: {
        //javabean中的set
        SET_TOKEN: (state, token) => {
            state.token = token;
            localStorage.setItem('token', token);
        },
        SET_USERINFO: (state, userInfo) => {
            state.userInfo = userInfo;
            sessionStorage.setItem('userInfo', JSON.stringify(userInfo));
        },
        REMOVE_INFO: (state => {
            state.token = "";
            state.userInfo = "";
            localStorage.removeItem('token');
            sessionStorage.removeItem('userInfo');
        })
    },
    getters: {
        //get
        getUserInfo: state => {
            return state.userInfo;
        },

        getToken: state => {
            return state.token;
        }
    },
    actions: {},
    modules: {}
})
