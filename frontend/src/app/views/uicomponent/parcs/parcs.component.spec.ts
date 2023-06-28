import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParcsComponent } from './parcs.component';

describe('ParcsComponent', () => {
  let component: ParcsComponent;
  let fixture: ComponentFixture<ParcsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ParcsComponent]
    });
    fixture = TestBed.createComponent(ParcsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
