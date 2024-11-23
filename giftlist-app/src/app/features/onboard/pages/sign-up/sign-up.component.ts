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
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { HeaderMinimalComponent } from '../../../../layout/header-minimal/header-minimal.component';
import { FooterMinimalComponent } from '../../../../layout/footer-minimal/footer-minimal.component';
import { SignUpService } from '../../services/signup.service';
import { SignUpRequest } from '../../models/signup.request';
import { MsgService } from '../../../../core/services/msg.service';

@Component({
  selector: 'app-sign-up',
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
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.scss',
})
export class SignUpComponent {
  signForm: FormGroup;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private signUpService: SignUpService,
    private msgService: MsgService
  ) {
    this.signForm = this.formBuilder.group({
      nameInput: ['', [Validators.required]],
      lastnameInput: ['', [Validators.required]],
    });
  }

  onSubmitSignupForm() {
    if (this.signForm.valid) {
      let request = new SignUpRequest(
        this.signForm.value.nameInput,
        this.signForm.value.lastnameInput
      );
      this.signUpService.signin(request).subscribe({
        next: this.handleSuccess.bind(this),
        error: this.handleError.bind(this),
      });
    }
  }

  private handleSuccess() {
    this.router.navigate(['/code-validation']);
  }

  private handleError(_err: any) {
    this.msgService.showGenericError();
  }
}
