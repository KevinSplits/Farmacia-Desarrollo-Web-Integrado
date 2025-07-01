import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class VentaService {
  private apiUrl = 'http://localhost:8080/farmacia/ventas/api';

  constructor(private http: HttpClient) {}

  getVentas(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/listar`);
  }

  anularVenta(id: number): Observable<any> {
    // Suponiendo que tienes un endpoint REST para anular la venta
    return this.http.get(`${this.apiUrl}/anular/${id}`);
  }

  registrarVenta(venta: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/guardar`, venta);
  }
}
