<template>
	<div class="test-box">
        <div>asdasd</div>
		<grid-drag-layout :width="600" :height="600">
            <grid-drag-item row="1 / 2" col="1 / 2"></grid-drag-item>
            <grid-drag-item row="2 / 3" col="2 / 3"></grid-drag-item>
        </grid-drag-layout>
		<div>{{ username }}</div>
		<button @click="logout()">退出登录</button>
	</div>
</template>

<script>
import DragTool from "@/components/DragTool.vue";
import GridDragLayout from "@/components/GridDragLayout.vue";
import GridDragItem from "@/components/GridDragItem.vue";
export default {
	name: "TestPage",
	components: {
        GridDragLayout,
        GridDragItem,
		DragTool
	},
	data() {
		return {
			username: window.sessionStorage.getItem("name") || "未登录"
		}
	},
    create() {
    },
	methods: {
		logout() {
			window.sessionStorage.removeItem("token");
			window.sessionStorage.removeItem("name");
            this.$router.push('/login');
			this.$message.success('退出成功');
		}
	}
}
</script>

<style scoped lang="less">
.test-box {
	height: 100%;
	width: 100%;
}
</style>