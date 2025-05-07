import { Component, inject, signal } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule, Location } from "@angular/common";
import { ReactiveFormsModule } from "@angular/forms";
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { NotificationService } from '@core/services/notification.service';
import { CursoService } from '@core/services/curso.service';
import { TarefaListComponent } from './tarefa-list/tarefa-list.component';

@Component({
    selector: 'app-curso-details',
    standalone: true,
    imports: [
        RouterModule,
        CommonModule,
        ReactiveFormsModule,
        MatIconModule,
        MatButtonModule,
        MatTooltipModule,
        MatSnackBarModule,
        TarefaListComponent
    ],
    templateUrl: './curso-details.component.html',
    styleUrl: './curso-details.component.scss',
    providers: [CursoService]
})
export class CursoDetailsComponent {
    private readonly activatedRoute = inject(ActivatedRoute);
    private readonly router = inject(Router);
    private readonly cursoService = inject(CursoService);
    private readonly notificationService = inject(NotificationService);
    private readonly location = inject(Location);

    curso = signal<any>(null);
    tarefas = signal<any>([]);
    loading = signal<boolean>(false);


    ngOnInit(): void {
        const cursoData = this.activatedRoute.snapshot.data['curso'];
        if (cursoData) {
            this.curso.set(cursoData);
        }
        const tarefasData = this.activatedRoute.snapshot.data['tarefas'];
        if(tarefasData) {
            this.tarefas.set(tarefasData);
        }
    }

    back(): void {
        const estudanteId = this.activatedRoute.snapshot.paramMap.get('estudanteId');
        this.router.navigateByUrl(`/matriculas/${estudanteId}`);
      }
}
