<template>
  <div style="display: flex; flex-direction: column; height: 100%; width: 150vh; flex: 1">
    <HeaderBar/>
    <div class="center">
      <h1>
        Welcome to BudgetMyMoney. Use the Budget tab to create categories of things you spend money on. Add transactions through the Transactions
        Tab and assign them to the categories you created. View how much you spent over time through the Reports tab.
      </h1>
    </div>
  </div>

</template>
<script>
import HeaderBar from "@/components/HeaderBar.vue";
export default {
  components: {
    HeaderBar
  }
}
</script>
<style scoped>
h1 {
  text-align: center;
  padding: 20px;
  color: white;
}
.center {
  display: flex;
  flex-direction: row;
  justify-content: center;
}
</style>