import { Component } from '@angular/core';
import { MatToolbar } from '@angular/material/toolbar';

@Component({
  selector: 'app-header-minimal',
  standalone: true,
  imports: [MatToolbar],
  templateUrl: './header-minimal.component.html',
  styleUrl: './header-minimal.component.scss',
})
export class HeaderMinimalComponent {}
