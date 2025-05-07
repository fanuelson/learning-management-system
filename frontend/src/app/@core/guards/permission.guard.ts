import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { RoleService } from '@core/services/role.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthStateService } from '@core/services/auth-state.service';

export const PermissionGuard = (role: string): CanActivateFn => {
    return () => {
        const roleService = inject(RoleService);
        const router = inject(Router);
        const snackBar = inject(MatSnackBar);
        const authState = inject(AuthStateService);

        // Initialize auth state if needed
        authState.initializeFromStorage();

        // Get current user state
        const currentUser = authState.getCurrentUser()();

        if (!currentUser) {
            snackBar.open('Please login to continue.', 'Close', {
                duration: 3000,
                horizontalPosition: 'end'
            });
            return router.parseUrl('/login');
        }

        if (roleService.hasRole(role)) {
            return true;
        }

        snackBar.open('Access denied. You do not have permission to view this page.', 'Close', {
            duration: 3000,
            horizontalPosition: 'end'
        });

        return router.parseUrl('/');
    };
};
