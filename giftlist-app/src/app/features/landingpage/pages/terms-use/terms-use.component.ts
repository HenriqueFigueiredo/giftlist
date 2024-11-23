import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { HeaderComponent } from '../../../../layout/header/header.component';
import { FooterComponent } from '../../../../layout/footer/footer.component';

@Component({
  selector: 'app-terms-use',
  standalone: true,
  imports: [CommonModule, HeaderComponent, FooterComponent],
  templateUrl: './terms-use.component.html',
  styleUrl: './terms-use.component.scss',
})
export class TermsUseComponent {}
