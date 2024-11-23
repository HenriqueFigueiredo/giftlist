import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { FooterMinimalComponent } from '../../../../layout/footer-minimal/footer-minimal.component';
import { HeaderComponent } from '../../../../layout/header/header.component';
import { GuestGiftListCompleteResponse } from '../../models/guest-gift-list-complete.response';
import { MsgService } from '../../../../core/services/msg.service';
import { GuestGiftListService } from '../../services/guest-gift-list.service';
import { GuestGiftCardComponent } from '../../components/guest-gift-card/guest-gift-card.component';

@Component({
  selector: 'app-guest-gift-list',
  standalone: true,
  imports: [
    CommonModule,
    HeaderComponent,
    FooterMinimalComponent,
    MatButtonModule,
    GuestGiftCardComponent,
    RouterModule,
  ],

  templateUrl: './guest-gift-list.component.html',
  styleUrl: './guest-gift-list.component.scss',
})
export class GuestGiftListComponent implements OnInit {
  giftList: GuestGiftListCompleteResponse = new GuestGiftListCompleteResponse(
    '',
    '',
    '',
    []
  );

  constructor(
    private route: ActivatedRoute,
    private giftListService: GuestGiftListService,
    private msgService: MsgService
  ) {}

  ngOnInit(): void {
    this.loadGiftList();
  }

  private handleSuccess(data: GuestGiftListCompleteResponse) {
    this.giftList = data;
  }

  private handleError(_err: any) {
    this.msgService.showGenericError();
  }

  giftSelected() {
    this.msgService.showInfo('Yay... you reserved a gift!');
    this.loadGiftList();
  }

  private loadGiftList() {
    const listId = this.route.snapshot.paramMap.get('listId');
    if (listId) {
      this.giftListService.findById(listId).subscribe({
        next: this.handleSuccess.bind(this),
        error: this.handleError.bind(this),
      });
    }
  }
}
