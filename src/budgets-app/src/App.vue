<template>
  <div  style="display: flex; justify-content: space-around; height: 100vh">
    <SideMenu />
    <div :style="{ 'margin-left': sidebarWidth}">
      <router-view />
    </div>
  </div>

</template>

<script>
import SideMenu from './components/SideBar/SideMenu.vue'
import {sidebarWidth} from "@/components/SideBar/SideMenuState";

export default {
  name: 'App',
  components: {
    SideMenu
  },
  setup() {
    return { sidebarWidth }
  }
};
</script>

<style>

</style>
