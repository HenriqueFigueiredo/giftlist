export class GiftListCreationResponse {
  constructor(private _listId: string) {}

  get listId(): string {
    return this._listId;
  }
}
