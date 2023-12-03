import 'stompit';

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
 * This function emit data to a specific room
 * @param {*} socket 
 * @param {String} room 
 * @param {String} event 
 * @param {object} data 
 */
export const emitToRoom = (socket, room, event, data) => {
   console.log("emit to room : "+room)
   socket.to(room).emit(event, data);
}

/**
 * This function make the user join a selected room
 * @param {*} socket 
 * @param {int} uidsrc 
 * @param {int} uiddest 
 * @returns String
 */
export const joinRoom = (socket, uidsrc, uiddest) => {
   if(!uiddest) {
      socket.join("general")
      return "general"
   } else {
      const room = findRoom(uidsrc, uiddest)
      socket.join(room)
      return room
   }
}

/**
 * This function find the room name
 * @param {int} uidsrc 
 * @param {int} uiddest 
 * @returns String
 */
const findRoom = (uidsrc, uiddest) => {
   return uiddest < uidsrc ? 'room'+uiddest+'-'+uidsrc : 'room'+uidsrc+'-'+uiddest
}

/**
 * This function send a message to the ActiveMQ queue
 * @param {String} queueName 
 * @param {String} message 
 */
export const sendMessageToQueue = async (queueName, message) => {
   const client = await connect({
      host: 'tcp://localhost',
      port: 61616,
      user: 'admin',
      pass: 'admin',
     });
   const sender = await client.send({ destination: queueName });
   sender.write(message);
   sender.end();
   client.disconnect();
}