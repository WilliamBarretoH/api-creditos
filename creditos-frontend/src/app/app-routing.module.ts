import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: 'consulta',
    loadChildren: () =>
      import('./credit/credit.module').then(m => m.CreditModule)
  },
  { path: '', redirectTo: 'consulta', pathMatch: 'full' },
  { path: '**', redirectTo: 'consulta' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
