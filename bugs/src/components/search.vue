<template>
	<div class="search-box">
		<input placeholder="你要什么Bug？" class="search-input"/>
		<div class="icon-box">
			<span class="iconfont icon-sousuo"></span>
		</div>
	</div>
</template>

<script>
export default {};
</script>

<style lang="less" scoped>
.search-box {
	width: 600px;
	height: 50px;
	border-radius: 50px;
	display: flex;
	flex-direction: row;
	background: rgba(255, 255, 255, 0.4);
	box-shadow: 0 0 10px 3px #0000001a;
	backdrop-filter: blur(18px);
	transition: 0.3s;
	input::-webkit-input-placeholder {
		color: #ccc;
		font-weight: 200;
	}
	input::-ms-input-placeholder {
		color: #ccc;
		font-weight: 200;
	}
	
	/* CSS Working Draft */
	input::placeholder {
		color: #ccc;
		font-weight: 200;
	}
}
.search-box:hover {
	background: white;
}
.search-box-border {
	width: 610px;
	height: 60px;
	border-radius: 50px;
	background: linear-gradient(to right, #8f41e9, #578aef);
	display: flex;
	justify-content: center;
	align-items: center;
	box-shadow: 0 0 10px 3px #0000001a;
}
.icon-box {
	color: #888888;
	font-size: 24px;
	width: 50px;
	height: 36px;
	padding-top: 19px;
	border-radius: 50%;
	display: flex;
	justify-content: center;
	align-items: center;
	cursor: pointer;
}
.search-input {
	border: none;
	background: none;
	outline: none;
	padding: 0;
	color: #222;
	font-size: 16px;
	line-height: 50px;
	margin-left: 50px;
	width: 500px;
	font-family: 微软雅黑 serif;
}

</style>
