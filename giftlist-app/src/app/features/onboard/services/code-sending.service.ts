import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CodeSendingService {
  private apiUrl = `${environment.apiUrl}/api/v1/send-code`;

  constructor(private http: HttpClient) {}

  send(): Observable<any> {
    return this.http.post<any>(this.apiUrl, {});
  }
}
