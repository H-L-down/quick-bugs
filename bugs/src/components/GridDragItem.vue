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
<!--        <div class="content">-->
<!--            <slot></slot>-->
<!--        </div>-->
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
            console.log(val);
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
            console.log(event);
            if (this.isMove) {
                console.log(event, '000');
                const minX = this.layoutWidth / this.cols;
                const minY = this.layoutHeight / this.cols;
                const mouseX = event.pageX;
                const mouseY = event.pageY;
                let row = this.gridRow.split(' ').filter(el => el !== '/').map(el => Number(el));
                let col = this.gridCol.split(' ').filter(el => el !== '/').map(el => Number(el));
                const top = (row[0] - 1) * minY;
                const left = (col[0] - 1) * minX;
                const width = (col[1] - col[0]) * minX;
                const height = (row[1] - row[0]) * minY;
                // let bgDOM = document.getElementsByClassName('drag-bg')[0];
                // let bgLine = document.getElementsByClassName('drag-line')[0];
                // let bgLine1 = document.getElementsByClassName('drag-line-1')[0];
                this.width = width;
                this.height = height;
                this.position = 'absolute';
                this.top = top;
                this.left = left;
                // bgDOM.style.width = width + 'px';
                // bgDOM.style.height = height + 'px';
                // bgDOM.style.gridRow = style['grid-row'];
                // bgDOM.style.gridColumn = style['grid-column'];
                // bgDOM.className += ' out';
                // bgLine.style.backgroundSize = '6px ' + height + 'px';
                // bgLine1.style.backgroundSize = width + 'px' + ' 6px';
                let that = this;
                document.onmousemove = function (event) {
                    let nextY = top + event.pageY - mouseY;
                    let nextX = left + event.pageX - mouseX;
                    // if (nextY <= 0) {
                    //     nextY = 0;
                    // }
                    // if (nextY >= (window.innerHeight - height)) {
                    //     nextY = window.innerHeight - height;
                    // }
                    // if (nextX <= window.innerWidth * 0.25) {
                    //     nextX = window.innerWidth * 0.25;
                    // }
                    // if (nextX >= (window.innerWidth - width)) {
                    //     nextX = window.innerWidth - width;
                    // }
                    that.top = nextY;
                    that.left = nextX;
                    let rowNum = Math.round(nextY / minY) + 1;
                    let colNum = Math.round(nextX / minX) + 1;
                    console.log(rowNum, colNum, row, col);
                    // bgDOM.style.gridRow = rowNum + ' / ' + (row[1] + rowNum - row[0]);
                    // bgDOM.style.gridColumn = colNum + ' / ' + (col[1] + colNum - col[0]);
                    // that.gridRow = rowNum + ' / ' + (row[1] + rowNum - row[0]);
                    // that.gridCol = colNum + ' / ' + (col[1] + colNum - col[0]);
                }
                document.onmouseup = function (event) {
                    that.position = 'static';
                    let rowNum = Math.round(that.top / minY) + 1;
                    let colNum = Math.round(that.left / minX) + 1;
                    that.gridRow = rowNum + ' / ' + (row[1] + rowNum - row[0]);
                    that.gridCol = colNum + ' / ' + (col[1] + colNum - col[0]);
                    // bgDOM.className = 'drag-bg';
                    document.onmousemove = document.onmouseup = null;
                    // bgLine.style.backgroundSize = '';
                    // bgLine1.style.backgroundSize = '';
                }
            }
        }
    }
}
</script>

<style scoped lang="less">
.grid-drag-item {
    padding: 10px;
    background: #00b89c;
    .content {
        height: 100%;
        width: 100%;
        overflow: hidden;
        resize: both;
        border: 1px #000 solid;
        background: #b6a9a9;
    }
}
</style>