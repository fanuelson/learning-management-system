import { Routes } from '@angular/router';
import LayoutComponent from '@core/layout/layout.component';
import { CursoDetailsResolver } from './curso.resolver';
import { CursoFormComponent } from './curso-form/curso-form.component';
import { CursoListComponent } from './curso-list/curso-list.component';
import { CursoDetailsComponent } from './curso-details/curso-details.component';

export default [
  {
    path: '',
    component: LayoutComponent,
    // canActivate: [redirectToLoginIfNotAuthenticated()],
    children: [
      {
        path: '',
        component: CursoListComponent,
        // canActivate: [PermissionGuard('Contacts', 'Read')]
      },
      {
        path: 'create',
        component: CursoFormComponent,
        // canActivate: [PermissionGuard('Contacts', 'Create')]
      },
      {
        path: 'edit/:cursoId',
        component: CursoFormComponent,
        // canActivate: [PermissionGuard('Contacts', 'Update')],
        resolve: { cursoDetails: CursoDetailsResolver }
      },
      {
        path: 'details/:cursoId',
        component: CursoDetailsComponent,
        // canActivate: [PermissionGuard('Contacts', 'Read')],
        resolve: { cursoDetails: CursoDetailsResolver }
      }
    ]
  }
] as Routes;
