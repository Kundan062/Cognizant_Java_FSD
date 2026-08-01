import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CourseService } from '../../services/course.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {
  portalName = 'Student Course Portal';
  isPortalActive = true;
  message = '';
  searchTerm = '';
  coursesAvailable = 0;
  enrolledCount = 3;
  gpa = 3.8;

  constructor(private courseService: CourseService) {}

  ngOnInit(): void {
    // ngOnInit fires once after inputs are set — the right place for data
    // fetching, unlike the constructor which runs before inputs exist.
    this.courseService.getCourses().subscribe({
      next: (courses) => (this.coursesAvailable = courses.length),
      error: () => (this.coursesAvailable = 12) // fallback demo value if API is unavailable
    });
    console.log('HomeComponent initialised — courses loaded');
  }

  ngOnDestroy(): void {
    console.log('HomeComponent destroyed');
  }

  onEnrollClick(): void {
    this.message = 'Enrollment opened!';
  }
}
