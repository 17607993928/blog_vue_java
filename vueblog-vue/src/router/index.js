import Vue from 'vue'
import VueRouter from 'vue-router'

/*配置路由*/
import Login from '../views/Login.vue'
import BlogDetail from '../views/BlogDetail.vue'
import BlogEdit from '../views/BlogEdit.vue'
import Blogs from '../views/Blogs.vue'


Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Index',
        redirect: {name: "Login"}
    },
    {
        path: '/blogs',
        name: 'Blogs',
        component: Blogs
    },
    {
        path: '/blog/add',
        name: 'BlogAdd',
        component: BlogEdit,
        meta:{
            requireAuth:true,
        }
    },
    {
        path: '/blog/:blogId',
        name: 'BlogDetail',
        component: BlogDetail
    },
    {
        path: '/blog/:blogId/edit',
        name: 'BlogEdit',
        component: BlogEdit,
        meta:{
            requireAuth:true,
        }
    },

    {
        path: '/login',
        name: 'Login',
        component: Login
    },

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
