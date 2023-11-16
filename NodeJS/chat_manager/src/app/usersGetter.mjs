import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';

const mock = new MockAdapter(axios);

mock.onGet('localhost:8081/userapi/public/users').reply(200, {
 users: [
    { id: 1, login: 'JDoe', password: '', firstname: 'John', lastname: 'Doe', wallet : '100'},
    { id: 2, login: 'JaneD', password: '', firstname: 'Jane', lastname: 'Doe', wallet : '100'},
    { id: 3, login: 'Zozz', password: '', firstname: 'Enzob', lastname: 'Louini', wallet : '2022'},
 ],
});

/**
 * Fetches users from the Spring Boot service.
 */
export const fetchUsers = async () => {
  try {
    const response = await axios.get('localhost:8081/userapi/public/users');
    return response.data.users;
  } catch (error) {
    console.error('Failed to fetch users:', error);
  }
}