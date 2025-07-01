import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductoService } from '../services/producto.service';
import { Producto } from '../models/producto.model';

@Component({
  selector: 'app-editar-producto',
  templateUrl: './editar-producto.component.html',
  styleUrl: './editar-producto.component.css'
})
export class EditarProductoComponent implements OnInit {
  producto: Producto = { idproducto: 0, descripcion: '', precio: 0, stock: 0, estado: 'ACTIVO' };
  loading = true;
  error = '';

  constructor(
    private route: ActivatedRoute,
    private productoService: ProductoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.productoService.getProductoById(id).subscribe({
        next: (data) => {
          this.producto = data;
          this.loading = false;
        },
        error: (err) => {
          this.error = 'No se pudo cargar el producto';
          this.loading = false;
        }
      });
    } else {
      this.error = 'ID de producto inválido';
      this.loading = false;
    }
  }

  guardar() {
    this.productoService.updateProducto(this.producto).subscribe({
      next: () => this.router.navigate(['/productos']),
      error: () => this.error = 'No se pudo actualizar el producto'
    });
  }
}
