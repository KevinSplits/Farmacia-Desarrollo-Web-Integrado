import { Component, OnInit } from '@angular/core';
import { VentaService } from '../services/venta.service';

@Component({
  selector: 'app-ventas',
  templateUrl: './ventas.component.html',
  styleUrls: ['./ventas.component.css']
})
export class VentasComponent implements OnInit {
  ventas: any[] = [];

  constructor(private ventaService: VentaService) {}

  ngOnInit(): void {
    this.ventaService.getVentas().subscribe((data: any[]) => {
      console.log('Ventas recibidas:', data);
      this.ventas = data;
    });
  }
  // Acción para anular una venta
  anularVenta(id: number): void {
    if (confirm('¿Está seguro de anular esta venta?')) {
      this.ventaService.anularVenta(id).subscribe({
        next: () => {
          this.ventas = this.ventas.map(v => v.idventa === id ? { ...v, estado: 'Anulado' } : v);
          alert('Venta anulada correctamente.');
        },
        error: () => {
          alert('Error al anular la venta.');
        }
      });
    }
  }

  // Acción para ver detalle de una venta
  verDetalle(id: number): void {
    // Navega a la ruta de detalle de venta (debes tener la ruta configurada en el router)
    window.location.href = `/ventas/detalle/${id}`;
  }
}
