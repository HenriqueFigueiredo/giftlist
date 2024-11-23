import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SignUpRequest } from '../models/signup.request';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class SignUpService {
  private apiUrl = `${environment.apiUrl}/api/v1/signon`;

  constructor(private http: HttpClient) {}

  signin(signUpRequest: SignUpRequest): Observable<any> {
    return this.http.post<any>(this.apiUrl, signUpRequest);
  }
}
