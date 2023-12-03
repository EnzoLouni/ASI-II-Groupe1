import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';

const mock = new MockAdapter(axios);

mock.onGet('localhost:8081/cardapi/public/cards/1/user').reply(200, {
  cards: [
    { "id": 1, "name": "Zozz enragé", "description": "Cette carte représente un Zozzer enragé.", "type": "Zozzer", "element": "Terre", "image": "img/zozz_enrage.png", "thumbnail": "img/small/zozz_aenrage.png", "health": 1.5, "attack": 80, "defense": 70, "speed": 50, "price": 2.99 },
    { "id": 2, "name": "Zozz anti-germanique", "description": "Le Zozz anti-germanique est un Zozz spécialisé dans la lutte contre les Zozzers de type germanique.", "type": "Zozz", "element": "Terre", "image": "img/zozz_anti_germanique.png", "thumbnail": "img/small/zozz_anti_germanique.png", "health": 2.5, "attack": 90, "defense": 80, "speed": 70, "price": 5.99 },
    { "id": 3, "name": "ZéoZolitique", "description": "Le ZéoZolitique est un Zozz influencé par la géopolitique.", "type": "Zozz", "element": "Feu", "image": "img/zeozolitique.png", "thumbnail": "img/small/zeozolitique.png", "health": 3.0, "attack": 70, "defense": 60, "speed": 80, "price": 6.99 },
    { "id": 4, "name": "Zozzermanique", "description": "Le Zozzermanique est un Zozzer de type germanique, puissant et agressif.", "type": "Zozzer", "element": "Eau", "image": "img/zozzgermanique.png", "thumbnail": "img/small/zozzgermanique.png", "health": 2.5, "attack": 80, "defense": 70, "speed": 60, "price": 5.99 },
    { "id": 5, "name": "Zozz chef de projet", "description": "Le Zozz chef de projet est un Zozz spécialisé dans la gestion de projets complexes. Il a été commandité par le Zozz anti-germanique pour couler une dalle stratégique.", "type": "Zozz", "element": "Air", "image": "img/zozz_chef_de_projet.png", "thumbnail": "img/small/zozz_chef_de_projet.png", "health": 3.5, "attack": 60, "defense": 40, "speed": 50, "price": 7.99 },
    { "id": 6, "name": "Zozzers du bâtiment", "description": "Les Zozzers du bâtiment sont des employés du Zozz chef de projet spécialisés dans la construction et le travail manuel.", "type": "Zozzer", "element": "Terre", "image": "img/zozzers_du_batiment.png", "thumbnail": "img/small/zozzers_du_batiment.png", "health": 2.0, "attack": 50, "defense": 60, "speed": 70, "price": 4.99 },
    { "id": 7, "name": "Zozz cynique", "description": "Le Zozz cynique est connu pour son attitude sarcastique et son sens de l'ironie. Son attaque est réduite, car il préfère se moquer de ses adversaires plutôt que de les attaquer directement.", "type": "Zozz", "element": "Feu", "image": "img/zozz_cynique.png", "thumbnail": "img/small/zozz_cynique.png", "health": 2.0, "attack": 60, "defense": 30, "speed": 50, "price": 4.99 }
  ],
});

export const fetchUserCards = async () => {
  try {
    const response = await axios.get('localhost:8081/cardapi/public/cards/1/user');
    return response.data.cards;
  } catch (error) {
    console.error('Failed to fetch users:', error);
  }
}