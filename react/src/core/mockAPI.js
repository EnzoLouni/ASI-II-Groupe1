import { axiosMockAdapterInstance } from "./axiosMockInstance";

axiosMockAdapterInstance.onPost(process.env.REACT_APP_RPROXY+"userapi").reply(200)
axiosMockAdapterInstance.onPost(process.env.REACT_APP_RPROXY+"authapi").reply(200, {
    id:1,
    login:"ZOZZ",
    wallet:2022
})
axiosMockAdapterInstance.onGet(process.env.REACT_APP_RPROXY+"cardapi").reply(200,
    [
        {
          "id":1,
          "name": "Zozz enragé",
          "description": "Cette carte représente un Zozzer enragé.",
          "family": "Zozzer",
          "affinity": "Terre",
          "imgUrl": "img/zozz_enrage.png",
          "smallImgUrl": "img/small/zozz_aenrage.png",
          "energy": 1.5,
          "hp": 80,
          "defense": 70,
          "attack": 50,
          "price": 2.99
        },
        {
          "id":2,
          "name": "Zozz anti-germanique",
          "description": "Le Zozz anti-germanique est un Zozz spécialisé dans la lutte contre les Zozzers de type germanique.",
          "family": "Zozz",
          "affinity": "Terre",
          "imgUrl": "img/zozz_anti_germanique.png",
          "smallImgUrl": "img/small/zozz_anti_germanique.png",
          "energy": 2.5,
          "hp": 90,
          "defense": 80,
          "attack": 70,
          "price": 5.99
        },
        {
          "id":3,
          "name": "ZéoZolitique",
          "description": "Le ZéoZolitique est un Zozz influencé par la géopolitique.",
          "family": "Zozz",
          "affinity": "Feu",
          "imgUrl": "img/zeozolitique.png",
          "smallImgUrl": "img/small/zeozolitique.png",
          "energy": 3.0,
          "hp": 70,
          "defense": 60,
          "attack": 80,
          "price": 6.99
        },
        {
          "id":4,
          "name": "Zozzermanique",
          "description": "Le Zozzermanique est un Zozzer de type germanique, puissant et agressif.",
          "family": "Zozzer",
          "affinity": "Eau",
          "imgUrl": "img/zozzgermanique.png",
          "smallImgUrl": "img/small/zozzgermanique.png",
          "energy": 2.5,
          "hp": 80,
          "defense": 70,
          "attack": 60,
          "price": 5.99
        },
        {
          "id":5,
          "name": "Zozz chef de projet",
          "description": "Le Zozz chef de projet est un Zozz spécialisé dans la gestion de projets complexes. Il a été commandité par le Zozz anti-germanique pour couler une dalle stratégique.",
          "family": "Zozz",
          "affinity": "Air",
          "imgUrl": "img/zozz_chef_de_projet.png",
          "smallImgUrl": "img/small/zozz_chef_de_projet.png",
          "energy": 3.5,
          "hp": 60,
          "defense": 40,
          "attack": 50,
          "price": 7.99
        },
        {
          "id":6,
          "name": "Zozzers du bâtiment",
          "description": "Les Zozzers du bâtiment sont des employés du Zozz chef de projet spécialisés dans la construction et le travail manuel.",
          "family": "Zozzer",
          "affinity": "Terre",
          "imgUrl": "img/zozzers_du_batiment.png",
          "smallImgUrl": "img/small/zozzers_du_batiment.png",
          "energy": 2.0,
          "hp": 50,
          "defense": 60,
          "attack": 70,
          "price": 4.99
        },
        {
          "id":7,
          "name": "Zozz cynique",
          "description": "Le Zozz cynique est connu pour son attitude sarcastique et son sens de l'ironie. Son attaque est réduite, car il préfère se moquer de ses adversaires plutôt que de les attaquer directement.",
          "family": "Zozz",
          "affinity": "Feu",
          "imgUrl": "img/zozz_cynique.png",
          "smallImgUrl": "img/small/zozz_cynique.png",
          "energy": 2.0,
          "hp": 60,
          "defense": 30,
          "attack": 50,
          "price": 4.99
        },
        {
          "id":8,
          "name": "Zhistorien",
          "description": "Le Zhistorien est un Zozz passionné d'histoire et de connaissances.",
          "family": "Zozz",
          "affinity": "Eau",
          "imgUrl": "img/zhistorien.png",
          "smallImgUrl": "img/small/zhistorien.png",
          "energy": 2.5,
          "hp": 75,
          "defense": 40,
          "attack": 60,
          "price": 5.99
        },
        {
          "id":9,
          "name": "Zozzer ultime - MaTTHIas",
          "description": "MaTTHIas est le Zozzer ultime, représentant la puissance et la perfection dans le monde de \"Zozzémon\".",
          "family": "Zozzer",
          "affinity": "Air",
          "imgUrl": "img/zozzer_ultime.png",
          "smallImgUrl": "img/small/zozzer_ultime.png",
          "energy": 5.0,
          "hp": 100,
          "defense": 100,
          "attack": 100,
          "price": 100
        },
        {
          "id":10,
          "name": "Néo-Zozzer - Raphaël",
          "description": "Raphaël est un Néo-Zozzer, une forme évoluée et améliorée de Zozzer dans le monde de \"Zozzémon\".",
          "family": "Zozzer",
          "affinity": "Terre",
          "imgUrl": "img/neo_zozzer.png",
          "smallImgUrl": "img/small/neo_zozzer.png",
          "energy": 3.5,
          "hp": 80,
          "defense": 75,
          "attack": 60,
          "price": 8.99
        },
        {
          "id":11,
          "name": "Anti-Zozzer - Mathis",
          "description": "Mathis est un Anti-Zozzer, spécialisé dans la lutte contre les Zozzers adversaires.",
          "family": "Zozzer",
          "affinity": "Feu",
          "imgUrl": "img/anti_zozzer.png",
          "smallImgUrl": "img/small/anti_zozzer.png",
          "energy": 3.0,
          "hp": 85,
          "defense": 80,
          "attack": 75,
          "price": 7.99
        },
        {
          "id":12,
          "name": "Fidèle Zozzer - Thony",
          "description": "Thony est un Fidèle Zozzer, dévoué et loyal envers ses alliés.",
          "family": "Zozzer",
          "affinity": "Eau",
          "imgUrl": "img/fidele_zozzer.png",
          "smallImgUrl": "img/small/fidele_zozzer.png",
          "energy": 2.5,
          "hp": 70,
          "defense": 60,
          "attack": 80,
          "price": 6.99
        },
        {
          "id":13,
          "name": "ZAlexis l'allergique - le Bref",
          "description": "ZAlexis l'allergique, surnommé \"le Bref\", est un Zozz qui souffre d'allergies sévères.",
          "family": "Zozzer",
          "affinity": "Air",
          "imgUrl": "img/zalexis_allergique.png",
          "smallImgUrl": "img/small/zalexis_allergique.png",
          "energy": 2.0,
          "hp": 60,
          "defense": 50,
          "attack": 40,
          "price": 4.99
        },
        {
          "id":14,
          "name": "ZAlexis le Gros - les 60€",
          "description": "ZAlexis le Gros, surnommé \"les 60€\", est un Zozz imposant et puissant.",
          "family": "Zozzer",
          "affinity": "Terre",
          "imgUrl": "img/zalexis_gros.png",
          "smallImgUrl": "img/small/zalexis_gros.png",
          "energy": 2.5,
          "hp": 100,
          "defense": 80,
          "attack": 60,
          "price": 5.99
        },
        {
          "id":15,
          "name": "Le Zozz",
          "description": "Le Zozz est l'un des Zozzers de base dans le monde de \"Zozzémon\".",
          "family": "Zozz",
          "affinity": "Feu",
          "imgUrl": "img/zozz_pro_git.png",
          "smallImgUrl": "img/small/zozz_pro_git.png",
          "energy": 3.0,
          "hp": 80,
          "defense": 70,
          "attack": 60,
          "price": 6.99
        },
        {
          "id":16,
          "name": "Zozz le pro de git",
          "description": "Zozz, le Pro de Git, est un expert dans l'utilisation du versionnement et du contrôle de code avec Git.",
          "family": "Zozz",
          "affinity": "Feu",
          "imgUrl": "img/zozz_pro_git.png",
          "smallImgUrl": "img/small/zozz_pro_git.png",
          "energy": 3.0,
          "hp": 70,
          "defense": 60,
          "attack": 70,
          "price": 6.99
        },
        {
          "id":17,
          "name": "Zactiviste",
          "description": "Zactiviste, est un fervent défenseur des droits et des causes sociales.",
          "family": "Zozz",
          "affinity": "Eau",
          "imgUrl": "img/zactiviste.png",
          "smallImgUrl": "img/small/zactiviste.png",
          "energy": 3.5,
          "hp": 60,
          "defense": 50,
          "attack": 60,
          "price": 7.99
        }
    ])

axiosMockAdapterInstance.onPost(process.env.REACT_APP_RPROXY+"storeapi").reply(200)
