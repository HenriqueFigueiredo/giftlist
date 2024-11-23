import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import {
  FormGroup,
  FormControl,
  ReactiveFormsModule,
  FormBuilder,
  Validators,
} from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { HeaderMinimalComponent } from '../../../../layout/header-minimal/header-minimal.component';
import { FooterMinimalComponent } from '../../../../layout/footer-minimal/footer-minimal.component';
import { GiftListService } from '../../services/gift-list.service';
import { GiftListRequest } from '../../models/gift-list.request';
import { GiftListCreationResponse } from '../../models/gift-list-creation.response';
import { CommonModule } from '@angular/common';
import { MsgService } from '../../../../core/services/msg.service';
import { HttpErrorResponse } from '@angular/common/http';
import { mapError } from '../../../../core/utils/error.utils';

@Component({
  selector: 'app-gift-list-form',
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
  templateUrl: './gift-list-form.component.html',
  styleUrl: './gift-list-form.component.scss',
})
export class GiftListFormComponent {
  giftListForm: FormGroup;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private giftListService: GiftListService,
    private msgService: MsgService
  ) {
    this.giftListForm = this.formBuilder.group({
      nameInput: ['', [Validators.required]],
      descriptionInput: ['', [Validators.required]],
    });
  }

  onSubmiGiftListForm() {
    if (this.giftListForm.valid) {
      let request = new GiftListRequest(
        this.giftListForm.value.nameInput,
        this.giftListForm.value.descriptionInput
      );
      this.giftListService.create(request).subscribe({
        next: this.handleSuccess.bind(this),
        error: this.handleError.bind(this),
      });
    }
  }

  private handleSuccess(data: GiftListCreationResponse) {
    this.router.navigate(['/gift-list-management', data.listId]);
  }

  private handleError(error: HttpErrorResponse) {
    let errorModel = mapError(error);
    if (errorModel.cod == '1006') {
      this.msgService.showError(
        'You have reached the maximum number of lists at the moment, try deleting a list.'
      );
    } else {
      this.msgService.showGenericError();
    }
  }
}
