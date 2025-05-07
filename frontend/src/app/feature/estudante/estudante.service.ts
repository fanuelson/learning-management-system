import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { catchError, map } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { environment } from "@environments/environment";

@Injectable({
  providedIn: 'root'
})
export class EstudanteService {
  private readonly http = inject(HttpClient);
  private readonly snackBar = inject(MatSnackBar);

  create(estudante: any) {
    return this.http
      .post(environment.apiEndpoint + '/estudantes', estudante)
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
