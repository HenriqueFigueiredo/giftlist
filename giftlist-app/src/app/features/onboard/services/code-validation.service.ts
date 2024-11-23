import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CodeValidationRequest } from '../models/code-validation.request';
import { CodeValidationResponse } from '../models/code-validation.response';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CodeValidationService {
  private apiUrl = `${environment.apiUrl}/api/v1/code`;

  constructor(private http: HttpClient) {}

  validate(
    codeValidationRequest: CodeValidationRequest
  ): Observable<CodeValidationResponse> {
    return this.http.post<CodeValidationResponse>(
      this.apiUrl,
      codeValidationRequest
    );
  }
}
