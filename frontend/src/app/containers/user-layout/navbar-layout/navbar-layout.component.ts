import { Component , HostListener, Input } from '@angular/core';
import { GridModule } from '@coreui/angular';
import { FormControl, FormGroup } from '@angular/forms';

import { ClassToggleService, HeaderComponent } from '@coreui/angular';
@Component({
  selector: 'app-navbar-layout',
  templateUrl: './navbar-layout.component.html',
  styleUrls: ['./navbar-layout.component.scss']
})
export class NavbarLayoutComponent extends HeaderComponent{
  @Input() sidebarId: string = "sidebar";

  public newMessages = new Array(4)
  public newTasks = new Array(5)
  public newNotifications = new Array(5)
  isNavbarOpen = false;
  isNavbarScrolled = false;

  @HostListener('window:scroll')
  onWindowScroll() {
    this.isNavbarScrolled = (window.pageYOffset > 0);
  }

  toggleNavbar() {
    this.isNavbarOpen = !this.isNavbarOpen;
  }

  constructor(private classToggler: ClassToggleService) {
    super();
  }
}
