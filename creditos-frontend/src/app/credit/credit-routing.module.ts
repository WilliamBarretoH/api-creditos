import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreditSearchComponent } from './components/credit-search/credit-search.component';

const routes: Routes = [
  { path: 'nfse/:nfse', component: CreditSearchComponent },
  { path: 'credito/:numero', component: CreditSearchComponent },
  { path: '', component: CreditSearchComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CreditRoutingModule { }
