import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';

import { MsgService } from '../../../../core/services/msg.service';
import { MatIconModule } from '@angular/material/icon';
import { GuestGiftListService } from '../../services/guest-gift-list.service';
import { ConfirmGiftDialogComponent } from '../confirm-gift-dialog/confirm-gift-dialog.component';
import { isLoggedUser } from '../../../../core/utils/session.utils';
import { GuestGiftSelectionRequest } from '../../models/guest-gift-selection.request';
import { CommonModule } from '@angular/common';
import { FlowService } from '../../../../shared/services/flow.service';

@Component({
  selector: 'app-guest-gift-card',
  standalone: true,
  imports: [CommonModule, MatButtonModule, MatCardModule, MatIconModule],
  templateUrl: './guest-gift-card.component.html',
  styleUrl: './guest-gift-card.component.scss',
})
export class GuestGiftCardComponent {
  @Input() giftId: string = '';
  @Input() listId: string = '';
  @Input() giftName: string | undefined;
  @Input() giftUrl: string | undefined;
  @Input() giftFree: boolean = false;

  @Output() giftSelected = new EventEmitter<void>();

  constructor(
    private dialog: MatDialog,
    private giftListService: GuestGiftListService,
    private msgService: MsgService,
    private flowService: FlowService
  ) {}

  onLinkClick(_event: MouseEvent) {
    window.open(this.giftUrl, '_blank');
  }

  selectGift(): void {
    if (!isLoggedUser()) {
      this.flowService.setGiftSelectionFlow(this.listId);
      this.msgService.showInfo(
        'To confirm a gift, you need to log in to the application.'
      );
      return;
    }

    const dialogRef = this.dialog.open(ConfirmGiftDialogComponent);

    let request = new GuestGiftSelectionRequest(this.listId, this.giftId);

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.giftListService.selectGift(request).subscribe({
          next: this.handleSuccess.bind(this),
          error: this.handleError.bind(this),
        });
      }
    });
  }

  private handleSuccess() {
    this.giftSelected.emit();
  }

  private handleError(_err: any) {
    this.msgService.showGenericError();
  }
}
