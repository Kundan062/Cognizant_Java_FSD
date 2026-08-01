import { TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { authGuard } from './auth.guard';
import { AuthService } from '../services/auth.service';

describe('authGuard', () => {
  it('allows navigation when logged in', () => {
    TestBed.configureTestingModule({ providers: [{ provide: AuthService, useValue: { isLoggedIn: true } }, { provide: Router, useValue: { navigate: () => {} } }] });
    const result = TestBed.runInInjectionContext(() => authGuard({} as any, {} as any));
    expect(result).toBeTrue();
  });

  it('blocks navigation and redirects when logged out', () => {
    const navigateSpy = jasmine.createSpy('navigate');
    TestBed.configureTestingModule({ providers: [{ provide: AuthService, useValue: { isLoggedIn: false } }, { provide: Router, useValue: { navigate: navigateSpy } }] });
    const result = TestBed.runInInjectionContext(() => authGuard({} as any, {} as any));
    expect(result).toBeFalse();
    expect(navigateSpy).toHaveBeenCalledWith(['/']);
  });
});
