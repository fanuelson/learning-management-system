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
import { MatriculaService } from '@features/matricula/matricula.service';
import { TarefaService } from '@features/matricula/tarefa.service';

@Component({
    selector: 'app-tarefa-form',
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
    templateUrl: './tarefa-form.component.html',
    styleUrl: './tarefa-form.component.scss',
    providers: [MatriculaService]
})
export class TarefaFormComponent implements OnInit {
    private readonly fb = inject(UntypedFormBuilder);
    private readonly router = inject(Router);
    private readonly validationService = inject(ValidationService);
    private readonly matriculaService = inject(MatriculaService);
    private readonly tarefaService = inject(TarefaService);
    private readonly activatedRoute = inject(ActivatedRoute);
    private readonly notificationService = inject(NotificationService);

    form!: UntypedFormGroup;
    loading = signal<boolean>(false);
    isEditMode = signal<boolean>(false);
    obj = signal<any>(null);

    estudanteId = signal<any>(null);
    cursoId = signal<any>(null);

    formValid = computed(() => this.form?.valid ?? false);

    onSubmit(): void {
        if (this.formValid()) {
            const tarefa = this.form.value;
            this.loading.set(true);

            if (this.isEditMode()) {
                this.tarefaService.update(tarefa).subscribe({
                    next: () => {
                        this.notificationService.success('Salvo');
                        this.back()
                    },
                    error: (error) => {
                        this.loading.set(false);
                        this.notificationService.error('Error');
                    }
                });
            } else {
                this.matriculaService.createTarefa(this.estudanteId(), this.cursoId(), tarefa).subscribe({
                    next: () => {
                        this.notificationService.success('Salvo');
                        this.back();
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
        this.form = this.fb.group({
            id: ['', []],
            categoriaTarefa: [
                '',
                [
                    Validators.required,
                    Validators.minLength(2),
                    Validators.maxLength(35),
                ],
            ],
            data: [],
            descricao: [
                '',
                [
                    Validators.required,
                    Validators.minLength(2),
                    Validators.maxLength(35),
                ],
            ],
            
        });
    }

    reset(): void {
        const tarefa = this.form.value;
        if (tarefa.id) {
            this.getTarefaDetails();
        } else {
            this.form.reset();
        }
    }
    submit(): void {
        const tarefa = this.form.value;
        if (tarefa.id) {
            this.update(tarefa);
        } else {
            delete tarefa.id;
            this.save(tarefa);
        }
    }

    save(tarefa: any): void {
        this.loading.set(true);
        this.matriculaService.createTarefa(this.estudanteId(), this.cursoId(), tarefa).subscribe({
            next: (data) => {
                this.notificationService.success('Salvo');
                this.back()
            },
            error: (error) => {
                this.loading.set(false);
                this.notificationService.error('Error');
            }
        });
    }

    update(tarefa: any): void {
        this.loading.set(true);
        this.tarefaService.update(tarefa).subscribe({
            next: (data) => {
                this.notificationService.success('Salvo');
                this.back()
            },
            error: (error) => {
                this.loading.set(false);
                this.notificationService.error('Error');
            }
        });
    }

    ngOnInit(): void {
        this.estudanteId.set(this.activatedRoute.snapshot.paramMap.get('estudanteId'));
        this.cursoId.set(this.activatedRoute.snapshot.paramMap.get('cursoId'));
        this.createForm();
        this.getTarefaDetails();
    }

    private getTarefaDetails() {
        const tarefaDetails = this.activatedRoute.snapshot.data.tarefaDetails;
        if (tarefaDetails) {
            this.obj.set(tarefaDetails);
            this.isEditMode.set(true);
            this.form.patchValue(tarefaDetails);
            this.form.controls.data.setValue(this.formatDate(tarefaDetails.data));
        }
    }
    private formatDate(jsonDate: string): string {
      const date = new Date(jsonDate);
      return date.toISOString().split('T')[0]; // yyyy-MM-dd format
    }

    back(): void {
        this.router.navigateByUrl(`/matriculas/${this.estudanteId()}/cursos/${this.cursoId()}`);
    }
}
