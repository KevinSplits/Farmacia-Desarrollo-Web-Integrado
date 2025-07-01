import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ProductoService {
  private apiUrl = 'http://localhost:8080/farmacia/productos/api';

  constructor(private http: HttpClient) {}

  // Obtener productos activos (inventario principal)
  getProductosActivos(): Observable<any> {
    return this.http.get(`${this.apiUrl}/inventario`);
  }

  // Obtener productos inactivos
  getProductosInactivos(): Observable<any> {
    return this.http.get(`${this.apiUrl}/inactivos`);
  }

  // Buscar productos activos por término
  buscarProductos(term: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/inventario?search=${term}`);
  }

  // Obtener productos con stock bajo
  getProductosStockBajo(threshold: number = 10): Observable<any> {
    return this.http.get(`${this.apiUrl}/inventario/bajo-stock?threshold=${threshold}`);
  }

  // Obtener producto por ID para editar
  getProductoById(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/editar/${id}`);
  }

  // Actualizar producto
  updateProducto(producto: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/actualizar`, producto);
  }

  // Activar producto
  activarProducto(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/activar/${id}`);
  }

  // Inactivar producto
  inactivarProducto(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/invalidate/${id}`);
  }
}
