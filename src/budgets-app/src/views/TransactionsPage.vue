<template>
    <div>
      <SideMenu />
      <div style="margin-right: 5vh; margin-left: 30vh">
        <router-view />
        <div>
          <h1>Transactions</h1>
        </div>
        <div class="search-container">
          <div class="input-wrapper">
            <input v-model="searchTerm" id="searchTransactions" class="search-input" placeholder="Search by name..."/>
            <button @click="searchTransactions(searchTerm)"><i class="fa-solid fa-magnifying-glass"></i></button>
            <button @click="clearSearch">Clear Search</button>
            <button @click="generatePDF">Export to PDF</button>
          </div>
        </div>
        <div>
          <div class="deleteAllTransactions" style="margin-top: 15px" @click="deleteAllTransactions">
            <span style="background-color: #4caf50; padding: 5px;">Clear transactions</span>
          </div>
        </div>
        <button class="fab-button" @click="showNewModalMethod"><span class="plus-sign">+</span></button>
        <div v-if="showNewModal" class="modal-container">
          <div class="modal-content">
            <h2 style="padding: 5px">Add Transaction</h2>
            <label style="padding: 5px" for="transactionName">Transaction Name:</label>
            <input style="padding: 5px" v-model="newTransactionName" id="transactionName" type="text" />
            <label style="padding: 5px" for="transactionAmount">Transaction Amount:</label>
            <input style="padding: 5px" v-model="newTransactionAmount" id="transactionAmount" type="number" />
            <label style="padding: 5px" for="categoryID">Category:</label>
            <select style="padding: 5px; margin-bottom: 10px" v-model="newCategoryID">
              <option v-for="(category, index) in categories" :key="index" :value="category.categoryID">{{category.categoryName}}</option>
            </select>
            <div class="modal-buttons">
              <button style="padding: 5px; margin: 5px;" @click="submitTransaction">Submit</button>
              <button style="padding: 5px; margin: 5px;" @click="closeNewModalMethod">Close</button>
            </div>
          </div>
        </div>
        <div v-if="showEditModal" class="modal-container">
          <div class="modal-content">
            <h2 style="padding: 5px">Edit Transaction</h2>
            <label style="padding: 5px" for="transactionName">Transaction Name:</label>
            <input style="padding: 5px" v-model="editTransactionName" id="transactionName" type="text" />
            <label style="padding: 5px" for="transactionAmount">Transaction Amount:</label>
            <input style="padding: 5px" v-model="editTransactionAmount" id="transactionAmount" type="number" />
            <label style="padding: 5px" for="categoryID">Category:</label>
            <select style="padding: 5px; margin-bottom: 10px" v-model="editTransactionCategoryID">
              <option v-for="(category, index) in categories" :key="index" :value="category.categoryID">{{category.categoryName}}</option>
            </select>
            <div class="modal-buttons">
              <button style="padding: 5px; margin: 5px;" @click="editTransaction">Submit</button>
              <button style="padding: 5px; margin: 5px;" @click="closeEditModalMethod">Close</button>
            </div>
          </div>
        </div>
        <div class="transactionHeader">
          <p style="width: 550px">Name</p>
          <p>Category</p>
          <p>Amount</p>
        </div>
        <div v-if="noSearchTranCheck">
          <div class="transactionItems" v-for="(transaction, index) in transactions" :key="index">
            <p style="width: 500px">
              <span class="editSaveIcons" @click="toggleEditTransaction(transaction.transactionID)"><i class="fa-solid fa-pencil"></i></span>
              <span class="editSaveIcons" @click="deleteTransaction(transaction.transactionID)"><i class="fa-solid fa-trash"></i></span>
              {{ transaction.transactionName }}
            </p>
            <p>{{ getCategoryName(transaction.categoryID) }}</p>
            <p>{{ transaction.transactionAmount }}
            </p>
          </div>
        </div>
        <div v-if="searchTranCheck">
          <div class="transactionItems" v-for="(transaction, index) in searchedTransactions" :key="index">
            <p>
              <span class="editSaveIcons" @click="toggleEditTransaction(transaction.transactionID)"><i class="fa-solid fa-pencil"></i></span>
              <span class="editSaveIcons" @click="deleteTransaction(transaction.transactionID)"><i class="fa-solid fa-trash"></i></span>
              {{ transaction.transactionName }}
            </p>
            <p>{{ getCategoryName(transaction.categoryID) }}</p>
            <p>{{ transaction.transactionAmount }}
            </p>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
import {PDFDocument, rgb} from "pdf-lib";
import SideMenu from "@/components/SideBar/SideMenu.vue";
import {sidebarWidth} from "@/components/SideBar/SideMenuState";

export default {
  computed: {
    sidebarWidth() {
      return sidebarWidth
    }
  },
  data() {
    return {
      showEditModal: false,
      showNewModal: false,
      transactions: [],
      categories: [],
      categoryName: '',
      newTransactionName: '',
      newTransactionAmount: '',
      newCategoryID: '',
      transactionIDToEdit: null,
      transactionBeingEdited: {},
      editTransactionName: '',
      editTransactionAmount: '',
      editTransactionCategoryID: '',
      searchTerm: '',
      searchedTransactions: [],
      searchTranCheck: false,
      noSearchTranCheck: true
    }
},
  components: {
    SideMenu
  },
  methods: {
    showNewModalMethod() {
      this.getCategories()
      this.showNewModal = true
    },
    closeNewModalMethod() {
      this.showNewModal = false
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
        this.transactionBeingEdited = await response.json();
        this.editTransactionName = this.transactionBeingEdited.transactionName;
        this.editTransactionAmount = this.transactionBeingEdited.transactionAmount;
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
      if (this.newTransactionName && this.newTransactionAmount && this.newCategoryID) {
        const response = await fetch('/api/createNewTransaction/save', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            transactionName: this.newTransactionName,
            transactionAmount: this.newTransactionAmount,
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
          this.newCategoryID = '';
          this.showNewModal = false;
          this.getTransactions()
        }
      } else {
        this.$toast.open({
          message: 'You must input all the information!',
          type: 'error',
        });
      }
    },
    async editTransaction() {
      if (this.editTransactionName && this.editTransactionAmount) {
        const response = await fetch(`/api/editTransaction?id=${this.transactionIDToEdit}&transactionName=${this.editTransactionName}&transactionAmount=${this.editTransactionAmount}&categoryID=${this.editTransactionCategoryID}`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            transactionName: this.editTransactionName,
            transactionAmount: this.editTransactionAmount,
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
    },
    getCategories() {
      fetch('/api/allCategories')
          .then((response) => response.json()).then((data) => {
        this.categories = data;
      })
    },
    getCategoryName(categoryID) {
      const category = this.categories.find(cat => cat.categoryID === categoryID)
      return category ? category.categoryName : '???'
    },
    searchTransactions(searchTerm) {
      this.searchTranCheck = !this.searchTranCheck
      this.noSearchTranCheck = !this.searchTranCheck
      this.searchTerm = searchTerm
      this.searchedTransactions = this.transactions.filter(transaction => transaction.transactionName.toLowerCase().includes(searchTerm))
    },
    clearSearch() {
      this.searchTranCheck = !this.searchTranCheck
      this.noSearchTranCheck = !this.searchTranCheck
      this.searchTerm = ''
    },
    async deleteAllTransactions() {
      const response = await fetch(`/api/deleteAllTransactions`, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json'
        }
      });
      if (response.ok) {
        this.$toast.open({
          message: 'All transactions deleted!',
          type: 'success',
        });
        this.getTransactions()
      }
    },
    async generatePDF() {
      const pdfDoc = await PDFDocument.create();
      const page = pdfDoc.addPage([600, 800]);
      page.drawText('Transaction Report', { x: 50, y: 750, color: rgb(0, 0, 0), size: 20 });
      page.drawText('Date: ' + new Date().toLocaleDateString(), {x: 50, y:730, color: rgb(0, 0, 0), size: 20})
      page.drawText('Transaction Name', { x: 50, y: 700, color: rgb(0, 0, 0), size: 15 });
      page.drawText('Budget Category', { x: 250, y: 700, color: rgb(0, 0, 0), size: 15 });
      page.drawText('Transaction Amount', { x: 450, y: 700, color: rgb(0, 0, 0), size: 15 });
      const content = this.transactions
      content.forEach((entry, index) => {
        let name = this.getCategoryName(entry.categoryID)
        page.drawText(entry.transactionName, { x: 50, y: 670 - index * 20, color: rgb(0, 0, 0), size: 13 });
        page.drawText(name, { x: 250, y: 670 - index * 20, color: rgb(0, 0, 0), size: 13 });
        page.drawText(entry.transactionAmount.toString(), { x: 450, y: 670 - index * 20, color: rgb(0, 0, 0), size: 13 });
      });
      const pdfBytes = await pdfDoc.save();
      this.downloadPDF(pdfBytes);
    },
    downloadPDF(pdfBytes) {
      const blob = new Blob([pdfBytes], { type: 'application/pdf' });
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(blob);
      link.download = 'budget_report.pdf';
      link.click();
    },
  },
  mounted() {
    this.getTransactions();
    this.getCategories();
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
  padding: 30px;
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
.modal-buttons {
  display: flex;
  flex-direction: row;
}
.search-container {
  display: flex;
  align-items: center;
}
.input-wrapper {
  position: relative;
}
.search-input {
  padding: 15px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 15px;
  width: 300px;
}
.deleteAllTransactions {
  text-decoration: underline;
  font-size: 15px;
  color: black;
  margin-bottom: 10px;
  margin-right: 10px;
  &:hover {
    cursor: pointer;
  }
}
</style>











