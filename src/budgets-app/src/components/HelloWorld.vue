<template>
    <h1>{{ msg }}</h1>
    <div>{{budgets}}</div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data() {
    return {
      msg: '',
      budgets: []
    }
  },
  mounted() {
    fetch("api/test")
        .then((response) => response.text())
        .then((data) => {
          this.msg = data;
        });
    fetch("/api/budgets")
        .then((response) => {
          this.budgets = response;
        })
  }
}
</script>


<style scoped>

</style>
