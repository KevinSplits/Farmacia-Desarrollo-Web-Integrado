import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  private apiUrl = 'http://localhost:8080/api/dashboard'; // Ajusta la URL de tu backend

  constructor(private http: HttpClient) {}

  getResumen(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/resumen`);
  }

  getProductosStockBajo(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/productos-stock-bajo`);
  }

  getUltimosClientes(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/ultimos-clientes`);
  }
}