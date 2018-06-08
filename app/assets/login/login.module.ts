import './rxjs-extensions';

import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';
import { AppRoutingModule } from './app-routing.module';


import { AppComponent }         from './app.component';
import { HttpService }   from './httpServices';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,	
  ],
  declarations: [
    AppComponent,
  ],
  providers: [ HttpService ],
  bootstrap: [ AppComponent ],  
})
export class LoginModule { }
