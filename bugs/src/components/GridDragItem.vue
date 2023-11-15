<template>
    <div
        class="grid-drag-item"
        ref="grid-drag-item"
        :style="{
            'grid-row': gridRow,
            'grid-column': gridCol,
            'top': top + 'px',
            'left': left + 'px',
            'width': width + 'px',
            'height': height + 'px',
            'position': position
        }"
        @mousedown="dragEvent($event)"
    >
        <div class="content" @mousedown.stop="() => {}">
            {{top}} {{left}} {{gridRow}} {{gridCol}}
            <button @click="closeMove">close</button>
            <slot></slot>
        </div>
    </div>
</template>

<script>
export default {
    name: "GridDragItem",
    props: {
        row: {
            type: String,
            default: '1 / 2'
        },
        col: {
            type: String,
            default: '1 / 2'
        }
    },
    data() {
        return {
            layoutHeight: 0,
            layoutWidth: 0,
            rows: 0,
            cols: 0,
            gridRow: '',
            gridCol: '',
            width: 0,
            height: 0,
            top: 0,
            left: 0,
            position: '',
            itemTop: 0,
            itemLeft: 0,
            isMove: true
        }
    },
    created() {
        this.gridCol = this.col;
        this.gridRow = this.row;
        this.$bus.$on('grid-drag-init', val => {
            this.layoutHeight = val.height;
            this.layoutWidth = val.width;
            this.rows = val.rows;
            this.cols = val.cols;
            const minX = this.layoutWidth / this.cols;
            const minY = this.layoutHeight / this.cols;
            let row = this.gridRow.split(' ').filter(el => el !== '/').map(el => Number(el));
            let col = this.gridCol.split(' ').filter(el => el !== '/').map(el => Number(el));
            this.top = (row[0] - 1) * minY;
            this.left = (col[0] - 1) * minX;
            this.width = (col[1] - col[0]) * minX;
            this.height = (row[1] - row[0]) * minY;
            this.position = 'static';
        })
    },
    mounted() {

    },
    methods: {
        dragEvent (event) {
            if (this.isMove) {
                const minX = this.layoutWidth / this.cols;
                const minY = this.layoutHeight / this.cols;
                const mouseX = event.clientX;
                const mouseY = event.clientY;
                let row = this.gridRow.split(' ').filter(el => el !== '/').map(el => Number(el));
                let col = this.gridCol.split(' ').filter(el => el !== '/').map(el => Number(el));
                const top = (row[0] - 1) * minY;
                const left = (col[0] - 1) * minX;
                this.top = top;
                this.left = left;
                this.gridRow = '';
                this.gridCol = '';
                this.position = 'absolute';
                this.$bus.$emit('grid-drag-show-line', { width: this.width, height: this.height });
                let that = this;
                console.log()
                document.onmousemove = function (event) {
                    let nextY = top + event.pageY - mouseY;
                    let nextX = left + event.pageX - mouseX;
                    if (nextY <= 0) {
                        nextY = 0;
                    }
                    if (nextY >= (that.layoutHeight - that.height)) {
                        nextY = that.layoutHeight - that.height;
                    }
                    if (nextX <= 0) {
                        nextX = 0;
                    }
                    if (nextX >= (that.layoutWidth - that.width)) {
                        nextX = that.layoutWidth - that.width;
                    }
                    that.top = nextY;
                    that.left = nextX;
                    // console.log(rowNum, colNum, row, col);
                    // bgDOM.style.gridRow = rowNum + ' / ' + (row[1] + rowNum - row[0]);
                    // bgDOM.style.gridColumn = colNum + ' / ' + (col[1] + colNum - col[0]);
                    // that.gridRow = rowNum + ' / ' + (row[1] + rowNum - row[0]);
                    // that.gridCol = colNum + ' / ' + (col[1] + colNum - col[0]);
                }
                document.onmouseup = function (event) {
                    that.position = 'static';
                    let rowNum = Math.round(that.top / minY) + 1;
                    let colNum = Math.round(that.left / minX) + 1;
                    that.gridRow = `${rowNum} / ${row[1] + rowNum - row[0]}`;
                    that.gridCol = `${colNum} / ${col[1] + colNum - col[0]}`;
                    console.log(row, col, rowNum, colNum);
                    that.$bus.$emit('grid-drag-close-line');
                    document.onmousemove = document.onmouseup = null;
                }
            }
        },
        closeMove() {
            this.isMove = !this.isMove;
        }
    }
}
</script>

<style scoped lang="less">
.grid-drag-item {
    background: #00b89c;
    z-index: 4;
    user-select: none;
    -webkit-user-drag: none;
    .content {
        height: 100%;
        width: 100%;
        user-select: none;
        -webkit-user-drag: none;
        position: relative;
        top: 20px;
        left: 0;
        overflow: hidden;
        resize: both;
        border: 1px #000 solid;
        background: #b6a9a9;
    }
}
</style>