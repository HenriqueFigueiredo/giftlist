import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { HeaderComponent } from '../../../../layout/header/header.component';
import { MatButtonModule } from '@angular/material/button';
import { FooterComponent } from '../../../../layout/footer/footer.component';
import { isLoggedUser } from '../../../../core/utils/session.utils';

@Component({
  selector: 'app-main',
  standalone: true,
  imports: [
    CommonModule,
    MatIconModule,
    HeaderComponent,
    FooterComponent,
    MatButtonModule,
  ],
  templateUrl: './main.component.html',
  styleUrl: './main.component.scss',
})
export class MainComponent {
  isMobile: boolean = false;

  constructor(
    private breakpointObserver: BreakpointObserver,
    private router: Router
  ) {}

  navigateToSecond() {
    if (isLoggedUser()) {
      this.router.navigate(['/home']);
      return;
    }
    this.router.navigate(['/login']);
  }

  ngOnInit() {
    this.breakpointObserver
      .observe([Breakpoints.Handset])
      .subscribe((result) => {
        this.isMobile = result.matches;
      });
  }
}
