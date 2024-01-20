<template>
  <div>
    <div >
      <SideMenu />
      <div style="margin-left: 30vh; margin-right: 5vh">
        <router-view />
        <div>
          <h1>Budget</h1>
        </div>
        <div class="budgetAmounts">
          <div class="budgetAmountsButtons" :class= "{'selected' : showPlanned}" @click="togglePlanned">Planned</div>
          <div class="budgetAmountsButtons" :class= "{'selected' : showSpent}" @click="toggleSpent">Spent</div>
          <div class="budgetAmountsButtons" :class= "{'selected' : showRemaining}" @click="toggleRemaining">Remaining</div>
        </div>
        <div style="display: flex;" v-if="showPlanned">
          <div class="addNewCategory" @click="toggleNewCategory">
            <span style="background-color: #4caf50; padding: 5px;">New category +</span>
          </div>
          <div class="addNewCategory" @click="deleteAllCategories">
            <span style="background-color: #4caf50; padding: 5px;">Clear categories</span>
          </div>
          <div class="addNewCategory" @click="generatePDF">
            <span style="background-color: #4caf50; padding: 5px;">Export to PDF</span>
          </div>
        </div>
        <div v-if="showNewCategory" class="newCategoryForm">
          <input v-model="newCategoryName" placeholder="Category Name">
          <input v-model="newCategoryAmount" placeholder="Category Amount" type="number">
          <button @click="saveCategory">Submit</button>
        </div>
        <div v-if="showEditCategory" class="newCategoryForm">
          <input v-model="editCategoryName" placeholder="Category Name">
          <input v-model="editCategoryAmount" placeholder="Category Amount" type="number">
          <button @click="editCategory">Submit</button>
        </div>
        <div class="budgetHeader">
          <p>Name</p>
          <p>Amount</p>
        </div>
        <div v-if="showPlanned">
          <div class="categoryItems" v-for="(category, index) in categories" :key="index">
            <p>
              <span class="editSaveIcons" @click="toggleEditCategory(category.categoryID)"><i class="fa-solid fa-pencil"></i></span>
              <span class="editSaveIcons" @click="deleteCategory(category.categoryID)"><i class="fa-solid fa-trash"></i></span>
              {{ category.categoryName }}
            </p>
            <p>{{ category.categoryAmount }}</p>
          </div>
        </div>
        <div v-if="showSpent">
          <div class="categoryItems" v-for="(category, index) in categories" :key="index">
            <p>{{ category.categoryName }}</p>
            <p>{{ spentAmount(category.categoryID) }}</p>
          </div>
        </div>
        <div v-if="showRemaining">
          <div class="categoryItems" v-for="(category, index) in categories" :key="index">
            <p>{{ category.categoryName }}</p>
            <p>{{ remainingAmount(category.categoryID) }}</p>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<style scoped>
.budgetAmounts {
  background-color: black;
  padding: 10px;
  color: black;
  display: flex;
  justify-content: space-between;
  align-content: stretch;
}

.budgetAmountsButtons {
  flex: 1;
  border: 5px solid black;
  box-sizing: border-box;
  text-align: center;
  padding: 15px;
  background-color: antiquewhite;
  font-size: 20px;
  transition: background-color 0.3s;

  &:hover {
    background-color: chocolate;
    cursor: pointer;
  }
}

.selected {
  background-color: chocolate;
}

.addNewCategory {
  text-decoration: underline;
  font-size: 15px;
  color: black;
  margin-bottom: 10px;
  margin-right: 10px;
  &:hover {
    cursor: pointer;
  }
}

.categoryItems {
  display: flex;
  flex-direction: row;
  padding: 10px;
  margin-top: 10px;
  justify-content: space-between;
  background-color: darkgrey;
  font-size: 20px;
}

.newCategoryForm {
  display: flex;
  flex-direction: column;
  margin-top: 10px;
}

.newCategoryForm input {
  margin-bottom: 10px;
  padding: 8px;
}

.newCategoryForm button {
  padding: 10px;
  background-color: #4caf50;
  color: white;
  border: none;
  cursor: pointer;
}

.newCategoryForm button:hover {
  background-color: #45a049;
}

.editSaveIcons {
  padding: 5px;
  &:hover {
    cursor: pointer;
  }
}
.budgetHeader {
  display: flex;
  justify-content: space-between;
  font-size: 18px;
  border-bottom: 4px solid whitesmoke;
  margin-bottom: 5px;
}
</style>

<script>
import { PDFDocument, rgb } from "pdf-lib";
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
      showNewCategory: false,
      showEditCategory: false,
      showPlanned: true,
      showSpent: false,
      showRemaining: false,
      newCategoryName: '',
      newCategoryAmount: '',
      categories: [],
      transactions: [],
      editCategoryName: '',
      editCategoryAmount: '',
      categoryIDToEdit: null,
      categoryBeingEdited: {}
    };
  },
  components: {
    SideMenu
  },
  methods: {
    toggleNewCategory() {
      this.showNewCategory = !this.showNewCategory;
    },
    togglePlanned() {
      this.showPlanned = !this.showPlanned;
      this.showSpent = false;
      this.showRemaining = false
    },
    toggleSpent() {
      this.showSpent = !this.showSpent;
      this.showRemaining = false;
      this.showPlanned = false;
    },
    toggleRemaining() {
      this.showRemaining = !this.showRemaining;
      this.showSpent = false;
      this.showPlanned = false;
    },
    async toggleEditCategory(categoryID) {
      try {
        const response = await fetch(`/api/category?id=${categoryID}`, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json'
          }
        });
        if (!response.ok) {
          throw new Error(`Error fetching category: ${response.statusText}`);
        }
        const data = await response.json();
        this.categoryBeingEdited = data;
        this.editCategoryName = this.categoryBeingEdited.categoryName;
        this.editCategoryAmount = this.categoryBeingEdited.categoryAmount;
        this.showEditCategory = !this.showEditCategory;
        this.categoryIDToEdit = categoryID;
      } catch (error) {
        console.error('Toggle edit category error:', error);
      }
    },
    async saveCategory() {
      if (this.newCategoryName && this.newCategoryAmount) {
        const response = await fetch('/api/createNewCategory/save', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            categoryName: this.newCategoryName,
            categoryAmount: this.newCategoryAmount
          }),
        });
        if (response.ok) {
          this.$toast.open({
            message: 'Category created!',
            type: 'success',
          });
          this.newCategoryName = '';
          this.newCategoryAmount = '';
          this.showNewCategory = false;
          this.getCategories()
        }
      } else {
        this.$toast.open({
          message: 'You must input the category name and amount!',
          type: 'error',
        });
      }
    },
    async editCategory() {
      if (this.editCategoryName && this.editCategoryAmount) {
        const response = await fetch(`/api/editCategory?id=${this.categoryIDToEdit}&categoryName=${this.editCategoryName}&categoryAmount=${this.editCategoryAmount}`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            categoryName: this.editCategoryName,
            categoryAmount: this.editCategoryAmount
          }),
        });
        if (response.ok) {
          this.$toast.open({
            message: 'Category edited!',
            type: 'success',
          });
          this.editCategoryName = '';
          this.editCategoryAmount = '';
          this.showEditCategory = false;
          this.getCategories()
        }
      } else {
        this.$toast.open({
          message: 'You must input the category name and amount!',
          type: 'error',
        });
      }
    },
    async deleteCategory(categoryID) {
      const response = await fetch(`/api/deleteCategory?id=${categoryID}`, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json'
        }
      });
      if (response.ok) {
        this.$toast.open({
          message: 'Category deleted!',
          type: 'success',
        });
        this.getCategories()
      }
    },
    getCategories() {
      fetch('/api/allCategories')
          .then((response) => response.json()).then((data) => {
        this.categories = data;
      })
    },
    getTransactions() {
      fetch('/api/allTransactions')
          .then((response) => response.json()).then((data) => {
        this.transactions = data;
      })
    },
    spentAmount(categoryID) {
      let transactionToCalculate = [];
      for (let i = 0; i < this.transactions.length; i++) {
        if (this.transactions[i].categoryID === categoryID) {
          transactionToCalculate.push(this.transactions[i].transactionAmount)
        }
      }
      return transactionToCalculate.reduce((accumulator, currentValue) => {
        return accumulator + currentValue
      },0);
    },
    remainingAmount(categoryID) {
      const spentAmount = this.spentAmount(categoryID);
      const category = this.categories.find(cat => cat.categoryID === categoryID)
      return category ? category.categoryAmount - spentAmount : 0;
    },
    async deleteAllCategories() {
      const response = await fetch(`/api/deleteAllCategories`, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json'
        }
      });
      if (response.ok) {
        this.$toast.open({
          message: 'All categories deleted!',
          type: 'success',
        });
        this.getCategories()
      }
    },
    async generatePDF() {
      const pdfDoc = await PDFDocument.create();
      const page = pdfDoc.addPage([600, 800]);
      page.drawText('Budget Report', { x: 50, y: 750, fontColor: rgb(0, 0, 0), size: 20 });
      page.drawText('Date: ' + new Date().toLocaleDateString(), {x: 50, y:730, fontColor: rgb(0, 0, 0), size: 20})
      page.drawText('Category Name', { x: 50, y: 700, fontColor: rgb(0, 0, 0), size: 15 });
      page.drawText('Category Amount', { x: 250, y: 700, fontColor: rgb(0, 0, 0), size: 15 });
      const content = this.categories
      content.forEach((entry, index) => {
        page.drawText(entry.categoryName, { x: 50, y: 670 - index * 20, fontColor: rgb(0, 0, 0), size: 13 });
        page.drawText(entry.categoryAmount.toString(), { x: 250, y: 670 - index * 20, fontColor: rgb(0, 0, 0), size: 13 });
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
    this.getCategories()
    this.getTransactions()
  }
};
</script>