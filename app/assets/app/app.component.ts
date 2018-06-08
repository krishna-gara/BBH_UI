import { Component }          from '@angular/core';

@Component({
  selector: 'my-app',
  template: `
    <ul class="nav nav-tabs">
        <li [ngClass]="{active: activeTab===true}" (click)="activeTab=true" ><a routerLink="/dashboard" routerLinkActive="active" data-toggle="tab">Display Messages</a></li>
        <li [ngClass]="{active: activeTab===false}" (click)="activeTab=false"><a routerLink="/create" routerLinkActive="active" data-toggle="tab">Create</a></li>
        <li class="pull-right"><a routerLink="/logout"  routerLinkActive="active" data-toggle="tab">Log Out</a></li>
    </ul>

    <router-outlet></router-outlet>
  `,

})
export class AppComponent {
  public title = 'BBH';
  activeTab=true;
}
