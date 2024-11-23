import { GuestGiftResponse } from './guest-gift.response';

export class GuestGiftListCompleteResponse {
  constructor(
    private _id: string,
    private _name: string,
    private _description: string,
    private _gifts: GuestGiftResponse[]
  ) {}

  get id(): string {
    return this._id;
  }

  get name(): string {
    return this._name;
  }

  get description(): string {
    return this._description;
  }

  get gifts(): GuestGiftResponse[] {
    return this._gifts;
  }
}
