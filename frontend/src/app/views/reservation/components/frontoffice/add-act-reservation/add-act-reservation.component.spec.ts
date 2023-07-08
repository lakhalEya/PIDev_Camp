import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddActReservationComponent } from './add-act-reservation.component';

describe('AddActReservationComponent', () => {
  let component: AddActReservationComponent;
  let fixture: ComponentFixture<AddActReservationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddActReservationComponent]
    });
    fixture = TestBed.createComponent(AddActReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
