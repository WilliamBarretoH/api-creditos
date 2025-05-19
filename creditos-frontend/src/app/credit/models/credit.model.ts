export interface CreditDto {
  numeroCredito: string;
  numeroNfse: string;
  dataConstituicao: string;  // ISO string
  valorIssqn: number;
  tipoCredito: string;
  simplesNacional: string;
  aliquota: number;
  valorFaturado: number;
  valorDeducao: number;
  baseCalculo: number;
}
