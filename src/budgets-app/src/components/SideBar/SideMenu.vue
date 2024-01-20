<template>
  <div class="sidebar">
    <h1>
      <span>Menu</span>
    </h1>
    <SideMenuItem to="/home">Home</SideMenuItem>
    <SideMenuItem to="/budgets">Budget</SideMenuItem>
    <SideMenuItem to="/transactions">Transactions</SideMenuItem>
    <a class="link" @click="signOut">Sign Out</a>
  </div>
</template>

<script>
import SideMenuItem from "@/components/SideBar/SideMenuItem.vue";
import { getAuth, signOut } from 'firebase/auth'

export default {
  props: {},
  components: {SideMenuItem },
  methods: {
    signOut() {
      const auth = getAuth();
      signOut(auth).then(() => {
        this.$toast.open({
          message: 'Sign out successful!',
          type: 'success',
        });
        this.$router.push('/login')
      }).catch((error) => {
        this.$toast.open({
          message: `Sign out error: ${error}`,
          type: 'error',
        });
      });
    }
  },
  setup() {
    return { }
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

.link {
  display: flex;
  align-items: center;

  cursor: pointer;
  position: relative;
  font-weight: 400;
  user-select: none;

  margin: 0.1em 0;
  padding: 0.4em;
  border-radius: 0.25em;
  height: 1.5em;

  color: black;
  text-decoration: none;
}

.link:hover {
  background-color: blueviolet;
}

.link.active {
  background-color: greenyellow;
}

.link .icon {
  flex-shrink: 0;
  width: 25px;
  margin-right: 10px;
}
</style>









