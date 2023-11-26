<template>
	<div class="login-container">
		<div class="login-wrapper">
			<div class="opera-area" :class="mode ? 'opera-area-left' : 'opera-area-right'">
				<div class="opera opera-sign-in" v-if="mode">
					<h2 class="opera-title">Login</h2>
					<input type="text" placeholder="count" v-model="userId">
					<input type="password" placeholder="password" v-model="password">
					<h3 class="forget" @click="forgetPassword">
                        <span>
                            Forgot your password?
                        </span>
                    </h3>
                    <div class="button-wrapper">
	    				<button class="button-exec" @click="login">SIGN IN</button>
                        <button class="button-shift" @click="modeShift">SIGN UP</button>
                    </div>
				</div>
				<div class="opera opera-sign-up" v-else>
					<h2 class="opera-title">Registered Account</h2>
					<input type="text" placeholder="user" v-model="createUserId">
					<input type="password" placeholder="password" v-model="createUserPassword">
                    <div class="button-wrapper">
					    <button class="button-exec" @click="register">CREATE</button>
                        <button class="button-shift" @click="modeShift">SIGN IN</button>
                    </div>
				</div>
			</div>
			<div class="desc-area" :class="mode ? 'desc-area-right' : 'desc-area-left'">
				<div class="desc desc-go-sign-in" v-if="mode">
					<h2 class="desc-title">Welcome back</h2>
					<p class="desc-desc">
						We've all missed you
					</p>
				</div>
				<div class="desc desc-go-sign-up" v-else>
					<h2 class="desc-title">Hello,My Friend</h2>
					<p class="desc-desc">
                        Welcome to join us, let's get started
                    </p>
				</div>
			</div>
		</div>
	</div>
</template>
 
<script>
import { login, register } from '@/api/login.js';

export default {
    name: 'Login',
	data() {
		return {
            userId: '',
            password: '',
            createUserId: '',
            createUserPassword: '',
			mode: true // true为login,false为register
		}
	},
    created() {},
	methods: {
        modeShift() {
            this.mode = !this.mode;
        },
        login() {
            login({
                id: this.userId,
                password: this.password
            })
                .then(res => {
                    window.sessionStorage.setItem('token', res.data.token);
                    window.sessionStorage.setItem('name', res.data.name);
                    this.$message.success('登录成功');
                    this.$router.push('/');
                })
                .catch(err => {
                    console.log('发生了错误： ', err);
                })
        },
        register() {
            console.log("注册");
            register({
                id: this.createUserId,
                password: this.createUserPassword
            }) 
                .then(res => {
                    this.$message.success(res.msg);
                    this.userId = this.createUserId;
                    this.createUserId = '';
                    this.password = this.createUserPassword;
                    this.createUserPassword = '';
                    this.modeShift();
                })
                .catch(err => {
                    this.$message.error(err.msg);
                })
        },
        forgetPassword() {
            console.log("忘记密码");
        }
	}
}
</script>

<style lang="less" scoped>
.login-container {
    width: 100%;;
    height: 100%;
    background-image: url('@/assets/img/bg.png');
    background-size: 100% 100%;
    background-repeat: no-repeat;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
}

.login-wrapper {
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
    width: 768px;
    max-width: 100%;
    min-width: 768px;
    min-height: 480px;
    display: flex;
    background: -webkit-linear-gradient(right, #4284db, #29eac4);
}

// 操作
.opera-area {
    width: 50%;
    height: 100%;
    background-color: #ecf3f5;
    display: flex;
    align-items: center;
    justify-content: center;
    transition: transform 0.6s ease-in-out;
    .opera {
        width: 60%;
        height: 100%;
        display: flex;
        // align-items: center;
        justify-content: center;
        flex-direction: column;
        .opera-title {
            font-size: 25px;
            color: black;
            text-align: center;
        }
        input {
            width: 100%;
            background-color: #eee;
            border: none;
            padding: 12px 15px;
            margin: 10px 0;
            outline: none;
        }
        .forget {
            span {
                font-size: 12px;
                -webkit-user-select: none;
                &:hover {
                    color:#4284db;
                }
            }
        }
        .button-wrapper {
            margin-top: 10px;
            button {
                width: 100px;
                height: 35px;
                line-height: 35px;
                border-radius: 20px;
                border: none;
                font-size: 16px;
                color: #fff;
                text-align: center;
                margin-right: 10px;
                cursor: pointer;
            }
            .button-exec {
                background-color: #29eac4;
            }
            .button-shift {
                background-color: #fff;
                color: black;
            }
        }
    }
}
.opera-area-right {
    border-radius: 0px 10px 10px 0px;
    transform: translateX(100%);
}
.opera-area-left {
    border-radius: 10px 0 0 10px;
    transform: translateX(0%);
}

// 描述
.desc-area {
    width: 50%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0);
    display: flex;
    align-items: center;
    justify-content: center;
    transition: transform 0.6s ease-in-out;
    .desc {
        width: 80%;
        height: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
        flex-direction: column;
        .desc-title {
            font-size: 30px;
            color: #fff;
            margin-top: 20px;
        }
        .desc-desc {
            font-size: 15px;
            color: #fff;
            margin-top: 20px;
        }
    }
}

.desc-area-right {
    border-radius: 0px 10px 10px 0px;
    transform: translateX(0%);
}

.desc-area-left {
    border-radius: 0px 10px 10px 0px;
    transform: translateX(-100%);
}

// h3{
//     font-size: 10px;
//     margin-top: 10px;
//     cursor: pointer;
// }
</style>