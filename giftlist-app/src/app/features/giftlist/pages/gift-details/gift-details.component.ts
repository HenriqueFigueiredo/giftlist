import { Component } from '@angular/core';
import { FlowService } from '../../../../shared/services/flow.service';
import { GiftResponse } from '../../models/gift.response';
import { CommonModule, Location } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { FooterMinimalComponent } from '../../../../layout/footer-minimal/footer-minimal.component';
import { HeaderComponent } from '../../../../layout/header/header.component';

@Component({
  selector: 'app-gift-details',
  standalone: true,
  imports: [
    MatCardModule,
    MatButtonModule,
    HeaderComponent,
    FooterMinimalComponent,
    CommonModule,
  ],
  templateUrl: './gift-details.component.html',
  styleUrl: './gift-details.component.scss',
})
export class GiftDetailsComponent {
  gift: GiftResponse;

  constructor(private flowService: FlowService, private location: Location) {
    this.gift = this.flowService.giftDetailsFlow();
  }

  return() {
    this.location.back();
  }
}
