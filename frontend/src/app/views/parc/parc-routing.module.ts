import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ParcComponent } from './parc/parc.component';
import { ActivityComponent } from './activity/activity.component';
import { EquipmentComponent } from './equipment/equipment.component';



const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Parc',
    },
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'parc',
      },
      {
        path: 'parc',
        component: ParcComponent,
        data: {
          title: 'parc',
        },
      },
      {
        path: 'activity',
        component: ActivityComponent,
        data: {
          title: 'Activity',
        },
      },
      {
        path: 'equipment',
        component: EquipmentComponent,
        data: {
          title: 'Equipment',
        },
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ParcRoutingModule {}

