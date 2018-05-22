import { Component, OnInit } from '@angular/core';
import * as Stomp from 'stompjs'
import * as SockJS from 'sockjs-client';
import * as $ from 'jquery';
import { Constants } from '../shared/Constants';

@Component({
  selector: 'app-demo',
  templateUrl: './demo.component.html',
  styleUrls: ['./demo.component.css']
})
export class DemoComponent implements OnInit {
  userName;
  comments;
  constructor() {
    Constants.stompClient.subscribe("/topic/showComments", message => {
      if (message.body) {
        $('#messages').append("<div class='container'><img src='assets/avatar.png' alt='User' class='right' style=' float: left;max-width: 30px;width: 5%;margin-right: 20px;border-radius: 50%;'><p>"+JSON.parse(message.body).content+"</p></div>");
      }
    });
  }

  ngOnInit() {
  }

  sendMessage() {
    Constants.stompClient.send('/app/chatComments', {}, JSON.stringify({'userName': this.userName, 'comments': this.comments}) );
    this.comments = '';
    
  }

}
