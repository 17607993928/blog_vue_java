<template>
    <div>
        <el-container>
            <el-header></el-header>
            <el-main>
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="账号" prop="username">
                        <el-input v-model="ruleForm.username"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input v-model="ruleForm.password" type="password"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
                        <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                </el-form>


            </el-main>
            <el-footer></el-footer>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                ruleForm: {
                    username: 'admin',
                    password: '111111',
                },
                rules: {
                    username: [
                        {required: true, message: '请输入账号', trigger: 'blur'},
                        {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'change'}
                    ],
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        const vue_this = this;
                        this.$axios.post('/account/login', this.ruleForm).then(res => {
                            //获取token
                            const token = res.headers['authorization'];
                            const userInfo = res.data.data;

                            //信息存入vue全局中
                            vue_this.$store.commit("SET_TOKEN", token);
                            vue_this.$store.commit("SET_USERINFO", userInfo);


                            //登录成功跳转页面(全局异常已经处理，所以此处不需要判断)
                            vue_this.$router.push('/blogs')

                        }).catch(err => {

                        })


                    } else {
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>


<style>
    .el-header, .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 100px;
    }


    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        line-height: 160px;
    }

    .demo-ruleForm {
        width: 500px;
        margin: 0 auto;


    }


</style>