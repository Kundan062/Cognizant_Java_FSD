import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { finalize } from 'rxjs/operators';
import { LoadingService } from '../services/loading.service';

export const loadingInterceptor: HttpInterceptorFn = (req, next) => {
  const loading = inject(LoadingService);
  loading.show();
  // finalize runs on both success and error — the correct place to hide
  // a spinner, equivalent to a try/catch/finally block.
  return next(req).pipe(finalize(() => loading.hide()));
};
