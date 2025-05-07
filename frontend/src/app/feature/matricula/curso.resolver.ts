import { inject } from '@angular/core';
import { ResolveFn } from '@angular/router';
import { CursoService } from '@core/services/curso.service';

export const CursoDetailsResolver: ResolveFn<any> = (route, state) => {
  let cursoService = inject(CursoService);
  return cursoService.getById(route.paramMap.get('cursoId'));
};
