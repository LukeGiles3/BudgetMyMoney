<template>
  <div style="display: flex; flex-direction: column; height: 100%; width: 100vh; flex: 1">
    <HeaderBar/>
  <div class="budgetAmounts">
    <div class="budgetAmountsButtons">How Much?</div>
    <div class="budgetAmountsButtons">I Spent</div>
    <div class="budgetAmountsButtons">What's Left?</div>
  </div>
  </div>
</template>

<style>
.budgetAmounts {
  background-color: black;
  padding: 10px;
  color: black;
  display: flex;
  justify-content: space-between;
  align-content: stretch;
}
.budgetAmountsButtons {
  flex: 1;
  border: 5px solid black;
  box-sizing: border-box;
  text-align: center;
  padding: 15px;
  background-color: antiquewhite;
  font-size: 20px;
  transition: background-color 0.3s;
  &:hover {
    background-color: chocolate;
    cursor: pointer;
  }
}
</style>
<script setup>
import HeaderBar from "@/components/HeaderBar.vue";
</script>