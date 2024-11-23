import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';
import { ConfirmDialogComponent } from '../../../../shared/components/confirm-dialog/confirm-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { MsgService } from '../../../../core/services/msg.service';
import { GiftListService } from '../../services/gift-list.service';
import { MatIconModule } from '@angular/material/icon';
import { ShareGiftListDialogComponent } from '../share-giftlist-dialog/share-giftlist-dialog.component';

@Component({
  selector: 'app-gift-list-card',
  standalone: true,
  imports: [MatButtonModule, MatCardModule, MatIconModule],
  templateUrl: './gift-list-card.component.html',
  styleUrl: './gift-list-card.component.scss',
})
export class GiftListCardComponent {
  @Input() listId: string = '';
  @Input() listName: string | undefined;
  @Input() listDescription: string | undefined;

  @Output() giftListRemoved = new EventEmitter<void>();

  constructor(
    private router: Router,
    private dialog: MatDialog,
    private msgService: MsgService,
    private giftListService: GiftListService
  ) {}

  openList(listId: any) {
    this.router.navigate(['/gift-list-management', listId]);
  }

  deleteGiftList(): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent);

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.giftListService.deleteGiftList(this.listId).subscribe({
          next: this.handleSuccess.bind(this),
          error: this.handleError.bind(this),
        });
      }
    });
  }

  private handleSuccess() {
    this.giftListRemoved.emit();
  }

  private handleError(_err: any) {
    this.msgService.showGenericError();
  }

  shareList() {
    const dialogRef = this.dialog.open(ShareGiftListDialogComponent, {
      data: {
        link: `http://localhost4200/guest-gift-list/${this.listId}`,
      },
    });
  }
}
