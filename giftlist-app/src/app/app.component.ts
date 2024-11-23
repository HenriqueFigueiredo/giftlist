import { Component } from '@angular/core';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { MainComponent } from './features/landingpage/pages/main/main.component';
import { routeTransition } from './route-transitions';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, MainComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
  animations: [routeTransition],
})
export class AppComponent {
  title = 'giftlist-app';

  constructor(protected route: ActivatedRoute) {}
}
