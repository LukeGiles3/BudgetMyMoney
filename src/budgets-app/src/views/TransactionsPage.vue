<template>
  <div style="display: flex; flex-direction: column; height: 100%; width: 150vh; flex: 1; position: relative;">
    <HeaderBar />
    <div>
      <h1>This is the transactions page</h1>
    </div>
    <button class="fab-button" @click="openAddTransactionModal"><span class="plus-sign">+</span></button>
  </div>
</template>
<script setup>
import HeaderBar from "@/components/HeaderBar.vue";
const openAddTransactionModal = () => {
  console.log('Open Add Transaction Modal');
};
</script>
<style scoped>
.fab-button {
  position: fixed;
  bottom: 50px;
  right: 50px;
  background-color: #90EE8F;
  color: black;
  border: none;
  border-radius: 50%;
  width: 50px;
  height: 50px;
  font-size: 34px;
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: background-color 0.3s;
  &:hover {
    background-color: #45a049;
  }
}
.plus-sign {
  line-height: 1;
}
</style>