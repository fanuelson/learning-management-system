import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { signal, computed, inject } from '@angular/core';
import { UserService } from '@core/services/user.service';
import { RouterModule, Router } from "@angular/router";
import { CommonModule } from "@angular/common";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule } from "@angular/material/button";
import { MatProgressSpinnerModule } from "@angular/material/progress-spinner";
import { MatCheckboxModule } from '@angular/material/checkbox';
import { errorTailorImports } from '@core/components/validation';
import { NotificationService } from '@core/services/notification.service';
import { ValidationService } from '@core/services/validation.service';
import { EstudanteService } from '../estudante.service';
import { MatDatepickerModule } from '@angular/material/datepicker';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
  standalone: true,
  imports: [
    RouterModule,
    ReactiveFormsModule,
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    MatDatepickerModule,
    MatProgressSpinnerModule,
    MatCheckboxModule,
    errorTailorImports
  ],
})
export class RegisterComponent {
  // Inject dependencies
  private fb = inject(FormBuilder);
  private notificationService = inject(NotificationService);
  private readonly estudanteService = inject(EstudanteService);
  private router = inject(Router);

  // State with signals
  hidePassword = signal<boolean>(true);
  loading = signal<boolean>(false);

  // Form with validation
  registerForm: FormGroup = this.fb.group({
    email: [
        '',
        [Validators.required, Validators.email],
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


  // Submit handler
  onSubmit(): void {
    if (this.registerForm.valid) {
      this.loading.set(true);

      // Create user and handle response
      this.estudanteService.create(this.registerForm.value).subscribe({
        next: (response) => {
          this.loading.set(false);
          this.notificationService.success('Estudante registrado com sucesso');
          this.router.navigate(['/login']);
        },
        error: (error) => {
          this.loading.set(false);
          const errorMessage = error?.error?.errors 
            || error?.error?.message
            || 'Erro';
          this.notificationService.error(errorMessage);
        }
      });
    } else {
      // Mark all fields as touched to show validation errors
      Object.keys(this.registerForm.controls).forEach(key => {
        this.registerForm.get(key)?.markAsTouched();
      });
    }
  }
}
