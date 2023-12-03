/**
 * Is the function that emit data on the socket
 * @param {*} socket 
 * @param {String} event 
 * @param {object} data 
 */
export const emit = (socket, event, data) => {
   socket.emit(event, data);
}

/**
 * Is the function that calculate if the said card can attack the other card
 * @param {int} uidTurn
 * @param {int} uidAtt 
 * @param {int} cidAtt 
 * @param {int} cidDef 
 * @param {int} mana 
 * @returns void
 */
export const attack = (uidTurn, uidAtt, cidAtt, cidDef, mana) => {
   if(uidAtt == players[uidTurn]){
      if(mana == 0){
         emit(socket, 'error', 'Not enough mana');
         return;
      }
      if(uidAtt == players[0]){
         if(u1SelectedCards[cidAtt].attack < u2SelectedCards[cidDef].defense){
            emit(socket, 'error', 'Not enough attack');
            return;
         } else {
            calculateDamage(u1SelectedCards, u2SelectedCards);
         }
      }else if(uidAtt == players[1]){
         if(u2SelectedCards[cidAtt].attack < u1SelectedCards[cidDef].defense){
            emit(socket, 'error', 'Not enough attack');
            return;
         } else {
            calculateDamage(u2SelectedCards, u1SelectedCards);
         }
      }else{
         emit(socket, 'error', 'Not your turn');
         return;
      }
      if(uidTurn == 0){
         uidTurn = uidTurn+1;
      } else {
         uidTurn = uidTurn-1;
      }
      emit(socket, 'result', [u1SelectedCards, u2SelectedCards])
   } else {
      emit(socket, 'error', 'Not your turn');
   }
}

/**
 * is the function that calculate damage to both cards on attack
 */
export const calculateDamage = (uASelectedCards, uDSelectedCards) => {
   if (uASelectedCards[cidAtt].defense < uDSelectedCards[cidDef].defense) {
      uASelectedCards[cidAtt].health = uASelectedCards[cidAtt].health - (uDSelectedCards[cidDef].attack - uASelectedCards[cidAtt].defense);
   }
   uDSelectedCards[cidDef].health = uDSelectedCards[cidDef].health - (uASelectedCards[cidAtt].attack - uDSelectedCards[cidDef].defense);
   if (uDSelectedCards[cidDef].health <= 0) {
      uDSelectedCards.splice(cidDef, 1);
   }
   if (uASelectedCards[cidAtt].health <= 0) {
      uASelectedCards.splice(cidAtt, 1);
   }
}

/**
 * the function that test if the game is over
 */
export const isGameOver = () => {
   if (u1SelectedCards.length == 0) {
      emit(socket, 'gameOver', players[1]);
   } else if (u2SelectedCards.length == 0) {
      emit(socket, 'gameOver', players[0]);
   }
}