import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GuestGiftListCompleteResponse } from '../models/guest-gift-list-complete.response';
import { GuestGiftSelectionRequest } from '../models/guest-gift-selection.request';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class GuestGiftListService {
  private apiUrl = `${environment.apiUrl}/api/v1/guests`;

  constructor(private http: HttpClient) {}

  findById(listId: string): Observable<GuestGiftListCompleteResponse> {
    return this.http.get<GuestGiftListCompleteResponse>(
      `${this.apiUrl}/gift-list/${listId}`
    );
  }

  selectGift(giftRequest: GuestGiftSelectionRequest): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/select-gift`, giftRequest);
  }
}
