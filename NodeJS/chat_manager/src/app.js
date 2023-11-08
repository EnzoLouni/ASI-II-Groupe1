import CONFIG from './config.json' assert { type: 'json' };
import bodyParser from "body-parser";
import express from 'express'
import PingRouter from "./app/routers/PingRouter.js";
import UserRouter from "./app/routers/UserRouter.js";
import AppMiddleware from "./app/middleware/AppMiddleware.js";
global.CONFIG = CONFIG;

const app = express();

// Déclaration des middleware
app.use(AppMiddleware.logRequests);
app.use(bodyParser.json({}));
//app.use(bodyParser.text({}));
//app.use(bodyParser.raw({}));
//app.use(bodyParser.urlencoded({extended: true}));

// Ressources statiques
app.use(express.static(CONFIG.www));

// Routes
app.use(CONFIG.basePath, PingRouter);
app.use(CONFIG.basePath, UserRouter);

// Gestion des 404 (doit etre après les routes)
app.use(AppMiddleware.notFound);

// Gestion des erreur (doit être déclaré en dernier)
app.use(AppMiddleware.errorHandler)

// Démarrage de l'application
app.listen(CONFIG.port, () => console.log(`Listening http://localhost:${CONFIG.port}`));