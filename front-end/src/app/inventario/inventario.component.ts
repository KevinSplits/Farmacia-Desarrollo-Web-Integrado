import { Component, OnInit } from '@angular/core';
import { ProductoService } from '../services/producto.service';

@Component({
  selector: 'app-inventario',
  templateUrl: './inventario.component.html',
  styleUrls: ['./inventario.component.css']
})
export class InventarioComponent implements OnInit {
  searchTerm: string = '';
  productos: any[] = [];
  inactivos: any[] = [];

  constructor(private productoService: ProductoService) {}

  ngOnInit(): void {
    this.cargarProductos();
  }

  cargarProductos(): void {
    this.productoService.getProductos().subscribe((data: any[]) => {
      this.productos = data.filter(p => p.estado === 'Activo');
      this.inactivos = data.filter(p => p.estado !== 'Activo');
    });
  }

  buscar(): void {
    if (this.searchTerm.trim() === '') {
      this.cargarProductos();
    } else {
      this.productoService.getProductos().subscribe((data: any[]) => {
        const term = this.searchTerm.toLowerCase();
        this.productos = data.filter(p => p.estado === 'Activo' && (
          p.descripcion?.toLowerCase().includes(term) ||
          p.idproducto?.toString().includes(term)
        ));
        this.inactivos = data.filter(p => p.estado !== 'Activo' && (
          p.descripcion?.toLowerCase().includes(term) ||
          p.idproducto?.toString().includes(term)
        ));
      });
    }
  }

  limpiarBusqueda(): void {
    this.searchTerm = '';
    this.cargarProductos();
  }

  desactivarProducto(id: number): void {
    // Aquí deberías llamar a un método del servicio para desactivar el producto
    // Por ahora solo recarga la lista
    this.cargarProductos();
  }

  activarProducto(id: number): void {
    // Aquí deberías llamar a un método del servicio para activar el producto
    // Por ahora solo recarga la lista
    this.cargarProductos();
  }
}
