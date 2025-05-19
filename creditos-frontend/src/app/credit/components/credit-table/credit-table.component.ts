import { Component, Input, ChangeDetectionStrategy } from '@angular/core';
import { CreditDto } from '../../models/credit.model';

@Component({
  selector: 'app-credit-table',
  templateUrl: './credit-table.component.html',
  styleUrls: ['./credit-table.component.scss'],
  //changeDetection: ChangeDetectionStrategy.OnPush
})
export class CreditTableComponent {
  @Input() data: CreditDto[] = [];
}
