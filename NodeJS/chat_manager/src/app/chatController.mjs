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
const io = new Server(server, { cors: { origin: '*' } });

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




   io.on('connection', (socket) => {
      console.log('A user connected');

      socket.on('getUsers', () => {
         console.log('jenvoie');
         socket.emit('users', usersJSON);
      });

      socket.on('join', (uidsrc, uiddest) => {
         if(!uiddest) socket.join("general")

         const roomName = uiddest < uidsrc ? 'room'+uiddest+'-'+uidsrc : 'room'+uidsrc+'-'+uiddest
         console.log(roomName)
         socket.join(roomName)
         socket.emit("room name",roomName)
      });

      socket.on('disconnect', () => {
         console.log('A user disconnected');
      });

      socket.on('chat message', (msg, room) => {
         console.log(msg)
         socket.to(room).emit('chat message', msg);
      });
   });


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