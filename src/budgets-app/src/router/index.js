import { createRouter, createWebHistory } from 'vue-router'
import App from "@/App.vue";

const routes = [
    {
        path: '/',
        name: 'Home',
        component: App
    },
    {
        path: '/transactions',
        name: 'TransactionsPage',
        component: () => import('../views/TransactionsPage.vue')
    },
    {
        path: '/reports',
        name: 'ReportsPage',
        component: () => import('../views/ReportsPage.vue')
    },
    {
        path: '/budgets',
        name: 'BudgetsPage',
        component: () => import('../views/BudgetsPage.vue')
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router