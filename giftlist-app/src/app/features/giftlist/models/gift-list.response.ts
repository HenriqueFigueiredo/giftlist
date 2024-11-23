export class GiftListResponseModel {
  constructor(
    private _id: string,
    private _name: string,
    private _description: string
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
}
