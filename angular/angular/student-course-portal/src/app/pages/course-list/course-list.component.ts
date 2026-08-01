import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Course } from '../../models/course.model';
import { CourseCardComponent } from '../../components/course-card/course-card.component';
import { loadCourses } from '../../store/course/course.actions';
import { selectAllCourses, selectCoursesError, selectCoursesLoading } from '../../store/course/course.selectors';
import { selectEnrolledIds } from '../../store/enrollment/enrollment.selectors';
import { enrollInCourse } from '../../store/enrollment/enrollment.actions';

@Component({
  selector: 'app-course-list',
  standalone: true,
  imports: [CommonModule, FormsModule, CourseCardComponent],
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {
  courses$: Observable<Course[]> = this.store.select(selectAllCourses);
  loading$: Observable<boolean> = this.store.select(selectCoursesLoading);
  error$: Observable<string | null> = this.store.select(selectCoursesError);
  enrolledIds$: Observable<number[]> = this.store.select(selectEnrolledIds);

  searchTerm = '';
  selectedCourseId: number | null = null;

  constructor(private store: Store, private router: Router, private route: ActivatedRoute) {}

  ngOnInit(): void {
    // Dispatch the load action — the CourseEffects listens for this,
    // calls the HTTP API, and dispatches success/failure in response.
    this.store.dispatch(loadCourses());
    this.searchTerm = this.route.snapshot.queryParamMap.get('search') ?? '';
  }

  trackByCourseId(index: number, course: Course): number {
    return course.id;
  }

  onSearch(): void {
    this.router.navigate(['courses'], { queryParams: { search: this.searchTerm || null } });
  }

  goToCourse(course: Course): void {
    this.router.navigate(['courses', course.id]);
  }

  onEnroll(courseId: number): void {
    console.log('Enrolling in course:', courseId);
    this.selectedCourseId = courseId;
    this.store.dispatch(enrollInCourse({ courseId }));
  }
}
