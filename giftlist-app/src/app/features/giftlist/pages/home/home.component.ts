import { AfterViewInit, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';
import { HeaderComponent } from '../../../../layout/header/header.component';
import { FooterMinimalComponent } from '../../../../layout/footer-minimal/footer-minimal.component';
import { GiftListCardComponent } from '../../components/gift-list-card/gift-list-card.component';
import { GiftListResponseModel } from '../../models/gift-list.response';
import { GiftListService } from '../../services/gift-list.service';
import { MsgService } from '../../../../core/services/msg.service';
import { isLoggedUser, logoff } from '../../../../core/utils/session.utils';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    HeaderComponent,
    FooterMinimalComponent,
    MatButtonModule,
    GiftListCardComponent,
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent implements OnInit, AfterViewInit {
  giftLists: GiftListResponseModel[] = [];

  constructor(
    private router: Router,
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

  private handleSuccess(data: GiftListResponseModel[]) {
    this.giftLists = data;
  }

  private handleError(_err: any) {
    this.msgService.showGenericError();
  }

  navigateToGiftListForm() {
    this.router.navigate(['/gift-list-form']);
  }

  giftListRemoved() {
    this.loadGiftList();
    this.msgService.showInfo('List removed');
  }

  private loadGiftList() {
    this.giftListService.listAll().subscribe({
      next: this.handleSuccess.bind(this),
      error: this.handleError.bind(this),
    });
  }
}
