<template>
	<div class="timeline-box">
		<div class="event-wrapper">
			<div class="event-line">
				<div class="line-item" style="left:10px">
					23/9
					<div class="item-icon"></div>
				</div>
				<div class="line-item" style="left:70px">
					24/9
					<div class="item-icon"></div>
				</div>
				<div class="line-item" style="left:130px">
					25/9
					<div class="item-icon"></div>
				</div>
				<div class="line-item" style="left:190px">
					26/9
					<div class="item-icon"></div>
				</div>
			</div>
		</div>
		<div class="event-content">
		
		</div>
	</div>
</template>

<script>
export default {
	name: "timeLine"
}
</script>

<style lang="less" scoped>
.timeline-box {
	height: 100px;
	width: 700px;
	.event-wrapper {
		height: 100%;
		width: 100%;
		display: flex;
		justify-content: center;
		align-items: center;
		overflow: hidden;
		
		.event-line {
			width: 1000px;
			height: 2px;
			background: #cccccc;
			position: relative;
			.line-item {
				position: absolute;
				bottom: -3px;
				margin: 0 20px;
				display: flex;
				flex-direction: column;
				align-items: center;
				.item-icon {
					width: 8px;
					height: 8px;
					border-radius: 50%;
					background: #fff;
					border: 2px #00a6ff solid;
				}
			}
		}
	}
}
</style>