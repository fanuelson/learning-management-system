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
      },
      {
        path: 'create',
        component: CursoFormComponent,
      },
      {
        path: 'edit/:cursoId',
        component: CursoFormComponent,
        resolve: { cursoDetails: CursoDetailsResolver },
        
      },
      {
        path: 'details/:cursoId',
        component: CursoDetailsComponent,
        resolve: { cursoDetails: CursoDetailsResolver }
      }
    ]
  }
] as Routes;
