import { inject } from '@angular/core';
import { ResolveFn } from '@angular/router';
import { MatriculaService } from './matricula.service';

export const CursosResolver: ResolveFn<any> = (route, state) => {
  let matriculaService = inject(MatriculaService);
  return matriculaService.getAllCursosMatriculados(route.paramMap.get('estudanteId'));
};
