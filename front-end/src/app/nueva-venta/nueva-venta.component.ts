import { Component, OnInit } from '@angular/core';
import { ClientService } from '../services/client.service';
import { EmployeeService } from '../services/employee.service';
import { VoucherTypeService } from '../services/voucher-type.service';
import { Client } from '../models/client.model';
import { Employee } from '../models/employee.model';
import { VoucherType } from '../models/voucher-type.model';

import { ProductoService } from '../services/producto.service';
import { Producto } from '../models/producto.model';
import { VentaService } from '../services/venta.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nueva-venta',
  templateUrl: './nueva-venta.component.html',
  styleUrls: ['./nueva-venta.component.css']
})
export class NuevaVentaComponent implements OnInit {
  clients: Client[] = [];
  employees: Employee[] = [];
  voucherTypes: VoucherType[] = [];

  productos: Producto[] = [];
  detalles: any[] = [];

  // Propiedades para el binding de los selects y la fecha
  selectedClientId: number | null = null;
  selectedEmployeeId: number | null = null;
  selectedVoucherTypeId: number | null = null;
  fecha: Date = new Date();

  constructor(
    private clientService: ClientService,
    private employeeService: EmployeeService,
    private voucherTypeService: VoucherTypeService,
    private productoService: ProductoService,
    private ventaService: VentaService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.clientService.getAllClients().subscribe((data) => {
      this.clients = data;
    });
    this.employeeService.getAllEmployees().subscribe((data) => {
      this.employees = data;
    });
    this.voucherTypeService.getAllVoucherTypes().subscribe((data) => {
      this.voucherTypes = data;
    });
    this.productoService.getProductosActivos().subscribe((data) => {
      this.productos = data;
    });
  }

  agregarDetalle() {
    this.detalles.push({
      producto: null,
      cantidad: 1,
      precioUnitario: 0,
      descuento: 0
    });
  }

  eliminarDetalle(index: number) {
    this.detalles.splice(index, 1);
  }

  actualizarPrecio(index: number) {
    const detalle = this.detalles[index];
    const producto = this.productos.find(p => p.idproducto == detalle.producto);
    if (producto) {
      detalle.precioUnitario = producto.precio;
    }
  }

  calcularSubtotal(detalle: any): number {
    return (detalle.cantidad * detalle.precioUnitario) - detalle.descuento;
  }

  get subtotal(): number {
    return this.detalles.reduce((sum, d) => sum + this.calcularSubtotal(d), 0);
  }

  get igv(): number {
    return (this.subtotal) * 0.18;
  }

  get total(): number {
    return this.subtotal + this.igv;
  }

  getStock(idproducto: number): number {
    const prod = this.productos.find((p: Producto) => p.idproducto === idproducto);
    return prod ? prod.stock : 1;
  }

  registrarVenta() {
    const venta = {
      cliente: { idcliente: this.selectedClientId },
      empleado: { idempleado: this.selectedEmployeeId },
      tipoComprobante: { idtipocomprobante: this.selectedVoucherTypeId },
      fecha: this.fecha,
      detalles: this.detalles.map(d => ({
        producto: { idproducto: d.producto },
        cantidad: d.cantidad,
        precioUnitario: d.precioUnitario,
        descuento: d.descuento
      })),
      subtotal: this.subtotal,
      igv: this.igv,
      total: this.total
    };
    this.ventaService.registrarVenta(venta).subscribe({
      next: (resp) => {
        alert('Venta registrada correctamente');
        this.router.navigate(['/ventas']);
      },
      error: (err) => {
        alert('Error al registrar la venta');
        console.error(err);
      }
    });
  }
}

