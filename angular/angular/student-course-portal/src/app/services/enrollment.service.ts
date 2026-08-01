import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Course } from '../models/course.model';
import { CourseService } from './course.service';

@Injectable({ providedIn: 'root' })
export class EnrollmentService {
  private enrolledCourseIds: number[] = [];

  // Service-to-service injection: EnrollmentService depends on CourseService
  // to resolve enrolled IDs into full Course objects.
  constructor(private courseService: CourseService) {}

  enroll(courseId: number): void {
    if (!this.enrolledCourseIds.includes(courseId)) {
      this.enrolledCourseIds.push(courseId);
    }
  }

  unenroll(courseId: number): void {
    this.enrolledCourseIds = this.enrolledCourseIds.filter((id) => id !== courseId);
  }

  isEnrolled(courseId: number): boolean {
    return this.enrolledCourseIds.includes(courseId);
  }

  getEnrolledCourses(): Observable<Course[]> {
    return this.courseService
      .getCourses()
      .pipe(map((courses) => courses.filter((c) => this.enrolledCourseIds.includes(c.id))));
  }

  getStudentsByCourse(courseId: number): Observable<{ id: number; name: string }[]> {
    // Placeholder demonstrating a dependent HTTP call chained with switchMap
    // from the component (see course-detail.component.ts).
    return this.courseService.getCourseById(courseId).pipe(map(() => []));
  }
}
