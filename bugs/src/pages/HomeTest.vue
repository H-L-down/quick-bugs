<template>
    <div class="home" ref="home">
		<div class="time-line-box">
			<div class="time-scroll">
				<div class="white-box"></div>
				<a-timeline mode="right">
					<a-timeline-item v-for="e in 20" @click="test">2015-09-0{{e + 1}}</a-timeline-item>
				</a-timeline>
				<div class="white-box">

                </div>
			</div>
			<div class="top-mask"></div>
			<div class="bottom-mask"></div>
		</div>
		<div class="drag-line"></div>
		<div class="drag-line-1"></div>
		<div class="drag-bg">
			<div></div>
		</div>
		<div
			class="content-box"
			ref="content-box"
            @mousedown="dragEvent($event)"
		>
			<div class="content">sadasd</div>
		</div>
        <button @click="() => { isMove = !isMove }"></button>
    </div>
</template>

<script>

export default {
    name: 'home',
    components: {},
	data() {
        return {
            isMove: true
        };
    },
	directives: {
		'grid-drag': {
			bind: function (el, binding, vnode) {
                el.onmousedown = function dragEvent (event) {
                    const style = window.getComputedStyle(el);
                    const minX = window.innerWidth / 40;
                    const minY = window.innerHeight / 40;
                    const mouseX = event.pageX;
                    const mouseY = event.pageY;
                    let row = style['grid-row'].split(' ').filter(el => el !== '/').map(el => Number(el));
                    let col = style['grid-column'].split(' ').filter(el => el !== '/').map(el => Number(el));
                    const top = (row[0] - 1) * minY;
                    const left = (col[0] - 1) * minX;
                    const width = (col[1] - col[0]) * minX;
                    const height = (row[1] - row[0]) * minY;
                    let bgDOM = document.getElementsByClassName('drag-bg')[0];
                    let bgLine = document.getElementsByClassName('drag-line')[0];
                    let bgLine1 = document.getElementsByClassName('drag-line-1')[0];
                    el.style.width = width + 'px';
                    el.style.height = height + 'px';
                    el.style.position = 'absolute';
                    el.style.top = top + 'px';
                    el.style.left = left + 'px';
                    bgDOM.style.width = width + 'px';
                    bgDOM.style.height = height + 'px';
                    bgDOM.style.gridRow = style['grid-row'];
                    bgDOM.style.gridColumn = style['grid-column'];
                    bgDOM.className += ' out';
                    bgLine.style.backgroundSize = '6px ' + height + 'px';
                    bgLine1.style.backgroundSize = width + 'px' + ' 6px';
                    document.onmousemove = function (event) {
                        let nextY = top + event.pageY - mouseY;
                        let nextX = left + event.pageX - mouseX;
                        if (nextY <= 0) {
                            nextY = 0;
                        }
                        if (nextY >= (window.innerHeight - height)) {
                            nextY = window.innerHeight - height;
                        }
                        if (nextX <= window.innerWidth * 0.25) {
                            nextX = window.innerWidth * 0.25;
                        }
                        if (nextX >= (window.innerWidth - width)) {
                            nextX = window.innerWidth - width;
                        }
                        el.style.top = nextY + 'px';
                        el.style.left = nextX + 'px';
                        let rowNum = Math.round(nextY / minY) + 1;
                        let colNum = Math.round(nextX / minX) + 1;
                        bgDOM.style.gridRow = rowNum + ' / ' + (row[1] + rowNum - row[0]);
                        bgDOM.style.gridColumn = colNum + ' / ' + (col[1] + colNum - col[0]);
                        el.style.gridRow = rowNum + ' / ' + (row[1] + rowNum - row[0]);
                        el.style.gridColumn = colNum + ' / ' + (col[1] + colNum - col[0]);
                    }
                    document.onmouseup = function (event) {
                        el.style.position = 'static';
                        bgDOM.className = 'drag-bg';
                        document.onmousemove = document.onmouseup = null;
                        bgLine.style.backgroundSize = '';
                        bgLine1.style.backgroundSize = '';
                    }
                }
			}
		}
	},
	methods: {
		test() {
			console.log(11);
			document.getElementsByClassName('content-box')[0].style.position = 'static';
		},
        dragEvent (event) {
            if (this.isMove) {
                let el = document.getElementsByClassName('content-box')[0];
                const style = window.getComputedStyle(el);
                const minX = window.innerWidth / 40;
                const minY = window.innerHeight / 40;
                const mouseX = event.pageX;
                const mouseY = event.pageY;
                let row = style['grid-row'].split(' ').filter(el => el !== '/').map(el => Number(el));
                let col = style['grid-column'].split(' ').filter(el => el !== '/').map(el => Number(el));
                const top = (row[0] - 1) * minY;
                const left = (col[0] - 1) * minX;
                const width = (col[1] - col[0]) * minX;
                const height = (row[1] - row[0]) * minY;
                let bgDOM = document.getElementsByClassName('drag-bg')[0];
                let bgLine = document.getElementsByClassName('drag-line')[0];
                let bgLine1 = document.getElementsByClassName('drag-line-1')[0];
                el.style.width = width + 'px';
                el.style.height = height + 'px';
                el.style.position = 'absolute';
                el.style.top = top + 'px';
                el.style.left = left + 'px';
                bgDOM.style.width = width + 'px';
                bgDOM.style.height = height + 'px';
                bgDOM.style.gridRow = style['grid-row'];
                bgDOM.style.gridColumn = style['grid-column'];
                bgDOM.className += ' out';
                bgLine.style.backgroundSize = '6px ' + height + 'px';
                bgLine1.style.backgroundSize = width + 'px' + ' 6px';
                document.onmousemove = function (event) {
                    let nextY = top + event.pageY - mouseY;
                    let nextX = left + event.pageX - mouseX;
                    if (nextY <= 0) {
                        nextY = 0;
                    }
                    if (nextY >= (window.innerHeight - height)) {
                        nextY = window.innerHeight - height;
                    }
                    if (nextX <= window.innerWidth * 0.25) {
                        nextX = window.innerWidth * 0.25;
                    }
                    if (nextX >= (window.innerWidth - width)) {
                        nextX = window.innerWidth - width;
                    }
                    el.style.top = nextY + 'px';
                    el.style.left = nextX + 'px';
                    let rowNum = Math.round(nextY / minY) + 1;
                    let colNum = Math.round(nextX / minX) + 1;
                    bgDOM.style.gridRow = rowNum + ' / ' + (row[1] + rowNum - row[0]);
                    bgDOM.style.gridColumn = colNum + ' / ' + (col[1] + colNum - col[0]);
                    el.style.gridRow = rowNum + ' / ' + (row[1] + rowNum - row[0]);
                    el.style.gridColumn = colNum + ' / ' + (col[1] + colNum - col[0]);
                }
                document.onmouseup = function (event) {
                    el.style.position = 'static';
                    bgDOM.className = 'drag-bg';
                    document.onmousemove = document.onmouseup = null;
                    bgLine.style.backgroundSize = '';
                    bgLine1.style.backgroundSize = '';
                }
            }
        }
	}
};
</script>

<style lang="less" scoped>
.home {
	width: 100%;
	height: 100%;
	display: grid;
	grid-template-columns: repeat(40, 2.5vw);
	grid-template-rows: repeat(40, 2.5vh);
	z-index: 1;
	.time-line-box {
		display: flex;
		position: relative;
		align-items: center;
		justify-content: center;
		overflow: hidden;
		grid-row: 9 / 33;
		grid-column: 4 / 10;
		z-index: 2;
		.time-scroll {
			width: 100%;
			height: 100%;
			overflow: scroll;
			scrollbar-width: none;
			&::-webkit-scrollbar {
				display: none;
			}
			.white-box {
				width: 100%;
				height: 35%;
			}
		}
		.top-mask {
			position: absolute;
			height: 30%;
			width: 100%;
			top: 0;
			left: 0;
			background: linear-gradient(to bottom, white, transparent);
		}
		.bottom-mask {
			position: absolute;
			height: 30%;
			width: 100%;
			left: 0;
			bottom: 0;
			background: linear-gradient(to top, white, transparent);
		}
	}
	.time-line-box:hover {
		//background: #514848;
		border-radius: 5px;
	}
	.content-box {
		grid-row: 9 / 14;
		grid-column: 13 / 18;
		z-index: 4;
        height: 12.5vh;
        width: 12.5vw;
		.content {
            height: 100%;
            width: 100%;
            overflow: hidden;
            resize: both;
            border: 1px #000 solid;
            background: #b6a9a9;
		}
	}
	.out {
		z-index: 3;
		div {
			width: 100%;
			height: 100%;
			background: #cecece;
			border-radius: 10px;
		}
	}
	.drag-line {
		grid-row: 1 / 41;
		grid-column: 11 / 41;
		height: 100vh;
		width: 75vw;
		background: linear-gradient(to right, white 3px, transparent 3px),
		linear-gradient(to bottom, #00b89c 1px, transparent 1px) repeat;
		background-size: 0;
		z-index: 2;
	}
	.drag-line-1 {
		grid-row: 1 / 41;
		grid-column: 11 / 41;
		height: 100vh;
		width: 75vw;
		background: linear-gradient(to bottom, white 3px, transparent 3px),
		linear-gradient(to right, #00b89c 1px, transparent 1px) repeat;
		background-size: 0;
		opacity:0.5;
		z-index: 2;
	}
}
</style>
