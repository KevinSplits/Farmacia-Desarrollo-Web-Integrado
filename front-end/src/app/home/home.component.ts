
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { ClientService } from '../services/client.service';
import { EmployeeService } from '../services/employee.service';
import { ProductoService } from '../services/producto.service';
import { DashboardService } from '../services/dashboard.service';
import { Router } from '@angular/router';

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
  currentUser: any;

  constructor(
    private authService: AuthService,
    private dashboardService: DashboardService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadUserData();
    this.loadDashboardData();
  }

  loadUserData() {
    this.currentUser = this.authService.getCurrentUser();
  }

  loadDashboardData() {
    this.dashboardService.getResumen().subscribe({
      next: (data) => {
        this.totalClientes = data.totalClientes;
        this.totalEmpleados = data.totalEmpleados;
        this.totalProductos = data.totalProductos;
        this.productosStockBajo = data.productosStockBajo;
        this.ultimosClientes = data.ultimosClientes;
      },
      error: (err) => console.error('Error loading dashboard data', err)
    });
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}