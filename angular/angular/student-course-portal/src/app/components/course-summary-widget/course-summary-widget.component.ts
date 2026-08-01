import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CourseService } from '../../services/course.service';

// Injects the same singleton CourseService as CourseListComponent/HomeComponent —
// demonstrates that all three share one instance and see the same data.
@Component({
  selector: 'app-course-summary-widget',
  standalone: true,
  imports: [CommonModule],
  template: `<p>Total courses (live): {{ count }}</p>`
})
export class CourseSummaryWidgetComponent implements OnInit {
  count = 0;

  constructor(private courseService: CourseService) {}

  ngOnInit(): void {
    this.courseService.getCourses().subscribe((courses) => (this.count = courses.length));
  }
}
