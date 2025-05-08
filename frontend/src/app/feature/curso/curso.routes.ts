import { Routes } from '@angular/router';
import LayoutComponent from '@core/layout/layout.component';
import { CursoDetailsResolver } from './curso.resolver';
import { CursoFormComponent } from './curso-form/curso-form.component';
import { CursoListComponent } from './curso-list/curso-list.component';
import { CursoDetailsComponent } from './curso-details/curso-details.component';
import { PermissionGuard } from '@core/guards/permission.guard';

export default [
  {
    path: '',
    component: LayoutComponent,
    // canActivate: [redirectToLoginIfNotAuthenticated()],
    children: [
      {
        path: '',
        component: CursoListComponent,
        canActivate: [PermissionGuard('ADMIN')]
      },
      {
        path: 'create',
        component: CursoFormComponent,
        canActivate: [PermissionGuard('ADMIN')]
      },
      {
        path: 'edit/:cursoId',
        component: CursoFormComponent,
        canActivate: [PermissionGuard('ADMIN')],
        resolve: { cursoDetails: CursoDetailsResolver },
        
      },
      {
        path: 'details/:cursoId',
        component: CursoDetailsComponent,
        canActivate: [PermissionGuard('ADMIN')],
        resolve: { cursoDetails: CursoDetailsResolver }
      }
    ]
  }
] as Routes;
