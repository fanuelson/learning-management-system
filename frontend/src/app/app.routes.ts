import { Routes } from '@angular/router';
export const routes: Routes = [
  {
    path: 'matriculas',
    loadChildren: () => import('@features/matricula/matricula.routes'),
  },
  {
    path: 'estudantes',
    loadChildren: () => import('@features/estudante/estudante.routes'),
  },
  {
    path: 'cursos',
    loadChildren: () => import('@features/curso/curso.routes'),
  },
  {
    path: '',
    loadChildren: () => import('@features/user/user.routes'),
  },
];
