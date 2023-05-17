var stompClientUserMessage = null;

var socket = new SockJS('/websocket');
stompClientUserMessage = Stomp.over(socket);
stompClientUserMessage.connect({}, function (frame) {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClientUserMessage.subscribe('/topic/u/'+ userId, function (greeting) {
        //Написать функцию обновления данных на странице
    });
});
function sendFromServer() {
    stompClientUserMessage.send("/app/u/" + userId, {}, JSON.stringify({'arrayListUsers': [1]}));
}

setInterval(sendFromServer, 5000);
