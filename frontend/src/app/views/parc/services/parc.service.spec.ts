import { TestBed } from '@angular/core/testing';

import { ParcService } from './parc.service';

describe('ParcService', () => {
  let service: ParcService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ParcService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
