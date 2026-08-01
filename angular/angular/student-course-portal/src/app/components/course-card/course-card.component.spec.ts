import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { CourseCardComponent } from './course-card.component';
import { EnrollmentService } from '../../services/enrollment.service';
import { Course } from '../../models/course.model';

describe('CourseCardComponent', () => {
  let fixture: ComponentFixture<CourseCardComponent>;
  let component: CourseCardComponent;
  const mockCourse: Course = { id: 1, name: 'Data Structures', code: 'CS101', credits: 4, gradeStatus: 'passed' };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CourseCardComponent],
      providers: [EnrollmentService]
    }).compileComponents();

    fixture = TestBed.createComponent(CourseCardComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render the course name from @Input', () => {
    component.course = mockCourse;
    fixture.detectChanges();
    const h3 = fixture.debugElement.query(By.css('h3')).nativeElement as HTMLElement;
    expect(h3.textContent).toContain('Data Structures');
  });

  it('should emit enrollRequested with the course id when Enroll is clicked', () => {
    component.course = mockCourse;
    fixture.detectChanges();
    spyOn(component.enrollRequested, 'emit');
    const btn = fixture.debugElement.query(By.css('button')).nativeElement as HTMLButtonElement;
    btn.click();
    fixture.detectChanges();
    expect(component.enrollRequested.emit).toHaveBeenCalledWith(1);
  });

  it('should log on ngOnChanges', () => {
    spyOn(console, 'log');
    component.course = mockCourse;
    component.ngOnChanges({
      course: {
        previousValue: undefined,
        currentValue: mockCourse,
        firstChange: true,
        isFirstChange: () => true
      }
    });
    expect(console.log).toHaveBeenCalled();
  });
});
