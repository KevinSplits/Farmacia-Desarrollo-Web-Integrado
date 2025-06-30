import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-inventario',
  templateUrl: './inventario.component.html',
  styleUrls: ['./inventario.component.css']
})
export class InventarioComponent implements OnInit {
  searchTerm: string = '';
  productos: any[] = []; // Simulación
  inactivos: any[] = [];

  constructor() {}

  ngOnInit(): void {
    // Aquí cargarías los productos desde el servicio
  }

  buscar(): void {
    console.log('Buscando:', this.searchTerm);
    // Aquí va el filtro real según tu lógica
  }

  limpiarBusqueda(): void {
    this.searchTerm = '';
    this.buscar(); // O recargar productos
  }

  desactivarProducto(id: number): void {
    console.log('Desactivar producto con ID:', id);
  }

  activarProducto(id: number): void {
    console.log('Activar producto con ID:', id);
  }
}
