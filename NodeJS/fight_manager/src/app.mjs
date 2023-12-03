import { server } from './app/fightController.mjs';

const port = 3002;

server.listen(port, () => {
 console.log('listening on *:'+port);
});