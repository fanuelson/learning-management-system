import { Routes } from '@angular/router';
import LayoutComponent from '@core/layout/layout.component';
import { EstudanteFormComponent } from './estudante-form/estudante-form.component';
import { redirectToLoginIfNotAuthenticated } from '@core/guards';

export default [
  {
    path: '',
    component: LayoutComponent,
    canActivate: [redirectToLoginIfNotAuthenticated()],
    children: [
      {
        path: '',
        component: EstudanteFormComponent,
      },
    ]
  }
] as Routes;
