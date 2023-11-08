// Mock
import User from "../models/User.js";
import {setTimeout} from 'timers/promises';

const listUsers = [];
listUsers.push(new User({
    id: 1, nom: 'TOTO', prenom: 'Toto', age: 12, mail: 'toto@yopmail.de'
}), new User({
    id: 2, nom: 'TATA', prenom: 'Tata', age: 12, mail: 'tata@yopmail.de'
}));

class UserDao {
    constructor({}) {
        console.log(`new UserDao`);
    }

    async getAllUsers() {
        const ms = Math.floor(Math.random() * 1000);
        return await setTimeout(ms, listUsers);
    }

    async getUser(userId) {
        const ms = Math.floor(Math.random() * 1000);

        const user = await setTimeout(ms, listUsers
            .filter(u => u.id === Number(userId))
            .pop()
        );

        if (!user) {
            throw new Error('User not found !')
        }
        return user
    }

    createUser(user) {
        user.id = listUsers.length + 1;
        listUsers.push(user);
        return user;
    }
}

export default new UserDao({});