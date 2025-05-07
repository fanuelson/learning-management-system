import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TarefaListComponent } from './tarefa-list.component';


describe('CursoListComponent', () => {
  let component: TarefaListComponent;
  let fixture: ComponentFixture<TarefaListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TarefaListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TarefaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
