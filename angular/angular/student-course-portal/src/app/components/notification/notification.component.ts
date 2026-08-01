import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationService } from '../../services/notification.service';

// providers: [NotificationService] here creates a NEW instance of the
// service scoped to this component (and its children), separate from any
// other NotificationService instance elsewhere in the app.
@Component({
  selector: 'app-notification',
  standalone: true,
  imports: [CommonModule],
  providers: [NotificationService],
  template: `
    <ul>
      <li *ngFor="let m of service.getAll()">{{ m }}</li>
    </ul>
  `
})
export class NotificationComponent {
  constructor(public service: NotificationService) {
    this.service.push('Welcome to the Student Course Portal!');
  }
}
