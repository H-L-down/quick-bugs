<template>
    <div
        class="grid-drag-layout"
        :style="{
            'grid-template-rows': 'repeat(' + rows +', 1fr)',
            'grid-template-columns': 'repeat(' + cols +', 1fr)',
            'height': height + 'px',
            'width': width + 'px'
        }"
    >
        <slot></slot>
    </div>
</template>

<script>
export default {
    name: "GridDragLayout",
    props: {
        width: {
            type: Number,
            default: 800
        },
        height: {
            type: Number,
            default: 800
        },
        rows: {
            type: Number,
            default: 4
        },
        cols: {
            type: Number,
            default: 4
        }
    },
    data() {
        return {
            layoutCss: ''
        }
    },
    created() {
        this.layoutCss = ''
    },
    mounted() {
        this.$bus.$emit('grid-drag-init', {
            height: this.height,
            width: this.width,
            rows: this.rows,
            cols: this.cols
        });
    }
}
</script>

<style scoped lang="less">
.grid-drag-layout {
    display: grid;
    border-radius: 10px;
    background: #c4c4c4;
    position: relative;
}
</style>