import { Component } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { HighlightDirective } from './highlight.directive';

@Component({
  standalone: true,
  imports: [HighlightDirective],
  template: `<div appHighlight="lightblue">Hover me</div>`
})
class HostComponent {}

describe('HighlightDirective', () => {
  let fixture: ComponentFixture<HostComponent>;

  beforeEach(() => {
    fixture = TestBed.configureTestingModule({ imports: [HostComponent] }).createComponent(HostComponent);
    fixture.detectChanges();
  });

  it('should set background color on mouseenter and clear on mouseleave', () => {
    const div = fixture.debugElement.query(By.css('div'));
    div.triggerEventHandler('mouseenter', null);
    fixture.detectChanges();
    expect(div.nativeElement.style.backgroundColor).toBe('lightblue');

    div.triggerEventHandler('mouseleave', null);
    fixture.detectChanges();
    expect(div.nativeElement.style.backgroundColor).toBe('');
  });
});
