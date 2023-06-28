import { Component , HostListener } from '@angular/core';

@Component({
  selector: 'app-navbar-layout',
  templateUrl: './navbar-layout.component.html',
  styleUrls: ['./navbar-layout.component.scss']
})
export class NavbarLayoutComponent {
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
