import UserService from "../services/UserService.js";
import User from "../models/User.js";

class UserController {
    constructor({}) {
        console.log(`new UserController`);
    }

    async getUsers(request, response) {
        response.json(await UserService.listUser());
    }

    async getUser(request, response, next) {
        try {
            response.json(await UserService.getUser(request.params.userId));
        } catch (err) {
            next(err)
        }
    }

    createUser(request, response) {
        let user = new User(request.body);
        user = UserService.addUser(user);
        response.json(user);
    }

    updateUser(request, response) {}

    deleteUser(request, response) {}
}

export default new UserController({});