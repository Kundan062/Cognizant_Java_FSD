import { createReducer, on } from '@ngrx/store';
import { enrollInCourse, unenrollFromCourse } from './enrollment.actions';

export interface EnrollmentState {
  enrolledCourseIds: number[];
}

export const initialState: EnrollmentState = { enrolledCourseIds: [] };

export const enrollmentReducer = createReducer(
  initialState,
  on(enrollInCourse, (state, { courseId }) => ({
    enrolledCourseIds: state.enrolledCourseIds.includes(courseId)
      ? state.enrolledCourseIds
      : [...state.enrolledCourseIds, courseId]
  })),
  on(unenrollFromCourse, (state, { courseId }) => ({
    enrolledCourseIds: state.enrolledCourseIds.filter((id) => id !== courseId)
  }))
);
