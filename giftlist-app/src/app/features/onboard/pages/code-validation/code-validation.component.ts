import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import {
  FormGroup,
  ReactiveFormsModule,
  FormBuilder,
  Validators,
} from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { HeaderMinimalComponent } from '../../../../layout/header-minimal/header-minimal.component';
import { FooterMinimalComponent } from '../../../../layout/footer-minimal/footer-minimal.component';
import { CommonModule } from '@angular/common';
import { CodeValidationService } from '../../services/code-validation.service';
import { CodeValidationRequest } from '../../models/code-validation.request';
import { MsgService } from '../../../../core/services/msg.service';
import { CodeValidationResponse } from '../../models/code-validation.response';
import { interval, Subscription } from 'rxjs';
import { FlowService } from '../../../../shared/services/flow.service';
import { CodeSendingService } from '../../services/code-sending.service';

@Component({
  selector: 'app-code-validation',
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
  templateUrl: './code-validation.component.html',
  styleUrl: './code-validation.component.scss',
})
export class CodeValidationComponent {
  codeValidationForm: FormGroup;
  counter: number = 0;
  private counterSubscription: Subscription | null = null;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private codeValidationService: CodeValidationService,
    private codeSendingService: CodeSendingService,
    private msgService: MsgService,
    private flowService: FlowService
  ) {
    this.codeValidationForm = this.formBuilder.group({
      codeInput: ['', [Validators.required]],
    });
  }

  sendCode() {
    this.codeSendingService.send().subscribe({
      next: this.handleSuccessCode.bind(this),
      error: this.handleErrorCode.bind(this),
    });
    this.counter = 60;
    this.initCounter();
  }

  private handleSuccessCode() {
    this.msgService.showInfo('Código enviado!');
  }

  private handleErrorCode(_err: any) {
    this.msgService.showGenericError();
  }

  initCounter() {
    if (this.counterSubscription) {
      this.counterSubscription.unsubscribe();
    }

    this.counterSubscription = interval(1000).subscribe(() => {
      this.counter--;
      if (this.counter <= 0) {
        this.counterSubscription?.unsubscribe();
        this.counterSubscription = null;
      }
    });
  }

  onSubmitCodeValidationForm() {
    if (this.codeValidationForm.valid) {
      let request = new CodeValidationRequest(
        this.codeValidationForm.value.codeInput
      );
      this.codeValidationService.validate(request).subscribe({
        next: this.handleSuccess.bind(this),
        error: this.handleError.bind(this),
      });
    }
  }

  private handleSuccess(data: CodeValidationResponse) {
    if (typeof sessionStorage !== 'undefined') {
      sessionStorage.setItem('logged_user', data.name);
    }

    if (this.flowService.giftSelectionFlow()) {
      this.router.navigate([
        '/guest-gift-list',
        this.flowService.giftSelectionFlow(),
      ]);
      return;
    }

    this.router.navigate(['/home']);
  }

  private handleError(_err: any) {
    if (_err.status === 401) {
      this.msgService.showError('Invalid code, please try again.');
      return;
    }
    this.msgService.showGenericError();
  }
}
