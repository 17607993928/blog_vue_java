<template>
    <div>
        <Header></Header>
        <div class="block">
            <el-timeline>
                <el-timeline-item v-bind:timestamp="blog.created" placement="top" v-for="(blog,indxe) in blog">
                    <el-card>
                        <router-link :to="{name:'BlogDetail',params:{blogId:blog.id}}" class="underscore">
                            <h4>{{blog.title}}</h4>
                        </router-link>
                        <p>{{blog.description}}</p>
                    </el-card>
                </el-timeline-item>
            </el-timeline>


            <el-pagination class="page"
                           prev-text="上一页"
                           next-text="下一页"
                           @size-change=handleSizeChange
                           @current-change=handleCurrentChange
                           :background="true"
                           :current-page="currentPage"
                           :page-size="pageSize"
                           :total="total"
                           :page-sizes="[3, 6, 9, 12]"
                           layout="total, sizes, prev, pager, next, jumper">
            </el-pagination>
        </div>

    </div>
</template>

<script>
    import Header from "../components/Header";

    export default {
        name: "Blogs",
        components: {Header},
        data() {
            return {
                blog: {},
                currentPage: 1,
                total: 0,
                pageSize: 3,
            }
        },
        methods: {

            getData(currentPage, pageSize) {
                const _this = this;
                this.$axios.get('/blog?currentPage=' + currentPage + '&size=' + pageSize)
                    .then(res => {
                        _this.blog = res.data.data.records;
                        _this.total = res.data.data.total;
                    })
            },

            handleSizeChange(size) {
                // 改变每页显示的条数
                this.pageSize = size
                // 点击每页显示的条数时，显示第一页
                this.getData(1, size)
                // 注意：在改变每页显示的条数时，要将页码显示到第一页
                this.currentPage = 1
            },
            // 显示第几页
            handleCurrentChange(currentPage) {
                // 改变默认的页数
                this.currentPage = currentPage
                // 切换页码时，要获取每页显示的条数
                this.getData((currentPage), this.pageSize)
            },


        },
        created() {
            this.getData(this.currentPage, this.pageSize);
        }

    }
</script>

<style scoped>
    .page {
        margin: 0 auto;
        text-align: center;
    }

    .underscore {
        text-decoration: none;
        color: #F08859;
    }


</style>