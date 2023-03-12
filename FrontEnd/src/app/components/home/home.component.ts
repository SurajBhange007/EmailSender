import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  ngOnInit(): void {
  }

  constructor(private snak: MatSnackBar){}

  btnClick(){
    console.log("button is clicked")
    this.snak.open("Hey Welcome to this app", "Ok")
  }

}
