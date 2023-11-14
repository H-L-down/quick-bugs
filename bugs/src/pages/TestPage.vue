<template>
	<div class="test-box">
		<grid-drag-layout :width="600" :height="600">
            <grid-drag-item></grid-drag-item>
        </grid-drag-layout>
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
    create() {

    }
}
</script>

<style scoped lang="less">
.test-box {
	height: 100%;
	width: 100%;
}
</style>