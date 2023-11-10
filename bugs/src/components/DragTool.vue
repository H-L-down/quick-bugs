<template>
	<div>
		<div id="top-bg"></div>
		<div
			id="tool-box"
			ref="tool-box"
			:class="['place-' + placement, 'direction-' + direction]"
			@mousedown="dragEvent($event)"></div>
		
	</div>
</template>

<script>
export default {
	name: "DragTool",
	props: {
		placement: {
			type: String,
			default: 'top'
		},
		direction: {
			type: String,
			default: 'vertical'
		}
	},
	computed: {
		toolDOM() {
			if (document.getElementById('tool-box')) {
				return document.getElementById('tool-box');
			} else {
				return null;
			}
		}
	},
	data() {
		return {
			isFirst: true
		};
	},
	mounted() {
	
	},
	methods: {
		dragEvent(e) {
			const disx = e.pageX - this.toolDOM.offsetLeft;
			const disy = e.pageY - this.toolDOM.offsetTop;
			console.log(e.pageX, e.pageY, this.toolDOM.offsetLeft, this.toolDOM.offsetTop);
			const dragHeight = this.toolDOM.offsetHeight;
			const dragWidth = this.toolDOM.offsetWidth;
			console.log(dragWidth, dragHeight);
			const bodyHeight = document.body.offsetHeight;
			const bodyWidth = document.body.offsetWidth;
			
			this.toolDOM.style.left = e.pageX - 100 + 'px';
			this.toolDOM.style.top = e.pageY - 25 + 'px';
			this.toolDOM.style.width = '200px';
			
			document.onmousemove = function (e) {
				let moveX = e.pageX - 100
				let moveY = e.pageY - 25
				
				if (moveX <= 0) {
					moveX = 0;
				}
				if (moveY <= 0) {
					moveY = 0;
				}
				if (moveX >= bodyWidth - 200) {
					moveX = bodyWidth - 200;
				}
				if (moveY >= bodyHeight - 50) {
					moveY = bodyHeight - 50;
				}
				document.getElementById('tool-box').style.left = moveX + 'px';
				document.getElementById('tool-box').style.top = moveY + 'px';
				
				if (e.pageY <= 50 ) {
					console.log(document.getElementById('top-bg').style.display);
					if (document.getElementById('top-bg').style.width === '200px') {
						document.getElementById('top-bg').style.height = '50px';
						document.getElementById('top-bg').style.width = '100vw';
						document.getElementById('top-bg').style.left ='0px';
						document.getElementById('top-bg').style.top = '0px';
					}
				} else {
					document.getElementById('top-bg').style.height = '0px';
					document.getElementById('top-bg').style.left = moveX + 'px';
					document.getElementById('top-bg').style.top = moveY + 'px';
					document.getElementById('top-bg').style.width = '200px';
				}
			}
			document.onmouseup = function (e) {
				document.onmousemove = document.onmouseup = null;
				if (e.pageY <= 50 && (e.pageX > (bodyWidth/2 - 100) || e.pageX < (bodyWidth/2 + 100))) {
				
				}
			}
		}
	},
	directives: {
		drag(el, bindings) {
			el.onmousedown = function (e) {
				const disx = e.pageX - el.offsetLeft;
				const disy = e.pageY - el.offsetTop;
				console.log(e.pageX, e.pageY, el.offsetLeft, el.offsetTop);
				const dragHeight = el.offsetHeight;
				const dragWidth = el.offsetWidth;
				console.log(dragWidth, dragHeight);
				const bodyHeight = document.body.offsetHeight;
				const bodyWidth = document.body.offsetWidth;
				
				el.style.left = e.pageX - 100 + 'px';
				el.style.top = e.pageY - 25 + 'px';
				
				document.onmousemove = function (e) {
					el.style.left = e.pageX - 100 + 'px';
					el.style.top = e.pageY - 25 + 'px';
				}
				document.onmouseup = function () {
					document.onmousemove = document.onmouseup = null;
				}
			}
		}
	}
}
</script>

<style scoped lang="less">
#tool-box {
	border-radius: 8px;
	box-sizing: border-box;
	background: rgba(42, 42, 42, 0.35);
	-webkit-user-select: none;
	user-select: none;
	position: absolute;
}
#top-bg {
	transition: left 0.3s, top 0.3s, width 0.3s;
	position: absolute;
	width: 200px;
	height: 0;
	background: rgba(128,128,128,0.25);
}
.direction-vertical .placement-right {
	right: 50px;
	top: 0;
}
.direction-vertical .placement-left {
	left: 0;
	top: 0;
}
.direction-horizontal .placement-top {
	left: 0;
	top: 0;
}
.direction-horizontal .placement-bottom {
	left: 0;
	bottom: 50px;
}
.direction-vertical {
	height: 50px;
	width: 100vw;
	padding: 5px;
}
.direction-horizontal {
	height: 100vh;
	width: 50px;
	padding: 5px;
}

</style>