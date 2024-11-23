import { ErrorResponse } from '../model/error.response';

export function mapError(errorResponse: any): ErrorResponse {
  return new ErrorResponse(
    errorResponse?.error?.cod,
    errorResponse?.error?.msg
  );
}
