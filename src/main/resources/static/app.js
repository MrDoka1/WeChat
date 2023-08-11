var stompClient = null;


var socket = new SockJS('/websocket');
stompClient = Stomp.over(socket);

function connectWebSocket(id) {
    stompClient.connect({}, function (frame) {

        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/'+ id, function (answerMessage) {
            sendAnswerMessage(JSON.parse(answerMessage.body));
        });
    });
}

connectWebSocket(id);


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
        if(e.keyCode===13 && !e.shiftKey) {
            $('.newMessage-chat__send').click();
            $('.newMessage-chat__message').val("")
        }
    });


    $(".newMessage-chat__send").click(function() {
        let textMessage = String($(".newMessage-chat__message").val());
        if (textMessage.replace(/\s/g, "") !== "") {
            sendMessage(id, textMessage);
            $(".newMessage-chat__message").val("");
        }
    })

    // *** Нажатие на чата в левой колонке с чатами ***
    $(".main-chats__item").click(function () {
        if ($(".main-page__chat").html() !== "") {
            if (this.id !== $(".chat-main__nav").attr("id")) {
                replaceChat(this);
            } else {
                this.classList.remove("--active")
                $(".main-page__chat").html("");
            }
        } else {
            $(".main-page__chat").append(bodyChat);
            replaceChat(this);
        }
    })

    function replaceChat(element) {
        $(".nav-chat__name").text(element.querySelector(".item-chat__name").innerHTML);
        $(".chat-main__nav").attr("id", element.id);
        $(".nav-chat__status").text("oбновление...").removeClass("--online");
        window.history.pushState("object or string", "Title", "/im?sel=" + element.id);
        id = element.id;
        $(".chat-main__body").text("")
        stompClientUserMessage.send("/app/u/" + userId, {}, JSON.stringify({
            'arrayListUsers': listOnline,
            'getMaessage': element.id
        }));
    }

});

let bodyChat = `<div class="chat-main__nav nav-chat">
                <div class="nav-chat__column">
                    <div class="nav-chat__avatar"><img src="img/avatars/main.jfif" alt="Дима"></div>
                </div>
                <div class="nav-chat__column">
                    <div class="nav-chat__name"></div>
                    <div class="nav-chat__status"></div>
                </div>
            </div>

            <div class="chat-main__body"></div>
            
            <div class="chat-main__newMessage newMessage-chat">
                <div class="newMessage-chat__body">
                    <div class="newMessage-chat__smile">
                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1"
                             id="Capa_1" x="0px" y="0px" width="438.533px" height="438.533px"
                             viewBox="0 0 438.533 438.533" style="enable-background:new 0 0 438.533 438.533;"
                             xml:space="preserve">
                                        <g>
                                            <g>
                                                <path
                                                    d="M409.132,109.205c-19.608-33.592-46.205-60.189-79.798-79.796C295.733,9.803,259.054,0.002,219.273,0.002    c-39.781,0-76.47,9.801-110.063,29.407c-33.595,19.604-60.192,46.201-79.8,79.796C9.801,142.802,0,179.491,0,219.269    c0,39.78,9.804,76.463,29.407,110.062c19.607,33.592,46.204,60.189,79.799,79.798c33.597,19.603,70.283,29.403,110.063,29.403    s76.47-9.801,110.065-29.403c33.593-19.608,60.189-46.206,79.795-79.798c19.603-33.599,29.403-70.284,29.403-110.062    C438.533,179.487,428.732,142.797,409.132,109.205z M387.433,290.215c-9.709,22.556-22.696,41.973-38.969,58.245    c-16.271,16.269-35.689,29.26-58.245,38.965c-22.555,9.712-46.202,14.564-70.946,14.564c-24.744,0-48.391-4.859-70.948-14.564    c-22.554-9.705-41.971-22.696-58.245-38.965c-16.269-16.275-29.259-35.687-38.97-58.245    c-9.707-22.552-14.562-46.206-14.562-70.946c0-24.744,4.854-48.391,14.562-70.948c9.707-22.554,22.697-41.968,38.97-58.245    c16.274-16.269,35.691-29.26,58.245-38.97c22.554-9.704,46.205-14.558,70.948-14.558c24.74,0,48.395,4.851,70.946,14.558    c22.556,9.707,41.97,22.698,58.245,38.97c16.272,16.274,29.26,35.688,38.969,58.245c9.709,22.554,14.564,46.201,14.564,70.948    C402.001,244.013,397.142,267.666,387.433,290.215z"/>
                                                <path
                                                    d="M312.06,247.533c-4.757-1.532-9.418-1.136-13.99,1.144s-7.617,5.899-9.13,10.849    c-4.754,15.229-13.562,27.555-26.412,36.973c-12.844,9.421-27.265,14.134-43.255,14.134c-15.986,0-30.402-4.716-43.252-14.134    c-12.847-9.421-21.65-21.744-26.409-36.973c-1.521-4.949-4.521-8.569-8.992-10.849c-4.473-2.279-9.087-2.676-13.846-1.144    c-4.949,1.52-8.564,4.518-10.85,8.987c-2.284,4.469-2.666,9.096-1.141,13.846c7.039,23.038,20.173,41.593,39.397,55.679    c19.226,14.086,40.924,21.121,65.096,21.121c24.169,0,45.873-7.035,65.098-21.121c19.212-14.093,32.347-32.641,39.389-55.679    c1.533-4.75,1.15-9.377-1.136-13.846C320.334,252.051,316.81,249.061,312.06,247.533z"/>
                                                <path
                                                    d="M146.181,182.727c10.085,0,18.699-3.576,25.837-10.709c7.139-7.135,10.708-15.749,10.708-25.837    c0-10.089-3.569-18.699-10.708-25.837s-15.752-10.709-25.837-10.709c-10.088,0-18.702,3.571-25.84,10.709    c-7.135,7.139-10.707,15.749-10.707,25.837c0,10.088,3.568,18.702,10.707,25.837C127.482,179.154,136.092,182.727,146.181,182.727    z"/>
                                                <path
                                                    d="M292.359,109.633c-10.089,0-18.706,3.571-25.845,10.709c-7.132,7.139-10.708,15.749-10.708,25.837    c0,10.088,3.576,18.702,10.708,25.837c7.139,7.137,15.756,10.709,25.845,10.709c10.081,0,18.698-3.576,25.837-10.709    c7.139-7.135,10.708-15.749,10.708-25.837c0-10.089-3.569-18.699-10.708-25.837S302.44,109.633,292.359,109.633z"/>
                                            </g>
                                        </g>
                                    </svg>
                    </div>
                    <textarea class="newMessage-chat__message" placeholder="Message" autoFocus></textarea>
                </div>
                <button type="submit" class="newMessage-chat__send">
                    <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" id="Layer_1"
                         style="enable-background:new 0 0 30.2 30.1;" version="1.1" viewBox="0 0 30.2 30.1"
                         xml:space="preserve">
										<path class="st0"
                                              d="M2.1,14.6C8.9,12,28.5,4,28.5,4l-3.9,22.6c-0.2,1.1-1.5,1.5-2.3,0.8l-6.1-5.1l-4.3,4l0.7-6.7l13-12.3l-16,10  l1,5.7l-3.3-5.3l-5-1.6C1.5,15.8,1.4,14.8,2.1,14.6z"/>
									</svg>
                </button>
            </div>`;