<template>
  <div style="display: flex; flex-direction: column; height: 100%; width: 150vh; flex: 1">
    <HeaderBar/>
    <div>
      <h1>Budget - </h1>
    </div>
    <div class="budgetAmounts">
      <div class="budgetAmountsButtons">Planned</div>
      <div class="budgetAmountsButtons">Spent</div>
      <div class="budgetAmountsButtons">Remaining</div>
    </div>
    <div class="addNewCategory" @click="toggleNewCategory">
      <span style="background-color: #4caf50; padding: 5px;">New category +</span>
    </div>
    <div v-if="showNewCategory" class="newCategoryForm">
      <input v-model="newCategoryName" placeholder="Category Name">
      <input v-model="newCategoryAmount" placeholder="Category Amount">
      <button @click="saveCategory">Submit</button>
    </div>
    <div v-if="showEditCategory" class="newCategoryForm">
      <input v-model="editCategoryName" placeholder="Category Name">
      <input v-model="editCategoryAmount" placeholder="Category Amount">
      <button @click="editCategory">Submit</button>
    </div>
    <div class="budgetHeader">
      <p>Name</p>
      <p>Amount</p>
    </div>
    <div class="categoryItems" v-for="(category, index) in categories" :key="index">
      <p>
        <span class="editSaveIcons" @click="toggleEditCategory(category.categoryID)"><i class="fa-solid fa-pencil"></i></span>
        <span class="editSaveIcons" @click="deleteCategory(category.categoryID)"><i class="fa-solid fa-trash"></i></span>
        {{ category.categoryName }}
      </p>
      <p>{{ category.categoryAmount }}</p>
    </div>
  </div>
</template>

<style>
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

.addNewCategory {
  text-decoration: underline;
  font-size: 15px;
  color: black;
  margin-bottom: 10px;

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
import HeaderBar from '../components/HeaderBar.vue'

export default {
  data() {
    return {
      showNewCategory: false,
      showEditCategory: false,
      newCategoryName: '',
      newCategoryAmount: '',
      categories: [],
      editCategoryName: '',
      editCategoryAmount: '',
      categoryIDToEdit: null,
      categoryBeingEdited: {}
    };
  },
  components: {
    HeaderBar
  },
  methods: {
    toggleNewCategory() {
      this.showNewCategory = !this.showNewCategory;
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
    }
  },
  mounted() {
    this.getCategories()
  }
};
</script>