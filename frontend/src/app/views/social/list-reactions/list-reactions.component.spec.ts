import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListReactionsComponent } from './list-reactions.component';

describe('ListReactionsComponent', () => {
  let component: ListReactionsComponent;
  let fixture: ComponentFixture<ListReactionsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListReactionsComponent]
    });
    fixture = TestBed.createComponent(ListReactionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
