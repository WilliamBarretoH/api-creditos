import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CreditDto } from './models/credit.model';

@Injectable({ providedIn: 'root' })
export class CreditService {
  private readonly baseUrl = 'http://localhost:8080/api/creditos';
  constructor(private http: HttpClient) { }

  buscarPorNfse(nfse: string): Observable<CreditDto[]> {
    return this.http.get<CreditDto[]>(`${this.baseUrl}/${nfse}`);
  }

  buscarPorCredito(numero: string): Observable<CreditDto> {
    return this.http.get<CreditDto>(`${this.baseUrl}/credito/${numero}`);
  }
}
