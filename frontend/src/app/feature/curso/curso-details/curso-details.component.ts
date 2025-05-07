import { Component, inject, signal } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule } from "@angular/common";
import { ReactiveFormsModule } from "@angular/forms";
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { NotificationService } from '@core/services/notification.service';
import { CursoService } from '../curso.service';

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

    curso = signal<any>(null);
    loading = signal<boolean>(false);

    deleteCurso(): void {
        if (confirm('Certeza?')) {
            if (!this.curso()) return;

            this.loading.set(true);
            this.cursoService.delete(this.curso()!.id).subscribe({
                next: () => {
                    this.notificationService.success('Removido');
                    this.router.navigate(['/cursos']);
                },
                error: (error) => {
                    this.loading.set(false);
                    this.notificationService.error('Erro');
                }
            });
        }
    }

    ngOnInit(): void {
        const cursoData = this.activatedRoute.snapshot.data['cursoDetails'];
        if (cursoData) {
            this.curso.set(cursoData);
        }
    }
}
