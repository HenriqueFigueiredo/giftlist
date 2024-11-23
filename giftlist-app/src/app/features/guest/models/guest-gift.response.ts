export class GuestGiftResponse {
  constructor(
    private _id: string,
    private _name: string,
    private _url: string,
    private _free: boolean
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

  get free(): boolean {
    return this._free;
  }
}
