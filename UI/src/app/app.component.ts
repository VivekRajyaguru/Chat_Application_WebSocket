import { Component, EventEmitter } from '@angular/core';
import { environment } from '../environments/environment';
import * as Stomp from 'stompjs'
import * as SockJS from 'sockjs-client';
import * as $ from 'jquery';
import { Constants } from './shared/Constants';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'WebSocket App';
  userName;
  disableFlag;
  constructor() {
  }

  initializeWebSocket() {
    let ws = new SockJS(environment.socket_url);
    Constants.stompClient = Stomp.over(ws);
    Constants.stompClient.connect({}, function() {
    });
    this.disableFlag = true;
  }

  connectDisConnectFunction(flag) {
    if(flag) {
      Constants.stompClient.connect({}, function() {});
      this.disableFlag = true;
    } else {
      Constants.stompClient.disconnect({}, function() {});
      this.disableFlag = false;
    }
  }

  sendMessage() {
    Constants.stompClient.send('/app/addUser', {}, JSON.stringify({'name': this.userName}) );
    this.userName = '';
  }
}
