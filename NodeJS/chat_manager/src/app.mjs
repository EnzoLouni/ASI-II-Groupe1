import { server } from './app/chatController.mjs';

const port = 3001;

server.listen(port, () => {
 console.log('listening on *:'+port);
});