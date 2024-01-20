<template>
    <div>
      <SideMenu />
      <div :style="{ 'margin-left': sidebarWidth}">
        <router-view />
        <div class="center">
          <h1>
            Welcome to BudgetMyMoney. Use the Budget tab to create categories of things you spend money on. Add transactions through the Transactions
            Tab and assign them to the categories you created. View how much you spent over time by generating reports on the two different pages.
          </h1>
        </div>
      </div>
    </div>

</template>
<script>
import SideMenu from "@/components/SideBar/SideMenu.vue";
import {sidebarWidth} from "@/components/SideBar/SideMenuState";
export default {
  computed: {
    sidebarWidth() {
      return sidebarWidth
    }
  },
  components: {
    SideMenu
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
  margin-left: 200px;
}
</style>