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
    this.productoService.getProductosActivos().subscribe((activos: any[]) => {
      this.productos = activos;
    });
    this.productoService.getProductosInactivos().subscribe((inactivos: any[]) => {
      this.inactivos = inactivos;
    });
  }

  buscar(): void {
    const term = this.searchTerm.trim().toLowerCase();
    if (term === '') {
      this.cargarProductos();
    } else {
      this.productoService.getProductosActivos().subscribe((activos: any[]) => {
        this.productos = activos.filter(p =>
          p.descripcion?.toLowerCase().includes(term) ||
          p.idproducto?.toString().includes(term)
        );
      });
      this.productoService.getProductosInactivos().subscribe((inactivos: any[]) => {
        this.inactivos = inactivos.filter(p =>
          p.descripcion?.toLowerCase().includes(term) ||
          p.idproducto?.toString().includes(term)
        );
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

  productosFiltradosStockBajo(): any[] {
    return this.productos.filter(p => p.stock < 10);
  }

  activarProducto(id: number): void {
    // Aquí deberías llamar a un método del servicio para activar el producto
    // Por ahora solo recarga la lista
    this.cargarProductos();
  }
}
