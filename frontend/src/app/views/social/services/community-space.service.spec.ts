import { TestBed } from '@angular/core/testing';

import { CommunitySpaceService } from './community-space.service';

describe('CommunitySpaceService', () => {
  let service: CommunitySpaceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CommunitySpaceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
