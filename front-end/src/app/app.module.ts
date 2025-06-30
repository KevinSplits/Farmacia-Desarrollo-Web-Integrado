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

@NgModule({
  declarations: [
    AppComponent,
    ClientesComponent,
    AgregarClienteComponent,
    AgregarEmpleadoComponent,
    EmpleadosComponent,
    EditarClienteComponent,
    EditarEmpleadoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
