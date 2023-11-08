import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import pkg from 'stompit';
import io from 'socket.io-client';
const { createClient } = pkg;

const mock = new MockAdapter(axios);

mock.onGet('https://your-springboot-service.com/users').reply(200, {
 users: [
    { id: 1, name: 'John Doe' },
    { id: 2, name: 'Jane Doe' },
 ],
});

const stompit = createClient();

const webSocket = io('wss://your-springboot-service.com/socket');

export default {
 data() {
    return {
      users: [],
      messages: [],
      messageContent: '',
    };
 },
 methods: {
    async fetchUsers() {
      try {
        const response = await axios.get('https://your-springboot-service.com/users');
        this.users = response.data.users;
      } catch (error) {
        console.error('Failed to fetch users:', error);
      }
    },
    async sendMessage() {
      if (this.messageContent) {
        const headers = { 'content-type': 'application/json' };
        const body = JSON.stringify({ message: this.messageContent });

        await stompit.connect((error, client) => {
          if (error) {
            console.error('Failed to connect to Stomp broker:', error);
            return;
          }

          client.publish({ destination: '/queue/messages', headers, body }, (error) => {
            if (error) {
              console.error('Failed to send message:', error);
            }

            client.disconnect();
          });
        });

        this.messageContent = '';
      }
    },
 },
 mounted() {
    this.fetchUsers();

    webSocket.on('message', (message) => {
      this.messages.push(message);
    });
 },
};