import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import {
  FormGroup,
  ReactiveFormsModule,
  Validators,
  FormBuilder,
} from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HeaderMinimalComponent } from '../../../../layout/header-minimal/header-minimal.component';
import { FooterMinimalComponent } from '../../../../layout/footer-minimal/footer-minimal.component';
import { LoginService } from '../../services/login.service';
import { LoginRequest } from '../../models/login.request';
import { mapError } from '../../../../core/utils/error.utils';
import { HttpErrorResponse } from '@angular/common/http';
import { MsgService } from '../../../../core/services/msg.service';

@Component({
  selector: 'app-login',
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
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  emailForm: FormGroup;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private loginService: LoginService,
    private msgService: MsgService
  ) {
    this.emailForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
    });
  }

  onSubmitEmailForm() {
    if (this.emailForm.valid) {
      let loginRequest = new LoginRequest(this.emailForm.value.email);
      this.loginService.login(loginRequest).subscribe({
        next: this.handleCodeValidation.bind(this),
        error: this.handleError.bind(this),
      });
    }
  }

  private handleCodeValidation() {
    this.router.navigate(['/code-validation']);
  }

  private handleError(error: HttpErrorResponse) {
    let errorModel = mapError(error);
    if (errorModel.cod == '1001') {
      this.router.navigate(['/sign-up']);
    } else {
      this.msgService.showGenericError();
    }
  }

  public return() {
    this.router.navigate(['/']);
  }
}
