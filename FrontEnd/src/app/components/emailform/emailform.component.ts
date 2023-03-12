import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EmailService } from 'src/app/service/email.service';

@Component({
  selector: 'app-emailform',
  templateUrl: './emailform.component.html',
  styleUrls: ['./emailform.component.css']
})
export class EmailformComponent {

  constructor(
    private emailService:EmailService,
    private snak: MatSnackBar
  ){}
  data={
    to:"",
    subject:"",
    message:""
  }
  flag:boolean = false;


  submitEmail(){ 
    if(this.data.to=='' || this.data.subject=='' || this.data.message==''){
      this.snak.open("Fields can not ne empty!!", "Ok")
      return
    }
    this.flag=true
    console.log("sending email to backend")
    this.emailService.sendEmail(this.data).subscribe(
      response=>{
        console.log(response)
        this.flag=false
        this.snak.open("Email Sent Successfully !!!", "Ok")
      },
      error=>{
        console.log(error)
        this.flag=false
        this.snak.open("Some Error Occurred...", "Ok")
      }
    )
    this.data.to=""
    this.data.subject=""
    this.data.message=""
  }
}
