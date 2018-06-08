import {Component} from "@angular/core"
import {Location, LocationStrategy, PathLocationStrategy} from '@angular/common';

@Component({
    selector: "my-logout",
    template: "",
})

export class LogoutComponent {
	
	constructor(location: Location) { this.location = location; }
	
	ngOnInit() {
		
		this.location.go('/');
		window.location.reload()
	}
	
}
