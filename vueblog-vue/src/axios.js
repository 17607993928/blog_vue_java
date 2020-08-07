import axios from 'axios'
import router from './router'
import store from './store'
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"

//封装请求ip端口
axios.defaults.baseURL = "http://localhost:8081";

//发起请求的前置拦截
axios.interceptors.request.use(config => {
    return config;
});

axios.interceptors.response.use(response => {
        if (response.data.code === "200") {
            return response;
        } else {
            Element.Message.error(response.data.message, {duration: 3 * 1000});
            return Promise.reject(response.data.message);
        }
    },

    error => {
        if(error.response.data){
            error.message=error.response.data.message;
        }
        console.log("----------------axios后置拦截异常----------------------")
        if (error.response.status === "401" || error.response.data.code==="401") {
            store.commit("REMOVE_INFO");
            Element.Message.error(error.message, {duration: 3 * 1000});
            router.push("/login");
        } else  {
            Element.Message.error(error.message, {duration: 3 * 1000});
            return Promise.reject(error);
        }
    }
);

