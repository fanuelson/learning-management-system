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
import { RouterModule } from '@angular/router';
import { ValidationService } from '@core/services/validation.service';

import { errorTailorImports } from "@core/components/validation";
import { NotificationService } from '@core/services/notification.service';
import { EstudanteService } from '../estudante.service';

@Component({
    selector: 'app-estudante-form',
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
    templateUrl: './estudante-form.component.html',
    styleUrl: './estudante-form.component.scss',
    providers: [EstudanteService]
})
export class EstudanteFormComponent implements OnInit {
    private readonly fb = inject(UntypedFormBuilder);
    private readonly validationService = inject(ValidationService);
    private readonly estudanteService = inject(EstudanteService);
    private readonly notificationService = inject(NotificationService);

    estudanteForm!: UntypedFormGroup;
    loading = signal<boolean>(false);
    isEditMode = signal<boolean>(false);
    estudante = signal<any>(null);

    formValid = computed(() => this.estudanteForm?.valid ?? false);

    onSubmit(): void {
        if (this.formValid()) {
            const estudante = this.estudanteForm.value;
            this.loading.set(true);

            this.estudanteService.create(estudante).subscribe({
                next: () => {
                    this.notificationService.success('Estudante registrado com sucesso');
                    this.loading.set(false);
                    this.createForm();
                },
                error: (error) => {
                    this.loading.set(false);
                    this.notificationService.error('Erro ao registrar estudante');
                }
            });
        }
    }

    createForm(): void {
        this.estudanteForm = this.fb.group({
            email: [
                '',
                [Validators.required, this.validationService.emailValidator],
            ],
            primeiroNome: [
                '',
                [
                    Validators.required,
                    Validators.minLength(2),
                    Validators.maxLength(35),
                ],
            ],
            ultimoNome: [
                '',
                [
                    Validators.required,
                    Validators.minLength(2),
                    Validators.maxLength(35),
                ],
            ],
            dataNascimento:[],
            telefone: ['', [Validators.required]],
        });
    }

    reset(): void {
        this.estudanteForm.reset();
    }

    submit(): void {
        const estudante = this.estudanteForm.value;
        delete estudante.id;
        this.save(estudante);
    }

    save(estudante: any): void {
        this.loading.set(true);
        this.estudanteService.create(estudante).subscribe({
            next: (data) => {
                this.notificationService.success('Estudante registrado com sucesso');
                this.loading.set(false);
            },
            error: (error) => {
                this.loading.set(false);
                this.notificationService.error('Erro ao registrar estudante');
            }
        });
    }

    ngOnInit(): void {
        this.createForm();
    }

}
