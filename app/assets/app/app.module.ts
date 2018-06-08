import './rxjs-extensions';

import { NgModule }      from '@angular/core';
import { DatePipe } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';
import { ModalModule,AlertModule,DatepickerModule} from 'ng2-bootstrap';
import { Ng2PaginationModule,PaginatePipe, PaginationControlsCmp, PaginationService} from 'ng2-pagination';
import {RouteConfig, Router, ROUTER_PROVIDERS} from '@angular/router';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent }         from './app.component';
import { DashboardComponent }   from './dashboard.component';
import { CreateComponent }      from './create.component';
import { LogoutComponent }      from './logout.component';
import { FilterPipe }           from './FilterPipe'
import { HttpService }          from './httpServices';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
	  ModalModule.forRoot(),
	  AlertModule.forRoot(),
    Ng2PaginationModule,
    DatepickerModule.forRoot(),
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    CreateComponent,
    LogoutComponent,
    FilterPipe
  ],
  providers: [ HttpService,DatePipe ],
  bootstrap: [ AppComponent ],
  directives: [PaginationControlsCmp,ROUTER_PROVIDERS],
  pipes: [PaginatePipe],
})
export class AppModule { }
