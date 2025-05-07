import { Directive, Input, TemplateRef, ViewContainerRef, inject } from '@angular/core';
import { RoleService } from '@core/services/role.service';

@Directive({
    selector: '[hasRole]',
    standalone: true
})
export class HasRoleDirective {
    private permissionService = inject(RoleService);
    private viewContainer = inject(ViewContainerRef);
    private templateRef = inject(TemplateRef<any>);

    @Input() set hasRole(roles: string[]) {
        if (roles.some(role => this.permissionService.hasRole(role))) {
            this.viewContainer.createEmbeddedView(this.templateRef);
        } else {
            this.viewContainer.clear();
        }
    }

}
