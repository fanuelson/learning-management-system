import { DecimalPipe, CommonModule } from '@angular/common';
import { Component, ViewChild, OnInit, inject, signal, computed, AfterViewInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, RouterModule } from '@angular/router';
import { MatPaginator, MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatSort, MatSortModule, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { NotificationService } from '@core/services/notification.service';
import { MatriculaService } from '../matricula.service';
import { HasRoleDirective } from '@core/directives/role.directive';
import { AuthStateService } from '@core/services/auth-state.service';

@Component({
    selector: 'app-curso-list',
    standalone: true,
    imports: [
        RouterModule,
        FormsModule,
        ReactiveFormsModule,
        CommonModule,
        MatPaginatorModule,
        MatFormFieldModule,
        MatInputModule,
        MatTableModule,
        MatIconModule,
        MatButtonModule,
        MatTooltipModule,
        MatSortModule,
        HasRoleDirective
    ],
    templateUrl: './curso-list.component.html',
    styleUrl: './curso-list.component.scss',
    providers: [DecimalPipe, MatriculaService]
})
export class CursoListComponent implements OnInit, AfterViewInit {
    private readonly matriculaService = inject(MatriculaService);
    private readonly activatedRoute = inject(ActivatedRoute);
    private readonly authState = inject(AuthStateService);
    private readonly router = inject(Router);
    private readonly notificationService = inject(NotificationService);

    cursos = signal<any[]>([]);
    filteredCursos = signal<any[]>([]);
    dataSource = signal<MatTableDataSource<any>>(new MatTableDataSource([]));
    displayedColumns = ['nome', 'dataInicio', 'dataConclusao', 'actions'];
    loading = signal<boolean>(false);

    // Pagination related signals
    pageSize = signal<number>(100);
    currentPage = signal<number>(0);
    pageSizeOptions = [5, 10, 25, 50, 100];
    totalItems = computed(() => this.filteredCursos().length);

    @ViewChild(MatSort) sort!: MatSort;
    @ViewChild(MatPaginator) paginator!: MatPaginator;

    ngOnInit(): void {
        if(!this.activatedRoute.snapshot.paramMap.get('estudanteId')) {
            const user = this.authState.getCurrentUser()();
            this.router.navigate([`/matriculas/${user.id}`]);
        } else{
            this.loadCursos();
        }
        
    }

    ngAfterViewInit() {
        // Connect the sort and paginator after the view is initialized
        if (this.dataSource()) {
            this.dataSource().sort = this.sort;
            this.dataSource().paginator = this.paginator;
        }
    }

    loadCursos(): void {
        this.loading.set(true);
        const data = this.activatedRoute.snapshot.data['cursos'];
        const sortedData = data.sort((a, b) =>
            new Date(b.dataInicio).getTime() - new Date(a.dataInicio).getTime()
        );
        this.cursos.set(sortedData);
        this.filteredCursos.set(sortedData);

        const dataSource = new MatTableDataSource(sortedData);
        this.dataSource.set(dataSource);

        // Initialize sort and paginator if they're available
        if (this.sort) {
            dataSource.sort = this.sort;
        }
        if (this.paginator) {
            dataSource.paginator = this.paginator;
        }

    }

    applyFilter(event: Event) {
        const filterValue = (event.target as HTMLInputElement).value;
        this.dataSource().filter = filterValue.trim().toLowerCase();

        // Reset to first page when filtering
        if (this.dataSource().paginator) {
            this.dataSource().paginator.firstPage();
        }
    }

    pageChanged(event: PageEvent): void {
        this.pageSize.set(event.pageSize);
        this.currentPage.set(event.pageIndex);

        // Update the data source's pagination parameters
        if (this.dataSource()) {
            // This ensures the MatTableDataSource is aware of the new page size
            this.dataSource().paginator = this.paginator;

            // Force the data source to reflect changes by resetting its data
            // This is important when items per page changes
            const currentData = this.dataSource().data;
            this.dataSource().data = [...currentData];
        }
    }

    sortData(sort: Sort): void {
        if (!sort.active || sort.direction === '') {
            // If sorting is cleared, revert to original data
            this.dataSource().data = this.filteredCursos();
            return;
        }

        this.dataSource().data = [...this.filteredCursos()].sort((a, b) => {
            const isAsc = sort.direction === 'asc';
            switch (sort.active) {
                case 'nome':
                    return this.compareNomes(a, b, isAsc);
                case 'dataInicio':
                    return this.compare(a.dataInicio, b.dataInicio, isAsc);
                default:
                    return 0;
            }
        });
    }

    compareNomes(a: any, b: any, isAsc: boolean): number {
        const nameA = `${a.nome}`.toLowerCase();
        const nameB = `${b.nome}`.toLowerCase();
        return (nameA < nameB ? -1 : 1) * (isAsc ? 1 : -1);
    }

    compare(a: string | number, b: string | number, isAsc: boolean): number {
        return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
    }

}
