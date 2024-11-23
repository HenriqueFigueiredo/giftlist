import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root',
})
export class MsgService {
  constructor(private snackBar: MatSnackBar) {}

  showGenericError() {
    this.showError('Oops, please try again later.');
  }

  showError(message: string) {
    this.snackBar.open(message, 'X', {
      duration: 5000,
      panelClass: ['error-snackbar'],
    });
  }

  showInfo(message: string) {
    this.snackBar.open(message, 'X', {
      duration: 5000,
      panelClass: ['info-snackbar'],
    });
  }
}
