<template>
    <div>
        <Header></Header>
        <div>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="标题" prop="title">
                    <el-input v-model="ruleForm.title"></el-input>
                </el-form-item>
                <el-form-item label="摘要" prop="description">
                    <el-input v-model="ruleForm.description"></el-input>
                </el-form-item>
                <el-form-item label="内容" prop="content">
                    <mavon-editor v-model="ruleForm.content" class="editor"></mavon-editor>
                </el-form-item>

                <el-form-item class="contents">
                    <el-button type="primary"  @click="submitForm('ruleForm')">提交</el-button>

                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
            </el-form>


        </div>
    </div>
</template>

<script>
    import Header from "../components/Header";

    export default {
        name: "BlogEdit",
        components: {Header},
        data() {
            return {
                ruleForm: {
                    id:'',
                    title: '',
                    description: '',
                    content: '',
                },
                rules: {
                    title: [
                        {required: true, message: '请输入标题', trigger: 'blur'},
                        {min: 3, max: 50, message: '长度在 3 到 50 个字符', trigger: 'blur'}
                    ],
                    description: [
                        {required: true, message: '请输入摘要', trigger: 'blur'},
                        {min: 3, max: 200, message: '长度在 3 到 200 个字符', trigger: 'blur'}
                    ],
                    content: [
                        {required: true, message: '请输入内容', trigger: 'blur'},
                        {min: 3, max: 1000, message: '长度在 3 到 1000 个字符', trigger: 'blur'}
                    ],
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {

                        const _this = this
                        this.$axios.post('/blog/edit', this.ruleForm, {
                            headers: {
                                "Authorization": localStorage.getItem('token'),
                            }
                        }).then(res => {
                            _this.$message({
                                message: '恭喜你，这是一条成功消息',
                                type: 'success'
                            });
                            _this.$router.push('/blogs')
                        })


                    } else {
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        },
        created() {
            const blogId=this.$route.params.blogId;
            const _this=this;
            if(blogId){
                this.$axios.get('/blog/'+blogId).then(res=>{
                    _this.ruleForm=res.data.data;
                })
            }
        }
    }
</script>

<style scoped>
    .demo-ruleForm {
        width: 1200px;
        margin: 0 auto;
    }

    .editor {
        height: 500px;
        margin: 0 auto;
    }

    .contents {
        text-align: center;
    }

</style>