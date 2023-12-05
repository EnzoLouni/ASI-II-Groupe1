import express from 'express';
import http from 'http';
import { Server } from 'socket.io';
import { fetchUsers } from './usersGetter.mjs';
import { emit, emitToRoom, joinRoom } from './chatService.mjs';
import 'stompit';

const app = express();
const server = http.createServer(app);
const io = new Server(server, { cors: { origin: '*' } });

const connectOptions = {
   host: 'tcp://localhost',
   port: 61616,
   user: 'admin',
   pass: 'admin',
}



const queueName = 'chatQueue';
 
io.on('connection', (socket) => {
   console.log('A user connected');

   socket.on('getUsers', async  () => {
      const usersJSON = await fetchUsers;

      console.log('jenvoie');
      emit(socket,'users', usersJSON);
      console.log(usersJSON)
   });

   socket.on('join', (uidsrc, uiddest) => {
      emit(socket, "room name",joinRoom(socket, uidsrc, uiddest));
   });

   socket.on('disconnect', () => {
      console.log('A user disconnected');
   });

   socket.on('chat message', (msg, room) => {
      console.log(msg)
      emitToRoom(socket, room, 'chat message', msg);

      // Sending message to ActiveMQ
      // const client = stompit.connect(connectOptions, (error, client) => {
      //    if (error) {
      //       return console.error(error)
      //    }
      //    const sender = client.send({ destination: queueName });
      //    sender.write(message);
      //    sender.end();
      //    client.disconnect();
      // });
      
   });
});

export { server };