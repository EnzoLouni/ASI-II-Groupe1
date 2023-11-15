import { fileURLToPath } from 'url';
import { dirname } from 'path';
import express from 'express';
import http from 'http';
import { Server } from 'socket.io';
import { server, app, io } from './app/chatController.mjs';

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);
const port = 3000;

server.listen(port, () => {
 console.log('listening on *:'+port);
});