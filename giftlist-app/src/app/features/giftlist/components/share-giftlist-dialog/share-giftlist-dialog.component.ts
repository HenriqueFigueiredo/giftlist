import { Component, Inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { QrCodeModule } from 'ng-qrcode';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle,
} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-share-giftlist-dialog',
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatButtonModule,
    MatDialogTitle,
    MatDialogContent,
    MatDialogActions,
    MatDialogClose,
    QrCodeModule,
  ],
  templateUrl: './share-giftlist-dialog.component.html',
  styleUrl: './share-giftlist-dialog.component.scss',
})
export class ShareGiftListDialogComponent {
  link: string | undefined;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private dialogRef: MatDialogRef<ShareGiftListDialogComponent>
  ) {
    this.link = data.link;
  }

  onConfirm(): void {
    this.dialogRef.close(true);
  }
}
