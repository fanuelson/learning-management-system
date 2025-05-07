import { CommonModule } from '@angular/common';
import { Component, OnInit, computed, inject, signal } from '@angular/core';
import {
    ReactiveFormsModule,
    UntypedFormBuilder,
    UntypedFormGroup,
    Validators,
} from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatIconModule } from '@angular/material/icon';
import { MatSelectModule } from '@angular/material/select';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ValidationService } from '@core/services/validation.service';

import { errorTailorImports } from "@core/components/validation";
import { NotificationService } from '@core/services/notification.service';
import { CursoService } from '../curso.service';

@Component({
    selector: 'app-curso-form',
    standalone: true,
    imports: [
        ReactiveFormsModule,
        RouterModule,
        CommonModule,
        errorTailorImports,
        MatFormFieldModule,
        MatInputModule,
        MatButtonModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatProgressSpinnerModule,
        MatIconModule,
        MatSelectModule
    ],
    templateUrl: './curso-form.component.html',
    styleUrl: './curso-form.component.scss',
    providers: [CursoService]
})
export class CursoFormComponent implements OnInit {
    private readonly fb = inject(UntypedFormBuilder);
    private readonly router = inject(Router);
    private readonly validationService = inject(ValidationService);
    private readonly cursoService = inject(CursoService);
    private readonly activatedRoute = inject(ActivatedRoute);
    private readonly notificationService = inject(NotificationService);

    cursoForm!: UntypedFormGroup;
    loading = signal<boolean>(false);
    isEditMode = signal<boolean>(false);
    curso = signal<any>(null);

    formValid = computed(() => this.cursoForm?.valid ?? false);

    onSubmit(): void {
        if (this.formValid()) {
            const curso = this.cursoForm.value;
            this.loading.set(true);

            if (this.isEditMode()) {
                this.cursoService.update(curso).subscribe({
                    next: () => {
                        this.notificationService.success('Salvo');
                        this.router.navigate(['/cursos']);
                    },
                    error: (error) => {
                        this.loading.set(false);
                        this.notificationService.error('Error');
                    }
                });
            } else {
                this.cursoService.create(curso).subscribe({
                    next: () => {
                        this.notificationService.success('Salvo');
                        this.router.navigate(['/cursos']);
                    },
                    error: (error) => {
                        this.loading.set(false);
                        this.notificationService.error('Error');
                    }
                });
            }
        }
    }

    createForm(): void {
        this.cursoForm = this.fb.group({
            id: ['', []],
            nome: [
                '',
                [
                    Validators.required,
                    Validators.minLength(2),
                    Validators.maxLength(35),
                ],
            ],
            dataInicio:[],
        });
    }

    reset(): void {
        const curso = this.cursoForm.value;
        if (curso.id) {
            this.getCursoDetails();
        } else {
            this.cursoForm.reset();
        }
    }
    submit(): void {
        const curso = this.cursoForm.value;
        if (curso.id) {
            this.update(curso);
        } else {
            delete curso.id;
            this.save(curso);
        }
    }

    save(curso: any): void {
        this.loading.set(true);
        this.cursoService.create(curso).subscribe({
            next: (data) => {
                this.notificationService.success('Salvo');
                this.router.navigate(['/cursos']);
            },
            error: (error) => {
                this.loading.set(false);
                this.notificationService.error('Error');
            }
        });
    }

    update(curso: any): void {
        this.loading.set(true);
        this.cursoService.update(curso).subscribe({
            next: (data) => {
                this.notificationService.success('Salvo');
                this.router.navigate(['/cursos']);
            },
            error: (error) => {
                this.loading.set(false);
                this.notificationService.error('Error');
            }
        });
    }

    ngOnInit(): void {
        this.createForm();
        this.getCursoDetails();
    }

    private getCursoDetails() {
        const cursoDetails = this.activatedRoute.snapshot.data.cursoDetails;
        if (cursoDetails) {
            this.curso.set(cursoDetails);
            this.isEditMode.set(true);
            this.cursoForm.patchValue(cursoDetails);
            this.cursoForm.controls.dataInicio.setValue(this.formatDate(cursoDetails.dataInicio));
        }
    }
    private formatDate(jsonDate: string): string {
      const date = new Date(jsonDate);
      return date.toISOString().split('T')[0]; // yyyy-MM-dd format
    }
}
