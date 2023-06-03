import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserLayoutComponent } from './user/user-layout/user-layout.component';
import { AdminLayoutComponent } from './admin/admin-layout/admin-layout.component';
import { NotFoundComponent } from './shared/not-found/not-found.component';

const routes: Routes = [
  { path: '', redirectTo: 'user', pathMatch: 'full' }, // Redirect to the user interface by default
  { path: 'user', component: UserLayoutComponent },
  { path: 'admin', component: AdminLayoutComponent },
  { path: '**', component: NotFoundComponent } // Redirect to the user interface for any other unknown route
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
