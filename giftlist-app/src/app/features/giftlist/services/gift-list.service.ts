import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GiftListResponseModel } from '../models/gift-list.response';
import { GiftListRequest } from '../models/gift-list.request';
import { GiftListCompleteResponse } from '../models/gift-list-complete.response';
import { GiftListCreationResponse } from '../models/gift-list-creation.response';
import { GiftRequest } from '../models/gift.request';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class GiftListService {
  private apiUrl = `${environment.apiUrl}/api/v1/gift-list`;

  constructor(private http: HttpClient) {}

  listAll(): Observable<GiftListResponseModel[]> {
    return this.http.get<GiftListResponseModel[]>(this.apiUrl);
  }

  create(
    giftListRequest: GiftListRequest
  ): Observable<GiftListCreationResponse> {
    return this.http.post<GiftListCreationResponse>(
      this.apiUrl,
      giftListRequest
    );
  }

  findById(listId: string): Observable<GiftListCompleteResponse> {
    return this.http.get<GiftListCompleteResponse>(`${this.apiUrl}/${listId}`);
  }

  deleteGiftList(listId: string): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${listId}`);
  }

  createGift(listId: string, giftRequest: GiftRequest): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/${listId}/gift`, giftRequest);
  }

  deleteGift(listId: string, giftId: string): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${listId}/gift/${giftId}`);
  }
}
