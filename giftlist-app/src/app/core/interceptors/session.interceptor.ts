import {
  HttpErrorResponse,
  HttpEvent,
  HttpEventType,
  HttpHandlerFn,
  HttpRequest,
} from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { logoff } from '../utils/session.utils';

export function sessionInterceptor(
  req: HttpRequest<unknown>,
  next: HttpHandlerFn
): Observable<HttpEvent<unknown>> {
  if (typeof sessionStorage !== 'undefined') {
    const token = sessionStorage.getItem('authorization_token');
    if (token) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
    }
  }

  return next(req).pipe(
    tap({
      next: (event) => {
        if (event.type === HttpEventType.Response) {
          const token = event.headers.get('Authorization');
          if (token && typeof sessionStorage !== 'undefined') {
            sessionStorage.setItem('authorization_token', token);
          }
        }
      },
      error: (_error) => {
        if (_error instanceof HttpErrorResponse) {
          const token = _error.headers.get('Authorization');
          if (token && typeof sessionStorage !== 'undefined') {
            sessionStorage.setItem('authorization_token', token);
          }
          if (_error.status == 403) {
            logoff();
          }
        }
      },
    })
  );
}
