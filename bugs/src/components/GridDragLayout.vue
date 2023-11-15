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
        <div
            class="bg-vertical-line"
            :style="{
                'height': height + 1 + 'px',
                'width': width + 1 + 'px',
                'background-size': `5px ${lineHeight}px`
            }"
        ></div>
        <div
            class="bg-horizontal-line"
            :style="{
                'height': height + 1 + 'px',
                'width': width + 1 + 'px',
                'background-size': `${lineWidth}px 5px`
            }"
        ></div>
        <div
            class="move-bg"
            :style="{
                'grid-row': '1/' + (rows + 1),
                'grid-column': '1/' + (cols + 1)
            }"
        ></div>
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
            layoutCss: '',
            lineWidth: 0,
            lineHeight: 0,
        }
    },
    created() {
        this.layoutCss = '';
        this.$bus.$on('grid-drag-show-line', (val) => {
            this.lineWidth = val.width;
            this.lineHeight = val.height;
        });
        this.$bus.$on('grid-drag-close-line', () => {
            this.lineWidth = 0;
            this.lineHeight = 0;
        });
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
    position: relative;
    box-sizing: border-box;
}
.bg-horizontal-line {
    position: absolute;
    top: 0;
    left: 0;
    background: linear-gradient(to bottom, white 3px, transparent 3px),
    linear-gradient(to right, #000000 1px, transparent 1px) repeat;
    background-size: 150px 5px;
    opacity:0.5;
    z-index: 2;
}
.bg-vertical-line {
    position: absolute;
    top: 0;
    left: 0;
    background: linear-gradient(to right, white 3px, transparent 3px),
    linear-gradient(to bottom, #000000 1px, transparent 1px) repeat;
    background-size: 5px 200px;
    z-index: 2;
}
.move-bg {

}
</style>