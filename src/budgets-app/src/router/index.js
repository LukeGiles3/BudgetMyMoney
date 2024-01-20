import { createRouter, createWebHistory } from 'vue-router'
import {getAuth} from "firebase/auth";

const routes = [
    {
        path: '/',
        redirect: '/login'
    },
    {
        path: '/home',
        name: 'HomePage',
        component: () => import('../views/HomePage.vue'),
        meta: {
            authRequired: true
        }
    },
    {
        path: '/login',
        name: 'LoginPage',
        component: () => import('../views/LoginPage.vue')
    },
    {
        path: '/signup',
        name: 'SignUpPage',
        component: () => import('../views/SignUpPage.vue')
    },
    {
        path: '/transactions',
        name: 'TransactionsPage',
        component: () => import('../views/TransactionsPage.vue'),
        meta: {
            authRequired: true
        }
    },
    {
        path: '/budgets',
        name: 'BudgetsPage',
        component: () => import('../views/BudgetsPage.vue'),
        meta: {
            authRequired: true
        }
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.authRequired)) {
        const auth = getAuth();
        if (auth.currentUser) {
            next();
        } else {
            alert('You must logged in to see this page')
            next({
                path: '/login',
            });
        }
    } else {
        next();
    }
});

export default router