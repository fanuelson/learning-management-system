import { Injectable, inject } from '@angular/core';
import { AuthStateService } from './auth-state.service';
import { User2 } from '@core/models/user.interface';

@Injectable({
  providedIn: 'root',
})
export class RoleService {
  private authState = inject(AuthStateService);

  hasRole(role: string): boolean {
    const user = this.authState.getCurrentUser()() as User2;
    if (!user?.roles) return false;

    return user.roles.some((p) => p.toLowerCase() === role.toLowerCase());
  }
}
