import {Component} from "@angular/core"
import { FormBuilder, Validators,FormsModule, ReactiveFormsModule } from "@angular/forms"
import { Response } from '@angular/http';
import {Validate} from "./validate"
import { HttpService }   from './httpServices';
import {ShiftEntity} from './entity/ShiftEntity';
import {UploadBind,validateSwiftModel} from './entity/UploadBind';
import {Observable} from 'rxjs/Rx';


@Component({
    selector: "my-create",
    templateUrl: "assets/app/create.component.html",

})
export class CreateComponent {
   // ngForm : heroForm;onChange
    msg = "";
    obj = {};
    parserbuttondisabled = false;
    createbuttondisabled = false;
	  showErrMsg = false;
    model :  Validate('');
    shiftEntity : ShiftEntity;
    upload = new UploadBind();
    valModel = new validateSwiftModel();
    //valModel.validateMsg = "Loading...";
	  showMsgCreate = false;
    timer: any;
    message: string;
    loading = false;
    completed = false;
    processDetails:any;
    constructor(private httpService: HttpService) {}

    get diagnostic() { return JSON.stringify(this.model); }

    initializePolling() {
       Observable.interval(500).take(10).map((x) => x+1).subscribe((x) => {
        this.httpService.get( {apiMethod : '/processEvents'})
          .subscribe((res: Response) => {
             this.processDetails = JSON.parse(res._body).result;
          });
       ):
    }

    saveMessage(model) {
      this.initializePolling();
      this.obj=model;
      //this.message = "Message save processing...."
      this.loading =true;
      this.httpService.create({ apiMethod : '/createSwiftMessage', formData : this.obj}).subscribe((res: Response) => {
        this.loading = false;
        if(res._body === "Producer success"){
          this.showMsgCreate = true;
          this.completed =true;
          //this.message = "Message save success"
        }else{
          //this.message = "Message save failed"
        }
        /*this.timer = setTimeout(() => {
            this.completed=falsetrue;
            this.message = "";
          }, 5000);*/
        });
    }

    makeTextFile(text) {
      var textFile = null;
      var data = new Blob([text], {type: 'text/plain'});
      if (textFile !== null) {
       window.URL.revokeObjectURL(textFile);
      }
      textFile = window.URL.createObjectURL(data);
      return textFile;
    }

    verifyMessage(model) {
    this.initializePolling();
      this.loading =true;
     // this.message = "Message verification processing...."
      this.obj=model;
      this.httpService.create({
        apiMethod : '/parseSwiftMessage', formData : this.obj}).subscribe((res: Response) => {
            this.loading = false;
            var da = JSON.parse(res._body);
            if(da.result == "success"){
              this.completed =true;
              this.parserbuttondisabled =true;
	      this.createbuttondisabled = true;
              this.Cssdisabled =true;
              //this.message = "Message verification success"
            }else{
             // this.message = "Message verification failed"
            }
            /*this.timer = setTimeout(() => {
                this.message = "";
                this.completed =false;
            }, 15000);*/
        });
    }
    /// Do the code for upload swift message file
    uploadFile(){
        this.initializePolling();
        var formData = new FormData(); var obj = {};
		    this.parserbuttondisabled = false;
	this.createbuttondisabled = true;
        formData.append('file', this.file);
        this.httpService.create({
          apiMethod : '/uploadSwiftFile',
          fileData:formData
        }).subscribe((res: Response) => {
				  this.shiftEntity = JSON.parse(res._body);
				  if( this.shiftEntity.result === "false" ){
					   this.showErrMsg = true;
				  }else {
					   this.showErrMsg = false;
					   //block 1
            this.upload.applicationId=this.shiftEntity.swift.block1.applicationId; // 23g
            this.upload.serviceId=this.shiftEntity.swift.block1.serviceId; // 20c
            this.upload.logicalTerminal=this.shiftEntity.swift.block1.logicalTerminal;
            this.upload.sessionNumber=this.shiftEntity.swift.block1.sessionNumber;
            this.upload.sequenceNumber=this.shiftEntity.swift.block1.sequenceNumber;

            //block 2
            this.upload.messageType = this.shiftEntity.swift.block2.messageType;
            this.upload.receiverAddress = this.shiftEntity.swift.block2.receiverAddress;
            this.upload.messagePriority = this.shiftEntity.swift.block2.messagePriority;
            this.upload.deliveryMonitoring = this.shiftEntity.swift.block2.deliveryMonitoring;
            this.upload.obsolescencePeriod = this.shiftEntity.swift.block2.obsolescencePeriod;

            //block 3
            this.upload.Block_119 = this.shiftEntity.swift.block3[0];

            //block 4
            this.upload.MessageFunction23G=this.shiftEntity.swift.block4.tags[2].value; // 23g
            this.upload.SenderReference20C=this.shiftEntity.swift.block4.tags[1].value; // 20c
            this.upload.Instrument35B=this.shiftEntity.swift.block4.tags[7].value; // ok
            this.upload.InstrumentType12A=this.shiftEntity.swift.block4.tags[9].value;//ok
            this.upload.TradingDate98A		=  this.dateFormat(this.shiftEntity.swift.block4.tags[6].value);//ok
            this.upload.Currency11A=this.shiftEntity.swift.block4.tags[10].value;//ok
            this.upload.SettlementDate98A  = this.dateFormat(this.shiftEntity.swift.block4.tags[5].value);//ok
            this.upload.DateOfMaturity98A=this.dateFormat(this.shiftEntity.swift.block4.tags[11].value);  //ok
            this.upload.DateOfIssue98A=this.dateFormat(this.shiftEntity.swift.block4.tags[12].value);  //ok
            this.upload.Quantity36B=this.shiftEntity.swift.block4.tags[16].value;//ok

            this.upload.TransactionType22F=this.shiftEntity.swift.block4.tags[20].value;//ok

            this.upload.TransactionTypeCola22F=this.shiftEntity.swift.block4.tags[21].value;//ok

            this.upload.DeliveryAgent95P= this.shiftEntity.swift.block4.tags[27].value;//ok
            this.upload.Seller95R=this.shiftEntity.swift.block4.tags[30].value;//ok
            this.upload.SafeKeepingAgent97A=this.shiftEntity.swift.block4.tags[28].value;//ok
            this.upload.SettlementAmount36B=this.shiftEntity.swift.block4.tags[16].value;//ok

            this.upload.incomingMsg="I";
          }
        });
    }


	dateFormat(dateVal){
		console.log(dateVal);
		var res = dateVal.split('//');
		var a = res[1];
		var b = [a.slice(0, 4), "-", a.slice(4, 6), "-", a.slice(6, 8)].join('');
		return b;
	}

	validateMessage(model) {
	  this.initializePolling();
		this.obj=model;
		this.loading = true;
		//this.message = "Message validation is processing...."
		this.httpService.create( {
			apiMethod : '/validateSwiftMessage',formData : this.obj}).subscribe((res: Response) => {
			  this.loading = false;
				let msg = JSON.parse(res._body);
				this.valModel.validateMsg =  msg.reason;
				if(msg.result == "success"){
				  this.completed =true;
          this.createbuttondisabled = true;
          this.parserbuttondisabled = true;
          this.parserCssdisabled = true;
          //this.message = "Message validation success"
				}else{
				  //this.message = "Message validation failed"
				}
				/*this.timer = setTimeout(() => {
				    this.completed = false;
            this.message = "";
        }, 5000);*/
			});
    }

    file: File;
    onChange(event: EventTarget) {
        let eventObj: MSInputMethodContext = <MSInputMethodContext> event;
        let target: HTMLInputElement = <HTMLInputElement> eventObj.target;
        let files: FileList = target.files;
        this.file = files[0];
    }
}
