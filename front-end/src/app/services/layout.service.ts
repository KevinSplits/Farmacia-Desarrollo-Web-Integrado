import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LayoutService {
  private sidebarVisible = new BehaviorSubject<boolean>(true);
  
  hideSidebar() {
    this.sidebarVisible.next(false);
  }

  showSidebar() {
    this.sidebarVisible.next(true);
  }

  getSidebarVisibility() {
    return this.sidebarVisible.asObservable();
  }
}