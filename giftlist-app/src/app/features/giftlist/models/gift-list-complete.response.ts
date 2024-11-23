import { GiftResponse } from './gift.response';

export class GiftListCompleteResponse {
  constructor(
    private _id: string,
    private _name: string,
    private _description: string,
    private _gifts: GiftResponse[]
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

  get gifts(): GiftResponse[] {
    return this._gifts;
  }
}
