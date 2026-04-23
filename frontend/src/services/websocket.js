import SockJS from "sockjs-client";
import Stomp from "stompjs";

let stompClient = null;

export const connectWebSocket = (onMessage) => {
    const socket = new SockJS("http://localhost:8080/ws");
    stompClient = Stomp.over(socket);

    stompClient.connect({}, () => {
        console.log("Connected");

        stompClient.subscribe("/topic/notifications", (msg) => {
            const data = JSON.parse(msg.body);
            onMessage(data.message);
        });
    });
};

export const sendMessage = (message) => {
    if (stompClient) {
        stompClient.send("/app/send", {}, JSON.stringify({ message }));
    }
};