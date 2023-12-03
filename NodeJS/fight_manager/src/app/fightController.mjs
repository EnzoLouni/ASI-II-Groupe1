import express from 'express';
import http from 'http';
import { Server } from 'socket.io';
import { fetchUserCards } from './bddCalls.mjs';
import { emit, attack, isGameOver } from './fightService.mjs';

const app = express();
const server = http.createServer(app);
const io = new Server(server, { cors: { origin: '*' } });

   io.on('connection', (socket) => {
      console.log('A user connected');
      const maxUsersPlaying = 2;
      let usersConnected = 0;
      let isTheGameOn = false;
      let players = [];
      let u1SelectedCards = [];
      let u2SelectedCards = [];
      let uidTurn = 0;

      
      socket.on('Connect', (uid) => {
         usersConnected++;
         if(usersConnected >= maxUsersPlaying && isTheGameOn==false){
            isTheGameOn = true;
            players.push(uid);
            emit(socket, 'startGame', 'start');
         } else if (isTheGameOn==true) {
            emit(socket, 'spectategame', 'spectate');

         }
      });

      socket.on('getUsercard', async () => {
         const cardsJSON = await fetchUserCards;
         console.log('jenvoie');
         emit(socket,'users', cardsJSON);
      });


      socket.on('disconnect', () => {
         console.log('A user disconnected');
      });

      socket.on('selectedCards', (uid, data) => {
         if(u1SelectedCards.length == 0 && uid == players[0]){
            u1SelectedCards = data;
            console.log(u1SelectedCards);
         } else {
            u2SelectedCards = data;
            console.log(u2SelectedCards);
         }
      });

      socket.on('getSelectedCards', () => {
         emit(socket,'selectedCards', [u1SelectedCards, u2SelectedCards]);
      });

      socket.on('attack', (uidAtt, uidDef, cidAtt, cidDef, mana) => {
         attack(uidTurn ,uidAtt, uidDef, cidAtt, cidDef, mana);
         isGameOver();
   });
});

export { server };