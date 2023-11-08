import { fileURLToPath } from 'url';
import { dirname } from 'path';
import { resolve } from 'path';
import express from 'express';
import http from 'http';
import { Server } from 'socket.io';
import './usersGetter.mjs';

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

const app = express();
const server = http.createServer(app);
const io = new Server(server);

let usersJSON = JSON.parse(fetchUsers());
console.log(usersJSON);

//Seras surement a supprimer l'orsque le front react seras fonctionnel car il n'y auras plus bsoin d'envoyer une page juste de se connecter au serveur.
//------------------------------------------------------------
app.use(express.static(resolve(__dirname, '../public')));

app.get('/', (req, res) => {
 res.sendFile(__dirname+'/../www/index.html');
});
//------------------------------------------------------------

io.on('connection', (socket) => {
 console.log('A user connected');

 socket.on('disconnect', () => {
    console.log('A user disconnected');
 });

 socket.on('chat message', (msg) => {
    io.emit('chat message', msg);
 });
});

export { server, app, io };