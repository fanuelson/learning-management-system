import { inject } from '@angular/core';
import { ResolveFn } from '@angular/router';
import { TarefaService } from './tarefa.service';

export const TarefaDetailsResolver: ResolveFn<any> = (route, state) => {
  let tarefaService = inject(TarefaService);
  return tarefaService.getById(route.paramMap.get('tarefaId'));
};
