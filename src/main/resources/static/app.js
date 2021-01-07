var ws = null;
var sessionId = "";
var myMessage = {'msg': '', 'applicant':null};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
        $("#clientBox").show();
    }
    else {
        $("#conversation").hide();
        $("#clientBox").hide();
        $("#message").html("");
    }
    
}

function connect() {
	var socket = new SockJS('/chatroom');
    ws = Stomp.over(socket);
    ws.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        ws.subscribe('/user/queue/reply', function (response) {
        	var msgObj= JSON.parse(response.body);
        	showMessage('BOT: ' + msgObj.msg);
        	myMessage['applicant'] = msgObj.applicant;
        });
        ws.subscribe('/user/queue/errors', function (response) {
			 console.error(response.body);
        });
    });
    
}

function disconnect() {
    if (ws !== null) {
        ws.disconnect();
    }
    setConnected(false);
    myMessage = {'msg': '', 'applicant':null};
    console.log("Disconnected");
}

function sendName() {
	showMessage("You: "+ $("#msg").val());
	myMessage['msg'] = $("#msg").val();
	ws.send("/app/message", {}, JSON.stringify(myMessage));
	
}

function showMessage(message) {
    $("#message").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});