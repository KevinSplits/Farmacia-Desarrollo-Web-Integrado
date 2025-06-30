import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClientesComponent } from './clientes/clientes.component';
import { AgregarClienteComponent } from './agregar-cliente/agregar-cliente.component';
import { AgregarEmpleadoComponent } from './agregar-empleado/agregar-empleado.component';
import { EmpleadosComponent } from './empleados/empleados.component';
import { EditarClienteComponent } from './editar-cliente/editar-cliente.component';
import { EditarEmpleadoComponent } from './editar-empleado/editar-empleado.component';
import { EditarProductoComponent } from './editar-producto/editar-producto.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { InventarioComponent } from './inventario/inventario.component';
import { NuevaVentaComponent } from './nueva-venta/nueva-venta.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { VentasComponent } from './ventas/ventas.component';

@NgModule({
  declarations: [
    AppComponent,
    ClientesComponent,
    AgregarClienteComponent,
    AgregarEmpleadoComponent,
    EmpleadosComponent,
    EditarClienteComponent,
    EditarEmpleadoComponent,
    EditarProductoComponent,
    HeaderComponent,
    HomeComponent,
    InventarioComponent,
    NuevaVentaComponent,
    SidebarComponent,
    VentasComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
