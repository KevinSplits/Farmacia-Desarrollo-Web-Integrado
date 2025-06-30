import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Clientes
import { ClientesComponent } from './clientes/clientes.component';
import { AgregarClienteComponent } from './agregar-cliente/agregar-cliente.component';
import { EditarClienteComponent } from './editar-cliente/editar-cliente.component';

// Empleados
import { EmpleadosComponent } from './empleados/empleados.component';
import { AgregarEmpleadoComponent } from './agregar-empleado/agregar-empleado.component';
import { EditarEmpleadoComponent } from './editar-empleado/editar-empleado.component';

// Productos
import { InventarioComponent } from './inventario/inventario.component';
import { EditarProductoComponent } from './editar-producto/editar-producto.component';

// Ventas
import { VentasComponent } from './ventas/ventas.component';

import { NuevaVentaComponent } from './nueva-venta/nueva-venta.component';
// Página de inicio
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path: '', component: HomeComponent },

  // Clientes
  { path: 'clientes', component: ClientesComponent },
  { path: 'clientes/agregar', component: AgregarClienteComponent },
  { path: 'clientes/editar/:id', component: EditarClienteComponent },

  // Empleados
  { path: 'empleados', component: EmpleadosComponent },
  { path: 'empleados/agregar', component: AgregarEmpleadoComponent },
  { path: 'empleados/editar/:id', component: EditarEmpleadoComponent },

  // Productos
  { path: 'productos', component: InventarioComponent },
  { path: 'productos/editar/:id', component: EditarProductoComponent },

  // Ventas
  { path: 'ventas', component: VentasComponent },
  { path: 'ventas/nueva', component: NuevaVentaComponent },

  // Ruta por defecto si no se encuentra
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
