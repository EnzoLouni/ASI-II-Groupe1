import {Router} from "express";
import PingRouter from "./PingRouter.js";
import UserController from "../controllers/UserController.js";

const BASE_PATH = '/users';

const UserRouter = Router();
export default UserRouter;

UserRouter.use(BASE_PATH, PingRouter);

UserRouter.route(BASE_PATH)
    .get(UserController.getUsers)
    .post(UserController.createUser);

UserRouter.route(`${BASE_PATH}/:userId`)
    .get(UserController.getUser);
