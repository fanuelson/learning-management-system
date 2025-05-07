import { Routes } from '@angular/router';
import LayoutComponent from '@core/layout/layout.component';
import { CursoListComponent } from './curso-list/curso-list.component';
import { redirectToLoginIfNotAuthenticated } from '@core/guards';
import { PermissionGuard } from '@core/guards/permission.guard';
import { CursosResolver } from './cursos.resolver';

export default [
  {
    path: '',
    component: LayoutComponent,
    canActivate: [redirectToLoginIfNotAuthenticated()],
    children: [
      {
        path: '',
        component: CursoListComponent,
        canActivate: [PermissionGuard('ESTUDANTE')]
      },
      {
        path: ':estudanteId',
        component: CursoListComponent,
        canActivate: [PermissionGuard('ESTUDANTE')],
        resolve: { cursos: CursosResolver }
      }
    ]
  }
] as Routes;
