export class ErrorResponse {
  constructor(
    private _cod: string,
    private _msg: string
  ) {}

  get cod(): string {
    return this._cod;
  }

  get msg(): string {
    return this._msg;
  }
}
