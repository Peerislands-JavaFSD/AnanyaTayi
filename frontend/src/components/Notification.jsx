import { useEffect, useState } from "react";
import { connectWebSocket, sendMessage } from "../services/websocket";

function Notification() {
    const [messages, setMessages] = useState([]);
    const [input, setInput] = useState("");

    useEffect(() => {
        connectWebSocket((msg) => {
            setMessages((prev) => [...prev, msg]);
        });
    }, []);

    return (
        <div style={{ padding: "20px" }}>
            <h2>🔔 Notifications</h2>

            <input
                value={input}
                onChange={(e) => setInput(e.target.value)}
                placeholder="Enter message"
            />

            <button onClick={() => sendMessage(input)}>
                Send
            </button>

            <div style={{ marginTop: "20px" }}>
                {messages.map((msg, i) => (
                    <p key={i}>{msg}</p>
                ))}
            </div>
        </div>
    );
}

export default Notification;