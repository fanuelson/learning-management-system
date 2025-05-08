import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { catchError, map } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { environment } from "@environments/environment";

@Injectable({
  providedIn: 'root'
})
export class TarefaService {
  private readonly http = inject(HttpClient);
  private readonly snackBar = inject(MatSnackBar);

  getById(_id: string) {
    return this.http.get(environment.apiEndpoint + '/tarefas/' + _id).pipe(
      map((res: any) => res),
      catchError(this.handleErrorObservable)
    );
  }

  update(tarefa: any) {
    return this.http
      .put(environment.apiEndpoint + '/tarefas/' + tarefa.id, tarefa)
      .pipe(
        map((res: any) => res),
        catchError(this.handleErrorObservable)
      );
  }

  delete(_id: string) {
    return this.http
      .delete(environment.apiEndpoint + '/tarefas/' + _id)
      .pipe(
        map((res: any) => res),
        catchError(this.handleErrorObservable)
      );
  }

  adicionarTempoGasto(id: any, tempoGastoEmMinutos = 30) {
    return this.http
      .patch(environment.apiEndpoint + `/tarefas/${id}/tempo-gasto`, {tempoGastoEmMinutos})
      .pipe(
        map((res: any) => res),
        catchError(this.handleErrorObservable)
      );
  }

  showNotification(message: string, isError = false) {
    this.snackBar.open(message, 'Close', {
      duration: 3000,
      horizontalPosition: 'end',
      verticalPosition: 'top',
      panelClass: isError ? ['bg-error', 'text-on-error'] : ['bg-success', 'text-on-success']
    });
  }

  private handleErrorObservable(error: any) {
    return throwError(() => error);
  }
}
