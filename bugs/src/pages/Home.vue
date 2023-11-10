<template>
    <div class="home">
		<div class="row-1">
			<div class="search-row">
				<search></search>
			</div>
		</div>
		<div class="row-2">
			<time-line></time-line>
		</div>
    </div>
</template>

<script>
import search from '@/components/search.vue';
import TimeLine from '@/components/timeLine.vue';
export default {
    name: 'home',
    components: {
		TimeLine,
        search,
    },
    data() {
        return {};
    },
};
</script>

<style lang="less" scoped>
.home {
	width: 100%;
	height: 100%;
	display: grid;
	grid-template-columns: repeat(10, 10vw);
	grid-template-rows: repeat(10, 10%);
}
.logo-row {
	grid-row: 2/4;
	grid-column: 1/11;
	display: flex;
	justify-content: center;
	align-items: center;
}
.search-row {
	grid-row: 4/5;
	grid-column: 1/11;
	display: flex;
	justify-content: center;
	align-items: center;
}
.row-1 {
	grid-row: 1/5;
	grid-column: 1/11;
	display: grid;
	grid-template-columns: repeat(10, 10vw);
	grid-template-rows: repeat(4, 25%);
}
.row-2 {
	grid-row: 5/11;
	grid-column: 1/11;
	display: grid;
	grid-template-columns: repeat(10, 10vw);
	grid-template-rows: repeat(4, 25%);
}
</style>
