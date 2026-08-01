import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { EnrollmentFormComponent } from './enrollment-form.component';

describe('EnrollmentFormComponent', () => {
  let fixture: ComponentFixture<EnrollmentFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EnrollmentFormComponent, HttpClientTestingModule]
    }).compileComponents();
    fixture = TestBed.createComponent(EnrollmentFormComponent);
  });

  it('should create', () => {
    expect(fixture.componentInstance).toBeTruthy();
  });
});
