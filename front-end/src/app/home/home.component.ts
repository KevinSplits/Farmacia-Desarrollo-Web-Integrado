
import { Component, OnInit } from '@angular/core';
import { ClientService } from '../services/client.service';
import { EmployeeService } from '../services/employee.service';
import { ProductoService } from '../services/producto.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
  totalClientes: number = 0;
  totalEmpleados: number = 0;
  totalProductos: number = 0;
  productosStockBajo: any[] = [];
  ultimosClientes: any[] = [];

  constructor(
    private clientService: ClientService,
    private employeeService: EmployeeService,
    private productoService: ProductoService
  ) {}

  ngOnInit(): void {
    this.clientService.getAllClients().subscribe((clientes: any[]) => {
      this.totalClientes = clientes.filter(c => c.estado === 'Activo').length;
      this.ultimosClientes = clientes.slice(-5).reverse();
    });
    this.employeeService.getAllEmployees().subscribe((empleados: any[]) => {
      this.totalEmpleados = empleados.filter(e => e.estado === 'Activo').length;
    });
    this.productoService.getProductosActivos().subscribe((productos: any[]) => {
      this.totalProductos = productos.length;
    });
    this.productoService.getProductosStockBajo().subscribe((stockBajo: any[]) => {
      this.productosStockBajo = stockBajo;
    });
  }
}
