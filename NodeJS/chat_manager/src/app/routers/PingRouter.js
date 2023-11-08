import {Router} from "express";
import PingController from "../controllers/PingController.js";

const BASE_PATH = '/ping';

const PingRouter = Router();
export default PingRouter;

PingRouter.route(BASE_PATH)
    .get(PingController.getPong);
