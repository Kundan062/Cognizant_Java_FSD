import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CourseService } from './course.service';
import { environment } from '../../environments/environment';
import { Course } from '../models/course.model';

describe('CourseService', () => {
  let service: CourseService;
  let httpMock: HttpTestingController;
  const mockCourses: Course[] = [
    { id: 1, name: 'Data Structures', code: 'CS101', credits: 4, gradeStatus: 'passed' },
    { id: 2, name: 'Databases', code: 'CS103', credits: 3, gradeStatus: 'pending' }
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [CourseService]
    });
    service = TestBed.inject(CourseService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => httpMock.verify());

  it('should fetch courses via GET', () => {
    service.getCourses().subscribe((courses) => expect(courses.length).toBe(2));
    const req = httpMock.expectOne(`${environment.apiUrl}/courses`);
    expect(req.request.method).toBe('GET');
    req.flush(mockCourses);
  });

  it('should propagate a friendly error message on server failure', () => {
    service.getCourses().subscribe({
      next: () => fail('expected an error'),
      error: (err) => expect(err.message).toContain('Failed to load courses')
    });
    // retry(2) means the request is retried twice before catchError fires: 3 requests total.
    for (let i = 0; i < 3; i++) {
      httpMock.expectOne(`${environment.apiUrl}/courses`).flush('error', { status: 500, statusText: 'Server Error' });
    }
  });
});
