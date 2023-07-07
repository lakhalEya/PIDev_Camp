import { Component , HostListener } from '@angular/core';

@Component({
  selector: 'app-user-layout',
  templateUrl: './user-layout.component.html',
  styleUrls: ['./user-layout.component.scss']
})
export class UserLayoutComponent {
  isNavbarOpen = false;
  isNavbarScrolled = false;

  @HostListener('window:scroll')
  onWindowScroll() {
    this.isNavbarScrolled = (window.pageYOffset > 0);
  }

  toggleNavbar() {
    this.isNavbarOpen = !this.isNavbarOpen;
  }
}
