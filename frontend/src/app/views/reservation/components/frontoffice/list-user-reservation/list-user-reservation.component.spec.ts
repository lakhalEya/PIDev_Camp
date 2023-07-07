import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListUserReservationComponent } from './list-user-reservation.component';

describe('ListUserReservationComponent', () => {
  let component: ListUserReservationComponent;
  let fixture: ComponentFixture<ListUserReservationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListUserReservationComponent]
    });
    fixture = TestBed.createComponent(ListUserReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
