import { Injectable } from '@angular/core';

// Provided at the component level (see NotificationComponent) rather than
// 'root', so each component instance that provides it gets its own isolated copy.
@Injectable()
export class NotificationService {
  private messages: string[] = [];

  push(message: string): void {
    this.messages.push(message);
  }

  getAll(): string[] {
    return this.messages;
  }
}
