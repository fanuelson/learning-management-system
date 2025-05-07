import { inject } from '@angular/core';
import { ResolveFn } from '@angular/router';
import { MatriculaService } from './matricula.service';

export const TarefasResolver: ResolveFn<any> = (route, state) => {
  let matriculaService = inject(MatriculaService);
  return matriculaService.getAllTarefas(route.paramMap.get('estudanteId'), route.paramMap.get('cursoId'));
};
