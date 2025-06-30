import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  totalClientes: number = 0; // 👈 Agrega esto

  // Puedes luego actualizarlo desde una API o servicio
}
