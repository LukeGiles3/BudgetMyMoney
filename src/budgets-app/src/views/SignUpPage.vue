<template>
  <body>
  <div class="form">
    <h1>Sign Up</h1>
    <div><label> First Name: <input type="text" name="firstname" v-model="userFirstName"/> </label></div>
    <div><label> Last Name: <input type="text" name="lastname" v-model="userLastName"/> </label></div>
    <div><label> Email: <input type="text" name="email" v-model="userEmail"/> </label></div>
    <div><label> Password: <input type="password" name="password" v-model="userPassword"/> </label></div>
    <button @click="createUser">Create Account</button>
    <div>Already have an account? <a href="/login">Click Here</a></div>
  </div>
  </body>
</template>

<style scoped>
body {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
  margin: 0;
  color: black;
  }

.form {
  width: 300px;
  margin: auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #f4f4f4;
  }

label {
  display: block;
  margin-bottom: 8px;
  }

input {
  width: 100%;
  padding: 8px;
  margin-bottom: 16px;
  box-sizing: border-box;
  }

button {
  background-color: greenyellow;
  color: black;
  padding: 10px 15px;
  margin-bottom: 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #45a049;
}

</style>

<script>

import { getAuth, createUserWithEmailAndPassword }from "firebase/auth";

export default {
  data() {
    return {
      userFirstName: '',
      userLastName: '',
      userEmail: '',
      userPassword: ''
    };
  },
  components: {

  },
  methods: {
    createUser() {
      if (this.userFirstName && this.userLastName && this.userEmail && this.userPassword) {
        const auth = getAuth();
        createUserWithEmailAndPassword(auth, this.userEmail, this.userPassword)
            .then(() => {
          this.$toast.open({
            message: 'Account created successfully!',
            type: 'success',
          });
          this.$router.push('/home')
        })
            .catch(error => {
              this.$toast.open({
                message: `Registration failed. Error: ${error}`,
                type: 'error'
              })
            });
      } else {
        this.$toast.open({
          message: 'You must fill out all the fields',
          type: 'error',
        });
      }
    }
  },
  mounted() {
  }
};

</script>