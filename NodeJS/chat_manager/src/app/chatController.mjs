import { fileURLToPath } from 'url';
import { dirname } from 'path';
import { resolve } from 'path';
import express from 'express';
import http from 'http';
import { Server } from 'socket.io';
import { fetchUsers } from './usersGetter.mjs';
import EventEmitter from'events';


const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

const app = express();
const server = http.createServer(app);
const io = new Server(server);


let usersJSON = JSON.parse(await fetchUsers().then((users) => JSON.stringify(users)));
console.log("--------------------------------------");
console.log("This is the result of the API call : ");
console.log(usersJSON);
console.log("--------------------------------------");

//Seras surement a supprimer l'orsque le front react seras fonctionnel car il n'y auras plus bsoin d'envoyer une page juste de se connecter au serveur.
//------------------------------------------------------------
app.use(express.static(resolve(__dirname, '../public')));

app.get('/', (req, res) => {
 res.sendFile(__dirname+'/../www/index.html');
});
//------------------------------------------------------------


class MyEmitter extends EventEmitter {}
const myEmitter = new MyEmitter();
myEmitter.on('general', () => {
   console.log('general');
   io.on('connection', (socket) => {
      console.log('A user connected');

      socket.on('disconnect', () => {
         console.log('A user disconnected');
      });

      socket.on('chat message', (msg) => {
         io.emit('chat message', msg);
      });
   });
}
);

//à commenter quand le front react seras fonctionnel
myEmitter.emit('general');

myEmitter.on('u2u', () => {
   console.log('u2u');

   let rooms = {};

   io.on('connection', (socket) => {
      console.log('A user connected');

      socket.on('disconnect', () => {
         console.log('A user disconnected');
      });

      socket.on('join room', (roomName) => {
         if (rooms[roomName]) {
            rooms[roomName].push(socket.id);
         } else {
            rooms[roomName] = [socket.id];
         }
         socket.join(roomName);
      });

      socket.on('chat message', (msg, uid) => {
         socket.to(uid).emit('chat message', msg);
      });
   });
});

//à commenter quand le front react seras fonctionnel
myEmitter.emit('u2u');

// io.on('connection', (socket) => {
//   console.log('A user connected');

//   socket.on('disconnect', () => {
//      console.log('A user disconnected');
//   });

//   socket.on('chat message', (msg) => {
//      io.emit('chat message', msg);
//   });
// });

export { server, app, io };