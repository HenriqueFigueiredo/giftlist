import { AfterViewInit, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { GiftCardComponent } from '../../components/gift-card/gift-card.component';
import { FooterMinimalComponent } from '../../../../layout/footer-minimal/footer-minimal.component';
import { HeaderComponent } from '../../../../layout/header/header.component';
import { GiftListCompleteResponse } from '../../models/gift-list-complete.response';
import { GiftListService } from '../../services/gift-list.service';
import { MsgService } from '../../../../core/services/msg.service';
import { isLoggedUser, logoff } from '../../../../core/utils/session.utils';

@Component({
  selector: 'app-gift-list-management',
  standalone: true,
  imports: [
    CommonModule,
    HeaderComponent,
    FooterMinimalComponent,
    MatButtonModule,
    GiftCardComponent,
    RouterModule,
  ],

  templateUrl: './gift-list-management.component.html',
  styleUrl: './gift-list-management.component.scss',
})
export class GiftListManagementComponent implements OnInit, AfterViewInit {
  giftList: GiftListCompleteResponse = new GiftListCompleteResponse(
    '',
    '',
    '',
    []
  );

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private giftListService: GiftListService,
    private msgService: MsgService
  ) {}

  ngOnInit(): void {
    this.loadGiftList();
  }

  ngAfterViewInit(): void {
    if (!isLoggedUser()) {
      logoff();
    }
  }

  private handleSuccess(data: GiftListCompleteResponse) {
    this.giftList = data;
  }

  private handleError(_err: any) {
    this.msgService.showGenericError();
  }

  navigateToGiftForm() {
    this.router.navigate(['/gift-form', this.giftList.id]);
  }

  giftRemoved() {
    this.loadGiftList();
    this.msgService.showInfo('Gift removed');
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
