import { Injectable } from '@angular/core';
import { GiftResponse } from '../../features/giftlist/models/gift.response';

@Injectable({
  providedIn: 'root',
})
export class FlowService {
  _giftListSelectionFlow: string | undefined;
  _giftDetailsFlow: GiftResponse;

  constructor() {
    this._giftDetailsFlow = new GiftResponse('', '', '', '', '', '');
  }

  setGiftSelectionFlow(giftListId: string): void {
    this._giftListSelectionFlow = giftListId;
  }

  giftSelectionFlow() {
    return this._giftListSelectionFlow;
  }

  setGiftDetailsFlow(gift: GiftResponse): void {
    this._giftDetailsFlow = gift;
  }

  giftDetailsFlow() {
    return this._giftDetailsFlow;
  }
}
