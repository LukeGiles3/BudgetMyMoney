<template>
  <div style="display: flex; flex-direction: column; height: 100%; width: 150vh; flex: 1; position: relative;">
    <HeaderBar />
    <div>
      <h1>Transactions</h1>
    </div>
    <button class="fab-button" @click="showNewModalMethod"><span class="plus-sign">+</span></button>
    <div v-if="showNewModal" class="modal-container">
      <div class="modal-content">
        <h2>Add Transaction</h2>
        <label for="transactionName">Transaction Name:</label>
        <input v-model="newTransactionName" id="transactionName" type="text" />
        <label for="transactionAmount">Transaction Amount:</label>
        <input v-model="newTransactionAmount" id="transactionAmount" type="number" />
        <label for="categoryID">Category ID:</label>
        <input v-model="newCategoryID" id="categoryID" type="text" />
        <label for="budgetID">Budget ID:</label>
        <input v-model="newBudgetID" id="budgetID" type="text" />
        <button @click="submitTransaction">Submit</button>
        <button @click="closeNewModalMethod">Close</button>
      </div>
    </div>
    <div v-if="showEditModal" class="modal-container">
      <div class="modal-content">
        <h2>Add Transaction</h2>
        <label for="transactionName">Transaction Name:</label>
        <input v-model="editTransactionName" id="transactionName" type="text" />
        <label for="transactionAmount">Transaction Amount:</label>
        <input v-model="editTransactionAmount" id="transactionAmount" type="number" />
        <label for="categoryID">Category ID:</label>
        <input v-model="editTransactionCategoryID" id="categoryID" type="text" />
        <label for="budgetID">Budget ID:</label>
        <input v-model="editTransactionBudgetID" id="budgetID" type="text" />
        <button @click="editTransaction">Submit</button>
        <button @click="closeEditModalMethod">Close</button>
      </div>
    </div>
    <div class="transactionHeader">
      <p>Name</p>
      <p>Budget</p>
      <p>Category</p>
      <p>Amount</p>
    </div>
    <div class="transactionItems" v-for="(transaction, index) in transactions" :key="index">
      <p>
        <span class="editSaveIcons" @click="toggleEditTransaction(transaction.transactionID)"><i class="fa-solid fa-pencil"></i></span>
        <span class="editSaveIcons" @click="deleteTransaction(transaction.transactionID)"><i class="fa-solid fa-trash"></i></span>
        {{ transaction.transactionName }}
      </p>
      <p>{{ transaction.budgetID }}</p>
      <p>{{ transaction.categoryID }}</p>
      <p>{{ transaction.transactionAmount }}

      </p>
    </div>
  </div>
</template>

<script>
import HeaderBar from "@/components/HeaderBar.vue";
export default {
  data() {
    return {
      showEditModal: false,
      showNewModal: false,
      transactions: [],
      newTransactionName: '',
      newTransactionAmount: '',
      newBudgetID: '',
      newCategoryID: '',
      transactionIDToEdit: null,
      transactionBeingEdited: {},
      editTransactionName: '',
      editTransactionAmount: '',
      editTransactionBudgetID: '',
      editTransactionCategoryID: ''
    }
},
  components: {
    HeaderBar
  },
  methods: {
    showNewModalMethod() {
      this.showNewModal = true
    },
    closeNewModalMethod() {
      this.showNewModal = false
    },
    showEditModalMethod() {
      this.showEditModal = true
    },
    closeEditModalMethod() {
      this.showEditModal = false
    },
    async toggleEditTransaction(transactionID) {
      try {
        const response = await fetch(`/api/transaction?id=${transactionID}`, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json'
          }
        });
        if (!response.ok) {
          throw new Error(`Error fetching transaction: ${response.statusText}`);
        }
        const data = await response.json();
        this.transactionBeingEdited = data;
        this.editTransactionName = this.transactionBeingEdited.transactionName;
        this.editTransactionAmount = this.transactionBeingEdited.transactionAmount;
        this.editTransactionBudgetID = this.transactionBeingEdited.budgetID;
        this.editTransactionCategoryID = this.transactionBeingEdited.categoryID;
        this.showEditModal = !this.showEditModal;
        this.transactionIDToEdit = transactionID;
      } catch (error) {
        console.error('Toggle edit transaction error:', error);
      }
    },
    getTransactions() {
      fetch('/api/allTransactions')
          .then((response) => response.json()).then((data) => {
        this.transactions = data;
      })
    },
    async submitTransaction() {
      if (this.newTransactionName && this.newTransactionAmount) {
        const response = await fetch('/api/createNewTransaction/save', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            transactionName: this.newTransactionName,
            transactionAmount: this.newTransactionAmount,
            budgetID: this.newBudgetID,
            categoryID: this.newCategoryID
          }),
        });
        if (response.ok) {
          this.$toast.open({
            message: 'Transaction created!',
            type: 'success',
          });
          this.newTransactionName = '';
          this.newTransactionAmount = '';
          this.newBudgetID = '';
          this.newCategoryID = '';
          this.showNewModal = false;
          this.getTransactions()
        }
      } else {
        this.$toast.open({
          message: 'You must input the transaction name and amount!',
          type: 'error',
        });
      }
    },
    async editTransaction() {
      if (this.editTransactionName && this.editTransactionAmount) {
        const response = await fetch(`/api/editTransaction?id=${this.transactionIDToEdit}&transactionName=${this.editTransactionName}&transactionAmount=${this.editTransactionAmount}&budgetID=${this.editTransactionBudgetID}&categoryID=${this.editTransactionCategoryID}`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            transactionName: this.editTransactionName,
            transactionAmount: this.editTransactionAmount,
            budgetID: this.editTransactionBudgetID,
            categoryID: this.editTransactionCategoryID
          }),
        });
        if (response.ok) {
          this.$toast.open({
            message: 'Transaction edited!',
            type: 'success',
          });
          this.editTransactionName = '';
          this.editTransactionAmount = '';
          this.editTransactionBudgetID = '';
          this.editTransactionCategoryID = '';
          this.showEditModal = false;
          this.getTransactions()
        }
      } else {
        this.$toast.open({
          message: 'You must input the transaction name and amount!',
          type: 'error',
        });
      }
    },
    async deleteTransaction(transactionID) {
      const response = await fetch(`/api/deleteTransaction?id=${transactionID}`, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json'
        }
      });
      if (response.ok) {
        this.$toast.open({
          message: 'Transaction deleted!',
          type: 'success',
        });
        this.getTransactions()
      }
    }
  },
  mounted() {
    this.getTransactions();
  }
}

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
.modal-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(0, 0, 0, 0.5);
}
.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: black;
}
button {
  display: inline-block;
  padding: 10px 20px;
  font-size: 16px;
  text-align: center;
  text-decoration: none;
  background-color: #90EE8F;
  color: black;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}
button:hover {
  background-color: #2980b9;
}
.transactionItems {
  display: flex;
  flex-direction: row;
  padding: 10px;
  margin-top: 10px;
  justify-content: space-between;
  background-color: darkgrey;
  font-size: 20px;
}
.transactionHeader {
  display: flex;
  justify-content: space-between;
  font-size: 18px;
  border-bottom: 4px solid whitesmoke;
  margin-bottom: 5px;
}
.editSaveIcons {
  padding: 5px;
  &:hover {
    cursor: pointer;
  }
}
</style>











