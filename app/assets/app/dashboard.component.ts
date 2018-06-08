import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { HttpService }   from './httpServices';
import { FormBuilder, Validators } from '@angular/forms';
import { Validate} from './validate';
import { NgFor, NgModel} from 'angular2/common';
import { Response } from '@angular/http';
import {UploadBind,validateSwiftModel} from './entity/UploadBind';
import {Router,ROUTER_PROVIDERS } from '@angular/router';
import {ShiftEntity} from './entity/ShiftEntity';
import {Observable} from 'rxjs/Rx';

@Component({

  selector: 'my-dashboard',
  templateUrl: 'assets/app/dashboard.component.html'
})

export class DashboardComponent{

    ngForm : heroForm;
    msg=""; str="";
    obj={};
    upload = new UploadBind();
    parserbuttondisabled = false;
    createbuttondisabled = false;
	  shiftEntity : ShiftEntity;
	  messages: ShiftEntity[];
    model:Validate;
    startDate: any;
    endDate:any;
    timer:any;
    message: string;
    loading = false;
    completed = false;
    processing: any;
    constructor(private httpService: HttpService ,private router: Router) {
          this.model = new Validate('');
          var datePipe = new DatePipe();
          this.endDate=new Date();
          this.startDate = new Date(this.endDate.getFullYear(), this.endDate.getMonth(),1,0,0,0 );
          this.model.from=datePipe.transform(this.startDate, 'yyyy-MM-dd');
          this.model.to=datePipe.transform(this.endDate, 'yyyy-MM-dd');
    }

	  ngOnInit() {
		  this.messages = [];
		  this.searchRequest(this.model);
	  }

    initializePolling() {
       Observable.interval(500).take(10).map((x) => x+1).subscribe((x) => {
        this.httpService.get( {apiMethod : '/processEvents'})
          .subscribe((res: Response) => {
             this.processDetails = JSON.parse(res._body).result;
          });
       ):
    }

    set startStrDate(e){
        this.startDate = new Date(e);
        //this.startDate.setFullYear(d.getUTCFullYear(), d.getUTCMonth(), d.getUTCDate();
    }
    get startStrDate(){
        return this.startDate.toISOString().substring(0, 10);
    }
    set endStrDate(e){
            this.endDate = new Date(e);
            //this.startDate.setFullYear(d.getUTCFullYear(), d.getUTCMonth(), d.getUTCDate();
        }
        get endStrDate(){
            return this.endDate.toISOString().substring(0, 10);
    }
	  get diagnostic() { return JSON.stringify(this.model); }

    downLoadAsFile(text, name, type){
      //console.log(this.today);
       var file = new Blob([text], {type: type});
        var isIE = /*@cc_on!@*/false || !!document.documentMode;
        if (isIE)
        {
            window.navigator.msSaveOrOpenBlob(file, name);
        }
        else
        {
            var a = document.createElement('a');
            a.href = URL.createObjectURL(file);
            a.download = name;
            a.click();
           // saveAs(file,"helloworld.txt");

        }
    }


  validationdata(model) {
		      this.upload.applicationId=model.applicationId;
          this.upload.serviceId=model.serviceId;
          this.upload.logicalTerminal=model.logicalTerminal;
          this.upload.sessionNumber=model.sessionNumber;
          this.upload.sequenceNumber=model.sequenceNumber;
          this.upload.messageType=model.messageType;
          this.upload.receiverAddress=model.receiverAddress;
          this.upload.messagePriority=model.messagePriority;
          this.upload.deliveryMonitoring=model.deliveryMonitoring;
          this.upload.obsolescencePeriod=model.obsolescencePeriod;
          this.upload.Block_119=model.messageType;
          this.upload.MessageFunction23G=model.newg_23G;
          this.upload.TradingDate98A=model.trad_98A;
          this.upload.instrument_35B=model.isin_35B;
          this.upload.quantity_36B=model.fiac_36B;
          this.upload.seller_95R=model.seller_95R;
          this.upload.buyer_95P=model.deag_95P;
          this.upload.settlement_place_36B=model.settlement_place_36B;
          this.parserbuttondisabled =true;
          this.Cssdisabled =true;
    }


    searchRequest(model){
      this.initializePolling();
      this.loading =true;
     // this.message = "Search query processing...."
      this.obj=model;
      this.httpService.create( {
			      apiMethod : '/searchQuery',formData : this.obj,
			  } ).subscribe((res: Response) => {
			    this.loading = false;
			    this.completed = true;
			   // this.message = "Search query success"
				  var da = JSON.parse(res._body);
				  //console.log(da.searchMessage);
				  this.messages = JSON.parse(da.searchMessage);
          //this.timer = setTimeout(() => {
             // this.message = "";
             // this.completed =false;
          //}, 5000);
			  } );
    }

    /// for date
    private _date: string;
    @Input() set date(d: Date) {
        this._date = this.toDateString(d);
    }
    @Output() dateChange: EventEmitter<Date>;

    private toDateString(date: Date): string {
        return (date.getFullYear().toString() + '-' + ("0" + (date.getMonth() + 1)).slice(-2) + '-' + ("0" + (date.getDate())).slice(-2));
    }
    private parseDateString(date:string): Date {
       date = date.replace('T','-');
       var parts = date.split('-');

       // new Date(year, month [, day [, hours[, minutes[, seconds[, ms]]]]])
       //console.log(new Date(parts[0], parts[1]-1, parts[2]));
       return new Date(parts[0], parts[1]-1, parts[2]); // Note: months are 0-based
    }
    private onDateChange(value: string): void {
        if (value != this._date) {


            var parsedDate = this.parseDateString(value);

            // check if date is valid first
            if (parsedDate.getTime() != NaN) {
               this._date = value;
              // this.dateChange.emit(parsedDate);
            }
        }
    }
    ///for date

}
