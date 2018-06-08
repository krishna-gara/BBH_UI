import { Component }  from '@angular/core';
import { Response }   from '@angular/http';

import {Location, LocationStrategy, PathLocationStrategy} from '@angular/common';

import { Login }      from './login';
import { HttpService }   from './httpServices';

@Component({
  selector: 'my-app',
providers: [Location, {provide: LocationStrategy, useClass: PathLocationStrategy}],
  templateUrl:'assets/login/app.component.html',
})
export class AppComponent {

  public title = 'BBH';
  login =  new Login();
  location : Location;
  obj = {}; showMsgCreate = false;
  constructor(private httpService: HttpService, location: Location) { this.location = location; }

    validate(model){

		  //console.log(model);
		  this.obj = model;

      //this.location.go('/dashboard');
			//window.location.reload();

		  this.httpService.create( {
				apiMethod : '/authenticate',          formData : this.obj,
          } ).subscribe((res: Response) => {
				var da = JSON.parse(res._body);
				//console.log(da.result);
				//this.router.navigate(['dashboard']);
				//window.location.href = "dashboard";
				if(da.result == "success"){
					this.location.go('/dashboard');
					window.location.reload()
					//this.router.navigate(['./create']]);
				}else{
					this.showMsgCreate = true;
				}
          } );
	}
}
