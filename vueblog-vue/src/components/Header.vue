<template>
    <div class="content">
        <h3>欢迎来到大佬的博客</h3>
        <div class="block">
            <el-avatar :size="50" v-bind:src="user.avatar"></el-avatar>
            <div v-text="user.username"></div>
        </div>
        <div class="maction">
            <span><el-link type="primary" href="/blogs">主页</el-link></span>
            <el-divider direction="vertical"></el-divider>
            <span><el-link type="success" href="/blog/add">发表文章</el-link></span>
            <el-divider direction="vertical"></el-divider>

            <span v-if="!isLogin"><el-link type="info" href="/login">登录</el-link></span>

            <span v-if="isLogin"><el-link type="danger" @click="logout()">退出</el-link></span>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Header",
        data() {
            return {
                user: {
                    username: '请先登录',
                    avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',

                },
                isLogin:false,
            }
        },
        methods: {
            logout() {
                const _this=this;
                this.$axios.get('/account/logout',{
                    headers: {
                        "Authorization":localStorage.getItem('token'),
                    }
                }).then(res=>{
                    _this.$store.commit("REMOVE_INFO");
                    _this.$router.push('/login');
                })

            },
        },
        //页面加载事件
        created() {
            if(this.$store.getters.getUserInfo){
                this.user.username=this.$store.getters.getUserInfo.username;
                this.user.avatar=this.$store.getters.getUserInfo.avatar;

                //判断登录
                this.isLogin=true;
            };
        }

    }
</script>

<style scoped>
    .content {
        margin: 0 auto;
        max-width: 960px;
        text-align: center;
    }

    .maction {
        margin: 10px;

    }
</style>