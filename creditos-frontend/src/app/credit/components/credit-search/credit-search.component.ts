import { Component, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import { map } from 'rxjs/operators';
import { CreditService } from '../../credit.service';
import { CreditDto } from '../../models/credit.model';

@Component({
  selector: 'app-credit-search',
  templateUrl: './credit-search.component.html',
  styleUrls: ['./credit-search.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CreditSearchComponent {
  resultados: CreditDto[] = [];
  loading = false;
  error: string | null = null;

  constructor(private service: CreditService, private cd: ChangeDetectorRef) { }

  onSearch(filtro: string, isNfse: boolean): void {
    this.resultados = [];
    this.error = null;
    this.loading = true;

    const request$ = isNfse
      ? this.service.buscarPorNfse(filtro)
      : this.service.buscarPorCredito(filtro).pipe(map(dto => [dto]));

    request$.subscribe({
      next: data => {
        this.resultados = data;
        this.loading = false;
              this.cd.markForCheck();    // força o OnPush a reavaliar

      },
      error: err => {
        this.error = err.message;
        this.loading = false;
      }
    });
  }
}
