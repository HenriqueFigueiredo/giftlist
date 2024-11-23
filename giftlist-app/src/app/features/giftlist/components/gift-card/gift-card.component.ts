import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../../../../shared/components/confirm-dialog/confirm-dialog.component';
import { GiftListService } from '../../services/gift-list.service';
import { MsgService } from '../../../../core/services/msg.service';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { GiftResponse } from '../../models/gift.response';
import { FlowService } from '../../../../shared/services/flow.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-gift-card',
  standalone: true,
  imports: [MatButtonModule, MatCardModule, MatIconModule, CommonModule],
  templateUrl: './gift-card.component.html',
  styleUrl: './gift-card.component.scss',
})
export class GiftCardComponent {
  @Input() listId: string = '';
  @Input() gift!: GiftResponse;

  @Output() giftRemoved = new EventEmitter<void>();

  constructor(
    private dialog: MatDialog,
    private giftListService: GiftListService,
    private msgService: MsgService,
    private flowService: FlowService,
    private router: Router
  ) {}

  deleteGift(): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent);

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.giftListService.deleteGift(this.listId, this.gift!.id).subscribe({
          next: this.handleSuccess.bind(this),
          error: this.handleError.bind(this),
        });
      }
    });
  }

  private handleSuccess() {
    this.giftRemoved.emit();
  }

  private handleError(_err: any) {
    this.msgService.showGenericError();
  }

  openDetails(): void {
    this.flowService.setGiftDetailsFlow(this.gift);
    this.router.navigate(['/gift-details']);
  }
}
