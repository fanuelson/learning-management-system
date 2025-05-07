import { DecimalPipe, CommonModule } from '@angular/common';
import { Component, ViewChild, OnInit, inject, signal, computed, AfterViewInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { MatPaginator, MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { NotificationService } from '@core/services/notification.service';
import { MatriculaService } from '@features/matricula/matricula.service';
import { TarefaService } from '@features/matricula/tarefa.service';

@Component({
    selector: 'app-tarefa-list',
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
    ],
    templateUrl: './tarefa-list.component.html',
    styleUrl: './tarefa-list.component.scss',
    providers: [DecimalPipe, MatriculaService, TarefaService]
})
export class TarefaListComponent implements OnInit, AfterViewInit {
    private readonly matriculaService = inject(MatriculaService);
    private readonly tarefaService = inject(TarefaService);
    private readonly router = inject(Router);
    private readonly activatedRoute = inject(ActivatedRoute);
    private readonly notificationService = inject(NotificationService);

    tarefas = signal<any[]>([]);
    dataSource = signal<MatTableDataSource<any>>(new MatTableDataSource([]));
    displayedColumns = ['categoriaTarefa', 'descricao', 'actions'];
    loading = signal<boolean>(false);

    // Pagination related signals
    pageSize = signal<number>(100);
    currentPage = signal<number>(0);
    pageSizeOptions = [5, 10, 25, 50, 100];
    totalItems = computed(() => this.tarefas().length);

    @ViewChild(MatSort) sort!: MatSort;
    @ViewChild(MatPaginator) paginator!: MatPaginator;

    ngOnInit(): void {
        this.loadTarefas();
    }

    ngAfterViewInit() {
        // Connect the sort and paginator after the view is initialized
        if (this.dataSource()) {
            this.dataSource().sort = this.sort;
            this.dataSource().paginator = this.paginator;
        }
    }

    loadTarefas(): void {
        this.loading.set(true);
        const estudanteId = this.activatedRoute.snapshot.paramMap.get("estudanteId");
        const cursoId = this.activatedRoute.snapshot.paramMap.get("cursoId");
        const data = this.activatedRoute.snapshot.data['tarefas']
        if(data) {
            this.setTarefasAndDataSource(data);
        } else {
            this.matriculaService.getAllTarefas(estudanteId, cursoId).subscribe({
                next: (data) => {
                    this.setTarefasAndDataSource(data);
                    this.loading.set(false);
                },
                error: (error) => {
                    this.notificationService.error('Erro ao buscar cursos');
                    this.loading.set(false);
                }
            });
        }
        
    }

    setTarefasAndDataSource(data) {
        const sortedData = data.sort((a, b) =>
            new Date(b.data).getTime() - new Date(a.data).getTime()
        );
        this.tarefas.set(sortedData);

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


    delete(id: any): void {
        if (confirm('Confirme')) {
            this.tarefaService.delete(id).subscribe({
                next: () => {
                    this.notificationService.success('Ok');
                    this.loadTarefas();
                },
                error: (error) => {
                    this.notificationService.error(`Erro ao remover curso: ${error.error?.errors}`);
                }
            });
        }
    }
}
