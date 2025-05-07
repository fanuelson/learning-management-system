import { Routes } from '@angular/router';
import LayoutComponent from '@core/layout/layout.component';
import { CursoListComponent } from './curso-list/curso-list.component';
import { redirectToLoginIfNotAuthenticated } from '@core/guards';
import { PermissionGuard } from '@core/guards/permission.guard';
import { CursosResolver } from './cursos.resolver';
import { CursoDetailsResolver } from './curso.resolver';
import { CursoDetailsComponent } from './curso-details/curso-details.component';
import { TarefasResolver } from './tarefas.resolver';
import { TarefaFormComponent } from './curso-details/tarefa-form/tarefa-form.component';
import { TarefaDetailsResolver } from './tarefa.resolver';

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
      },
      {
        path: ':estudanteId/cursos',
        component: CursoListComponent,
        canActivate: [PermissionGuard('ESTUDANTE')],
        resolve: { cursos: CursosResolver }
      },
      {
        path: ':estudanteId/cursos/:cursoId',
        component: CursoDetailsComponent,
        canActivate: [PermissionGuard('ESTUDANTE')],
        resolve: { curso: CursoDetailsResolver, tarefas: TarefasResolver }
      },
      {
        path: ':estudanteId/cursos/:cursoId/tarefas/create',
        component: TarefaFormComponent,
        canActivate: [PermissionGuard('ESTUDANTE')]
      },
      {
        path: ':estudanteId/cursos/:cursoId/tarefas/:tarefaId',
        component: TarefaFormComponent,
        canActivate: [PermissionGuard('ESTUDANTE')],
        resolve: { tarefaDetails: TarefaDetailsResolver }
      },

    ]
  }
] as Routes;
