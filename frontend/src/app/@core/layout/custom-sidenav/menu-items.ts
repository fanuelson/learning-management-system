import { Injectable, inject, signal, computed } from '@angular/core';
import { RoleService } from '@core/services/role.service';
import { AuthStateService } from '@core/services/auth-state.service';

export interface MenuItem {
  icon: string;
  label: string;
  route?: string;
  exact?: boolean;
  subItems?: MenuItem[];
  pageOrder?: number; // Added for explicit ordering
  role?: string;
}

const defaultMenuItems: MenuItem[] = [
  {
    icon: 'cursos',
    label: 'Cursos',
    route: '/cursos',
    exact: true
  },
  {
    icon: 'estudantes',
    label: 'Estudantes',
    route: '/estudantes',
    exact: true,
    role: 'ADMIN'
  },
  {
    icon: 'matriculas',
    label: 'Matriculas',
    route: '/matriculas',
    exact: true,
    role: 'ESTUDANTE'
  },

];

@Injectable({
  providedIn: 'root'
})
export class MenuService {
  private roleService = inject(RoleService);
  private authState = inject(AuthStateService);
  
  // Using signals for reactive state management
  private menuItemsSignal = signal<MenuItem[]>([...defaultMenuItems]);
  menuItems = computed(() => this.menuItemsSignal());

  getMenuItems(): MenuItem[] {
    const menuItems = [...defaultMenuItems];
    const user = this.authState.getCurrentUser()();

    if (!user?.roles) {
      return menuItems;
    }

    return this.sortMenuItems(menuItems);
  }

  private sortMenuItems(items: MenuItem[]): MenuItem[] {
    // Define default order for common menu items
    const defaultOrderMap: Record<string, number> = {
      'Dashboard': 10,
      'Cursos': 30,
    };
    
    return items.sort((a, b) => {
      // Special case: Dashboard always comes first
      if (a.label === 'Dashboard') return -1;
      if (b.label === 'Dashboard') return 1;
      
      // First, use explicit pageOrder if available
      if (a.pageOrder !== undefined && b.pageOrder !== undefined) {
        return a.pageOrder - b.pageOrder;
      }
      
      // If only one item has pageOrder, prioritize it
      if (a.pageOrder !== undefined) return -1;
      if (b.pageOrder !== undefined) return 1;
      
      // Otherwise, use the predefined order map
      const orderA = defaultOrderMap[a.label] || 500;
      const orderB = defaultOrderMap[b.label] || 500;
      
      if (orderA !== orderB) {
        return orderA - orderB;
      }
      
      // As a last resort, sort alphabetically
      return a.label.localeCompare(b.label);
    });
  }

}
