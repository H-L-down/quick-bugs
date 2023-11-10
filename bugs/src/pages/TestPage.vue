<template>
	<div class="test-box">
		<DragTool :placement="'bottom'"></DragTool>
		<div>
			adasdasd
		</div>
	</div>
</template>

<script>
import DragTool from "@/components/DragTool.vue";
export default {
	name: "TestPage",
	components: {
		DragTool
	}
}
</script>

<style scoped lang="less">
.test-box {
	height: 100%;
	width: 100%;
}
</style>