var stompClient = null;

var socket = new SockJS('/gs-guide-websocket');
stompClient = Stomp.over(socket);
stompClient.connect({}, function (frame) {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/u/'+ userId, function (greeting) {
        //Написать функцию обновления данных на странице
    });
});
function sendFromServer() {
    stompClient.send("/app/u/" + userId, {}, JSON.stringify({'arrayListUsers': [1]}));
}

setInterval(sendFromServer, 5000);
