import { Component } from '@angular/core';
import { MatCardModule, MatCardTitle } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import {
  FormGroup,
  ReactiveFormsModule,
  FormBuilder,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { HeaderMinimalComponent } from '../../../../layout/header-minimal/header-minimal.component';
import { FooterMinimalComponent } from '../../../../layout/footer-minimal/footer-minimal.component';
import { CommonModule, Location } from '@angular/common';
import { GiftListService } from '../../services/gift-list.service';
import { GiftRequest } from '../../models/gift.request';
import { MsgService } from '../../../../core/services/msg.service';
import { HttpErrorResponse } from '@angular/common/http';
import { mapError } from '../../../../core/utils/error.utils';

@Component({
  selector: 'app-gift-form',
  standalone: true,
  imports: [
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    HeaderMinimalComponent,
    FooterMinimalComponent,
    ReactiveFormsModule,
    RouterModule,
    CommonModule,
  ],
  templateUrl: './gift-form.component.html',
  styleUrl: './gift-form.component.scss',
})
export class GiftFormComponent {
  giftForm: FormGroup;
  listId: string = '';

  constructor(
    private router: Router,
    private location: Location,
    private formBuilder: FormBuilder,
    private giftListService: GiftListService,
    private route: ActivatedRoute,
    private msgService: MsgService
  ) {
    this.giftForm = this.formBuilder.group({
      nameInput: ['', [Validators.required]],
      urlInput: ['', [Validators.required]],
    });
    this.listId = this.route.snapshot.paramMap.get('listId') ?? '';
  }

  onSubmiGiftForm() {
    if (this.giftForm.valid) {
      let request = new GiftRequest(
        this.giftForm.value.nameInput,
        this.giftForm.value.urlInput
      );
      this.giftListService.createGift(this.listId, request).subscribe({
        next: this.handleSuccess.bind(this),
        error: this.handleError.bind(this),
      });
    }
  }

  return() {
    this.location.back();
  }

  private handleSuccess() {
    this.router.navigate(['/gift-list-management', this.listId]);
  }

  private handleError(error: HttpErrorResponse) {
    let errorModel = mapError(error);
    if (errorModel.cod == '1007') {
      this.msgService.showError(
        'You have reached the maximum number of gifts in a list. We are working to increase this limit in the future :('
      );
    } else {
      this.msgService.showGenericError();
    }
  }
}
