<template>
  <div style="padding-left: 10px">
    <h1>{{ msg }}</h1>
    <ul>
      <li v-for="budget in budgets" :key="budget.budgetID">
        {{ budget.budgetName }}
      </li>
    </ul>
    <h1>{{budget.budgetName}}</h1>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data() {
    return {
      msg: '',
      budgets: [],
      budget: []
    }
  },
  mounted() {
    fetch("api/test")
        .then((response) => response.text())
        .then((data) => {
          this.msg = data;
        });
    fetch("/api/budgets")
        .then((response) => response.json()).then((data) => {
          this.budgets = data;
        })
    fetch("/api/budget?id=1")
        .then((response) => response.json()).then((data) => {
        this.budget = data;
    })
  }
}
</script>


<style scoped>

</style>
