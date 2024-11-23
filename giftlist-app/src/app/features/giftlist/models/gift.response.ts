export class GiftResponse {
  constructor(
    private _id: string,
    private _name: string,
    private _url: string,
    private _guestName: string,
    private _guestLastName: string,
    private _guestEmail: string
  ) {}

  get id(): string {
    return this._id;
  }

  get name(): string {
    return this._name;
  }

  get url(): string {
    return this._url;
  }

  get guestName(): string {
    return this._guestName;
  }

  get guestLastName(): string {
    return this._guestLastName;
  }

  get guestEmail(): string {
    return this._guestEmail;
  }
}
