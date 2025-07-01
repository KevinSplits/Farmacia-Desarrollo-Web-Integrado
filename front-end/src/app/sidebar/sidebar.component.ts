import { Component } from '@angular/core';
import { LayoutService } from '../services/layout.service';


@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
  isVisible: boolean = true;

  constructor(private layoutService: LayoutService) {
    this.layoutService.getSidebarVisibility().subscribe(visible => {
      this.isVisible = visible;
    });
  }
}