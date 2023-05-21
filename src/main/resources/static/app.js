var stompClient = null;


var socket = new SockJS('/websocket');
stompClient = Stomp.over(socket);
stompClient.connect({}, function (frame) {

    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/'+ id, function (answerMessage) {
        // showGreeting(JSON.parse(greeting.body).content);
        sendAnswerMessage(JSON.parse(answerMessage.body));
    });
});




function sendMessage(id, textMessage) {
    textMessage = processingMessage(textMessage);
    stompClient.send("/app/" + id, {}, JSON.stringify({
        'chatId': id,
        'content': textMessage
    }))
}



function sendAnswerMessage(answerMessage) {
    let textMessage = answerMessage.content;
    let time = answerMessage.timeString;
    console.log(answerMessage.id + " " + userId);
    let forWhom;
    if (answerMessage.id === userId) {
        forWhom = "_for-them";
    } else {
        forWhom = "_for-me";
    }
    $(".chat-main__body").append(`<div class="chat-main__message message-chat ${forWhom}">
                                <div class="message-chat__body">
                                    <span class="message-chat__text">${textMessage.replace("\n", "<br>")}</span>
                                    <div class="message-chat__time">${time}</div>
                                </div>
                            </div>`)
}


function processingMessage(message) {
    while (message.substring(0, 1) === "\n") {
        message = message.substring(1);
    }
    while (message.substring(message.length - 1) === "\n") {
        message = message.substring(0, message.length - 1);
    }
    return message;
}

$(function () {

    $('.newMessage-chat__message').keypress(function(e){
        if(e.keyCode==13 && !e.shiftKey) {
            $('.newMessage-chat__send').click();
            $('.newMessage-chat__message').val("")
        }
    });


    $(".newMessage-chat__send").click(function() {
        let textMessage = String($(".newMessage-chat__message").val());
        if (textMessage.replace(/\s/g, "") != "") {
            sendMessage(id, textMessage);
            $(".newMessage-chat__message").val("");
        }
    })

    $(".main-chats__item").click(function () {
        if (this.id !== $(".chat-main__nav").attr("id")) {
            $(".nav-chat__name").text(this.querySelector(".item-chat__name").innerHTML);
            $(".chat-main__nav").attr("id", this.id);
            $(".nav-chat__status").text("oбновление...").removeClass("--online");
            window.history.pushState("object or string", "Title", "/im?sel=" + this.id);

            console.log(this.querySelector(".item-chat__name"))
        }
    })

});

// window.history.pushState("object or string", "Title", "/new-url");