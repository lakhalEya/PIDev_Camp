import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListReservationComponent } from './components/booking/list-reservation/list-reservation.component';
import { AppComponent } from './app.component';

const routes: Routes = [
  {path:"reservation",component: ListReservationComponent},
  {path:"acceuil",component: AppComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
