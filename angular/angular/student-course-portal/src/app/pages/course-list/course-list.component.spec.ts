import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideMockStore, MockStore } from '@ngrx/store/testing';
import { provideRouter } from '@angular/router';
import { CourseListComponent } from './course-list.component';
import { selectAllCourses, selectCoursesLoading } from '../../store/course/course.selectors';
import { selectEnrolledIds } from '../../store/enrollment/enrollment.selectors';

describe('CourseListComponent (NgRx-connected)', () => {
  let fixture: ComponentFixture<CourseListComponent>;
  let store: MockStore;
  const mockCourses = [{ id: 1, name: 'Data Structures', code: 'CS101', credits: 4, gradeStatus: 'passed' as const }];

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CourseListComponent],
      providers: [
        provideRouter([]),
        provideMockStore({
          initialState: { course: { courses: mockCourses, loading: false, error: null }, enrollment: { enrolledCourseIds: [] } }
        })
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(CourseListComponent);
    store = TestBed.inject(MockStore);
  });

  it('renders course cards from initial store state', () => {
    fixture.detectChanges();
    const cards = fixture.nativeElement.querySelectorAll('app-course-card');
    expect(cards.length).toBe(1);
  });

  it('shows the loading indicator when loading is true', () => {
    store.overrideSelector(selectCoursesLoading, true);
    store.overrideSelector(selectAllCourses, []);
    store.overrideSelector(selectEnrolledIds, []);
    store.refreshState();
    fixture.detectChanges();
    expect(fixture.nativeElement.textContent).toContain('Loading courses...');
  });
});
