var stompClientUserMessage = null;

var socket = new SockJS('/websocket');
stompClientUserMessage = Stomp.over(socket);
stompClientUserMessage.connect({}, function (frame) {
    console.log('Connected: ' + frame);
    stompClientUserMessage.subscribe('/topic/u/'+ userId, function (userStatusAnswer) {
        update(JSON.parse(userStatusAnswer.body))
    });
});


function update(userList) {
    for (let i=0; i<userList.length; i++) {

        // ** Обновление статуса текущего чата **
        if (userList[i].id.toString() === $(".chat-main__nav").attr("id").toString()) {
            if (userList[i].online) {
                $(".nav-chat__status").addClass("--online").text("в сети");
            } else {
                $(".nav-chat__status").removeClass("--online").text("был(а) в " + stringToTime(userList[i]["lastOnline"]))
            }
        }
        // ** Обновление статуса в чатах **
        if(userList[i].online) {
            $("#" + userList[i].id + " .item-chat__avatar").addClass("--online");
        } else {
            $("#" + userList[i].id + " .item-chat__avatar").removeClass("--online");
        }
    }
}

function stringToTime(str) {
    return str.substring(11, 16);
}

function sendFromServer() {
    stompClientUserMessage.send("/app/u/" + userId, {}, JSON.stringify({'arrayListUsers': listOnline}));
}

setInterval(sendFromServer, 5000);
