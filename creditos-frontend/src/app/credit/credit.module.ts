import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CreditRoutingModule } from './credit-routing.module';
import { CreditSearchComponent } from './components/credit-search/credit-search.component';
import { SearchFormComponent } from './components/search-form/search-form.component';
import { CreditTableComponent } from './components/credit-table/credit-table.component';
import { CreditService } from './credit.service';

@NgModule({
  declarations: [
    CreditSearchComponent,
    SearchFormComponent,
    CreditTableComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    CreditRoutingModule
  ],
  providers: [CreditService]
})
export class CreditModule { }
