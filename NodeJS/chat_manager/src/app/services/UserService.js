import UserDao from "../dao/UserDao.js";

class UserService {
    constructor({}) {
        console.log(`new UserService`);
    }

    async listUser() {
        return await UserDao.getAllUsers();
    }

    async getUser(userId) {
        return await UserDao.getUser(userId);
    }

    addUser(user) {
        return UserDao.createUser(user);
    }
}

export default new UserService({});