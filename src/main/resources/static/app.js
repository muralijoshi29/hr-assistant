var ws = null;
var sessionId = "";
var myMessage = {'msg': '', 'path':''};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
        $("#clientBox").show();
        showGreeting("BOT: Hi There! Please enter your applicant ID for us to assist with your interview status.");
    }
    else {
        $("#conversation").hide();
        $("#clientBox").hide();
        $("#greetings").html("");
    }
    
}

function connect() {
	var socket = new SockJS('/greeting');
    ws = Stomp.over(socket);
    ws.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        /*var url = stompClient.ws._transport.url;
        console.log(stompClient.ws._transport.url);
        url = url.replace("ws://localhost:8080/spring-security-mvc-socket/secured/room/",  "");
        url = url.replace("/websocket", "");
        url = url.replace(/^[0-9]+\//, "");
        console.log("Your current session is: " + url);
        sessionId = url;*/
        ws.subscribe('/user/queue/reply', function (greeting) {
        	console.log("received:"+greeting.body);
        	var msgObj= JSON.parse(greeting.body);
        	showGreeting(msgObj.msg);
        	myMessage['path'] = ','+msgObj.path+',';
        });
        ws.subscribe('/user/queue/errors', function (greeting) {
			 console.error(greeting.body);
        });
    });
    
}

function disconnect() {
    if (ws !== null) {
        ws.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
	showGreeting("You: "+ $("#msg").val());
	myMessage['msg'] = $("#msg").val();//, 'path': 'q1'};
	ws.send("/app/message", {}, JSON.stringify(myMessage));
	
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});