import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  totalEmpleados: number = 0;
  totalProductos: number = 0;
  productosStockBajo: any[] = [];
  ultimosClientes: any[] = [];

  constructor() {}

  ngOnInit(): void {
    // Aquí puedes hacer llamadas a tus servicios para llenar estas propiedades
    // Ejemplo (si tienes servicios definidos):
    // this.empleadoService.obtenerTotalEmpleados().subscribe(data => this.totalEmpleados = data);
  }
}
