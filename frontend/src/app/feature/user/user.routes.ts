import { Routes } from '@angular/router';
import { redirectToHomeIfAuthenticated, redirectToLoginIfNotAuthenticated } from '@core/guards';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import LayoutComponent from '@core/layout/layout.component';

export default [
    {
        path: '',
        component: LayoutComponent,
        canActivate: [redirectToLoginIfNotAuthenticated()],
    },
    {
        path: 'login',
        component: LoginComponent,
        canActivate: [redirectToHomeIfAuthenticated()],
    },

    {
        path: 'signup',
        component: RegisterComponent,
    },
] as Routes;
