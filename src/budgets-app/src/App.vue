<template>
  <div>
    <HeaderBar />
    <div>
      <router-view />
    </div>
  </div>

</template>

<script>
import HeaderBar from "@/components/HeaderBar.vue";

export default {
  name: 'App',
  components: {
    HeaderBar
  },
  setup() {
  }
};
</script>

<style>

</style>
