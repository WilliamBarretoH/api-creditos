import { Component, Output, EventEmitter, Input, ChangeDetectionStrategy } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SearchFormComponent {
  @Input() loading = false;
  @Output() search = new EventEmitter<{ filtro: string; isNfse: boolean }>();

  form = this.fb.group({
    filtro: ['', [Validators.required, Validators.pattern(/^\d[\d-]*$/)]]
  });

  constructor(private fb: FormBuilder) { }

  onSubmit(): void {
    if (this.form.valid && !this.loading) {
      const filtro = this.form.value.filtro!.trim();
      const isNfse = /^[0-9]/.test(filtro);
      this.search.emit({ filtro, isNfse });
    }
  }
}
