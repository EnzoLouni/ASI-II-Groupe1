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

myEmitter.on('chat', (uidsrc, uiddest) => {
   console.log('chat args : ' + uidsrc + ' ' + uiddest);

   io.on('connection', (socket) => {
      console.log('A user connected');

      socket.on('getUsers', () => {
         socket.emit('user', usersJSON);
      });

      socket.on('join', (room) => {
         if (socket.rooms.has(room)) {
            socket.join('room'+uiddest+'-'+uidsrc);
         }else{
            socket.join('room'+uidsrc+'-'+uiddest);
         }
         socket.on('disconnect', () => {
            console.log('A user disconnected');
         });
      });
      if (socket.rooms.has('room'+uiddest+'-'+uidsrc)) {
         socket.join('room'+uiddest+'-'+uidsrc);
      }else{
         socket.join('room'+uidsrc+'-'+uiddest);
      }
      socket.on('disconnect', () => {
         console.log('A user disconnected');
      });

      socket.on('chat message', (msg, room) => {
         
         socket.to(room).emit('chat message', msg);
      });
   });
});

//à commenter quand le front react seras fonctionnel
myEmitter.emit('chat', 1, 3);

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