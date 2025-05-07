import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { catchError, map } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { environment } from "@environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CursoService {
  private readonly http = inject(HttpClient);
  private readonly snackBar = inject(MatSnackBar);

  getAll() {
    return this.http.get<any[]>(environment.apiEndpoint + '/cursos').pipe(
      map((res: any) => res),
      catchError(this.handleErrorObservable)
    );
  }

  getById(_id: string) {
    return this.http.get(environment.apiEndpoint + '/cursos/' + _id).pipe(
      map((res: any) => res),
      catchError(this.handleErrorObservable)
    );
  }

  create(curso: any) {
    return this.http
      .post(environment.apiEndpoint + '/cursos', curso)
      .pipe(
        map((res: any) => res),
        catchError(this.handleErrorObservable)
      );
  }

  update(curso: any) {
    return this.http
      .put(environment.apiEndpoint + '/cursos/' + curso.id, curso)
      .pipe(
        map((res: any) => res),
        catchError(this.handleErrorObservable)
      );
  }

  delete(_id: string) {
    return this.http
      .delete(environment.apiEndpoint + '/cursos/' + _id)
      .pipe(
        map((res: any) => res),
        catchError(this.handleErrorObservable)
      );
  }

  matricular(estudanteId: string, cursoId: string) {
    return this.http
      .post(environment.apiEndpoint + `/estudantes/${estudanteId}/cursos/${cursoId}/matricular`, {})
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
