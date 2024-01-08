<template>
  <div class="sidebar" :style="{ width: sidebarWidth }">
    <h1>
      <span v-if="collapsed">
      </span>
      <span v-else>Menu</span>
    </h1>

    <SideMenuItem to="/">Home</SideMenuItem>
    <SideMenuItem to="/budgets">Budget</SideMenuItem>
    <SideMenuItem to="/transactions">Transactions</SideMenuItem>
    <SideMenuItem to="/reports">Reports</SideMenuItem>

    <span
        class="collapse-icon"
        :class="{ 'rotate-180': collapsed }"
        @click="toggleSidebar"
    >
      <i class="fas fa-angle-double-left" />
    </span>
  </div>
</template>

<script>
import { collapsed, toggleSidebar, sidebarWidth } from './SideMenuState'
import SideMenuItem from "@/components/SideBar/SideMenuItem.vue";

export default {
  props: {},
  components: {SideMenuItem },
  setup() {
    return { collapsed, toggleSidebar, sidebarWidth }
  }
}
</script>

<style scoped>
.sidebar {
  color: white;
  background-color: chocolate;

  float: left;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  bottom: 0;
  padding: 0.5em;

  transition: 0.3s ease;

  display: flex;
  flex-direction: column;
}

.sidebar h1 {
  height: 2.5em;
}

.collapse-icon {
  position: absolute;
  bottom: 0;
  padding: 0.75em;

  color: rgba(255, 255, 255, 0.7);

  transition: 0.2s linear;
}

.rotate-180 {
  transform: rotate(180deg);
  transition: 0.2s linear;
}
</style>









