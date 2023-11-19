import { io } from 'socket.io-client';

// "undefined" means the URL will be computed from the `window.location` object
const URL = process.env.REACT_APP_RPROXY+"/notifapi"

export const notifSocket = io(URL);