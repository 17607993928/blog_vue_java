<template>
    <div>
        <Header></Header>
        <div class="blog">
            <h2 style="text-align: center">{{blog.title}}</h2>
            <el-link icon="el-icon-edit" v-if="isUser">
                <router-link :to="{name:'BlogEdit',params:{blogId:blog.id}}">
                    编辑
                </router-link>
            </el-link>
            <el-divider></el-divider>
            <div v-html="blog.content" class="markdown-body">

            </div>
        </div>

    </div>
</template>

<script>
    import Header from "../components/Header";
    import "github-markdown-css"

    export default {
        name: "BlogDetail",
        components: {Header},
        data() {
            return {
                blog: {
                    id: "",
                    title: "默认标题",
                    content: "默认内容",

                },
                isUser:false,
            }
        },
        created() {
            const blogId = this.$route.params.blogId;
            const _this = this;
            if (blogId) {
                this.$axios.get('/blog/' + blogId).then(res => {
                    _this.blog = res.data.data;

                    //markdown文本回显
                    let MardownIt = require("markdown-it");
                    let md = new MardownIt();
                    let render = md.render(res.data.data.content);
                    _this.blog.content = render;

                    //判断编辑按钮是否是本人展示
                    // _this.isUser=res.data.data.userId===_this.$store.state.userInfo.id
                    _this.isUser=res.data.data.userId===_this.$store.getters.getUserInfo.id
                })
            }
        }
    }
</script>

<style scoped>
    .blog {
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        width: 960px;
        min-height: 700px;
        margin: 0 auto;
        padding: 20px 100px;
    }
</style>