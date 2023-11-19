CREATE DATABASE card_manager;
\c card_manager;

DROP TABLE IF EXISTS public.card_model;
CREATE TABLE public.card_model (
	id SERIAL,
	energy FLOAT,
	hp FLOAT,
	defense FLOAT,
	attack FLOAT,
	price FLOAT,
    user_id INTEGER DEFAULT NULL,
	card_reference_id INTEGER,
    CONSTRAINT PK_CARD_MODEL PRIMARY KEY (id)
);

DROP TABLE IF EXISTS public.card_reference;
DROP SEQUENCE IF EXISTS card_reference_sequence;
CREATE TABLE public.card_reference(
	id SERIAL,
	name VARCHAR(50) UNIQUE DEFAULT NULL,
	description VARCHAR(3000) DEFAULT NULL,
	family VARCHAR(100) DEFAULT NULL,
	affinity VARCHAR(100) DEFAULT NULL,
	img_url VARCHAR(200) DEFAULT NULL,
	small_img_url VARCHAR(200) DEFAULT NULL,
    CONSTRAINT PK_CARD_REFERENCE PRIMARY KEY (id)
);

\c card_manager;
INSERT INTO card_reference (id,name, description, family, affinity, img_url, small_img_url)
VALUES 
    (1,'Zozz enragé', 'Cette carte représente un Zozzer enragé.', 'Zozzer', 'Terre', 'img/zozz_enrage.png', 'img/small/zozz_aenrage.png'),
    (2,'Zozz anti-germanique', 'Le Zozz anti-germanique est un Zozz spécialisé dans la lutte contre les Zozzers de type germanique.', 'Zozz', 'Terre', 'img/zozz_anti_germanique.png', 'img/small/zozz_anti_germanique.png'),
    (3,'ZéoZolitique', 'Le ZéoZolitique est un Zozz influencé par la géopolitique.', 'Zozz', 'Feu', 'img/zeozolitique.png', 'img/small/zeozolitique.png'),
    (4,'Zozzermanique', 'Le Zozzermanique est un Zozzer de type germanique, puissant et agressif.', 'Zozzer', 'Eau', 'img/zozzgermanique.png', 'img/small/zozzgermanique.png'),
    (5,'Zozz chef de projet', 'Le Zozz chef de projet est un Zozz spécialisé dans la gestion de projets complexes. Il a été commandité par le Zozz anti-germanique pour couler une dalle stratégique.', 'Zozz', 'Air', 'img/zozz_chef_de_projet.png', 'img/small/zozz_chef_de_projet.png'),
    (6,'Zozzers du bâtiment', 'Les Zozzers du bâtiment sont des employés du Zozz chef de projet spécialisés dans la construction et le travail manuel.', 'Zozzer', 'Terre', 'img/zozzers_du_batiment.png', 'img/small/zozzers_du_batiment.png'),
    (7,'Zozz cynique', 'Le Zozz cynique est connu pour son attitude sarcastique et son sens de l''ironie. Son attaque est réduite, car il préfère se moquer de ses adversaires plutôt que de les attaquer directement.', 'Zozz', 'Feu', 'img/zozz_cynique.png', 'img/small/zozz_cynique.png'),
    (8,'Zhistorien', 'Le Zhistorien est un Zozz passionné d''histoire et de connaissances.', 'Zozz', 'Eau', 'img/zhistorien.png', 'img/small/zhistorien.png'),
    (9,'Zozzer ultime - MaTTHIas', 'MaTTHIas est le Zozzer ultime, représentant la puissance et la perfection dans le monde de "Zozzémon".', 'Zozzer', 'Air', 'img/zozzer_ultime.png', 'img/small/zozzer_ultime.png'),
    (10,'Néo-Zozzer - Raphaël', 'Raphaël est un Néo-Zozzer, une forme évoluée et améliorée de Zozzer dans le monde de "Zozzémon". ', 'Zozzer', 'Terre', 'img/neo_zozzer.png', 'img/small/neo_zozzer.png'),
    (11,'Anti-Zozzer - Mathis', 'Mathis est un Anti-Zozzer, spécialisé dans la lutte contre les Zozzers adversaires.', 'Zozzer', 'Feu', 'img/anti_zozzer.png', 'img/small/anti_zozzer.png'),
    (12,'Fidèle Zozzer - Thony', 'Thony est un Fidèle Zozzer, dévoué et loyal envers ses alliés.', 'Zozzer', 'Eau', 'img/fidele_zozzer.png', 'img/small/fidele_zozzer.png'),
    (13,'ZAlexis l''allergique - le Bref', 'ZAlexis l''allergique, surnommé "le Bref", est un Zozz qui souffre d''allergies sévères.', 'Zozzer', 'Air', 'img/zalexis_allergique.png', 'img/small/zalexis_allergique.png'),
    (14,'ZAlexis le Gros - les 60€', 'ZAlexis le Gros, surnommé "les 60€", est un Zozz imposant et puissant.', 'Zozzer', 'Terre', 'img/zalexis_gros.png', 'img/small/zalexis_gros.png'),
    (15,'Le Zozz', 'Le Zozz est l''un des Zozzers de base dans le monde de "Zozzémon".', 'Zozz', 'Feu', 'img/zozz_pro_git.png', 'img/small/zozz_pro_git.png'),
    (16,'Zozz le pro de git', 'Zozz, le Pro de Git, est un expert dans l''utilisation du versionnement et du contrôle de code avec Git.', 'Zozz', 'Feu', 'img/zozz_pro_git.png', 'img/small/zozz_pro_git.png'),
    (17,'Zactiviste', 'Zactiviste, est un fervent défenseur des droits et des causes sociales.', 'Zozz', 'Eau', 'img/zozz_activiste.png', 'img/small/zozz_activiste.png'),
    (18,'Zozz - Barrage à Zacron', 'Zozz, le Barrage à Zacron, est un Zozz déterminé à contrecarrer les plans de Zacron, un puissant antagoniste dans le monde de "Zozzémon".', 'Zozz', 'Feu', 'img/zozz_pro_git.png', 'img/small/zozz_pro_git.png'),
    (19,'Zacron - Le Visionnaire', 'Zacron, surnommé "Le Visionnaire", est un Zozz charismatique et habile dans la prise de décisions. Capable de motiver son équipe et d''adopter des approches diplomatiques pour gagner les combats.', 'Zacron', 'Feu', 'img/zozz_pro_git.png', 'img/small/zozz_pro_git.png'),
    (20,'Zozz - Fidèle à l''Empereur (Zapoléon)', 'Zozz, Fidèle à l''Empereur, est un être d''une loyauté indéfectible, façonné par l''aura et l''héritage de Zapoléon. Il incarne l''esprit et les valeurs de l''empire zapoléonien, se tenant prêt à protéger son empereur bien-aimé et l''avenir de l''empire.', 'Zozz', 'Terre', 'img/zozz_fidele_empereur.png', 'img/small/zozz_fidele_empereur.png'),
    (21,'L''Empreur Zapoléon', 'L''Empreur Zapoléon est un chef charismatique. Il incarne la grandeur et l''autorité impériale.', 'Zapoleon', 'Terre', 'img/lempreur_zapoleon.png', 'img/small/lempreur_zapoleon.png'),
    (22,'L''Empreur Zules Zézzar', 'L''Empreur Zules Zézzar est un leader charismatique, empreint de sagesse et de puissance. Il incarne l''autorité et la grandeur dans le monde de "Zozzémon".\n\nIl est doté d''une capacité spéciale unique, "Éclat Impérial," qui lui permet d''irradier une aura de pouvoir, augmentant temporairement les statistiques de son équipe. L''Empreur Zules Zézzar est un dirigeant vénéré dans le monde de "Zozzémon," prêt à conduire ses troupes vers la victoire tel un empereur romain conduisant son peuple vers la grandeur.', 'Zules Zézzar', 'Terre', 'img/lempreur_zules_zezzar.png', 'img/small/lempreur_zules_zezzar.png'),
    (23,'Zozz Fidèle à l''Empereur (Zules Zézzar)', 'Zozz, Fidèle à l''Empereur, est un modèle de loyauté et de dévouement envers Zules Zézzar, un des plus grands leaders de l''histoire.\n\nIl incarne la loyauté sans faille et le dévouement qui ont marqué l''histoire de l''Empire romain. En tant que Zozz Fidèle à l''Empereur, il est prêt à tout pour protéger et servir Zules Zézzar, comme les légionnaires romains qui ont suivi leur empereur sur les champs de bataille pour l''empire. Sa loyauté est inébranlable, et il est prêt à défendre l''héritage de Zules Zézzar avec détermination.', 'Zozz', 'Terre', 'img/zozz_fidele_empereur_zules_zezzar.png', 'img/small/zozz_fidele_empereur_zules_zezzar.png'),
    (24,'Zaugustus', 'Zaugustus est un puissant leader.\nIl est doté d''une capacité spéciale unique, "Règne Impérial," qui lui permet d''augmenter la résistance de son équipe pendant les batailles, reflétant sa capacité à diriger avec sagesse. Zaugustus est un empereur vénéré dans le monde de "Zozzémon," prêt à guider ses troupes vers la victoire et à préserver la grandeur de son empire.', 'Zaugustus', 'Terre', 'img/zaugustus.png', 'img/small/zaugustus.png'),
    (25,'Zozz Fidèle à l''Empereur (Augustus)', 'Zozz, Fidèle à l''Empereur, est un modèle de loyauté et de dévouement envers Zugustus, un des plus grands empereurs de l''histoire romaine.\n\nIl incarne la loyauté sans faille et le dévouement qui ont marqué l''empire Zugustien. En tant que Zozz Fidèle à l''Empereur, il est prêt à tout pour protéger et servir Zugustus, comme les légionnaires romains qui ont suivi leur empereur sur les champs de bataille pour l''empire. Sa loyauté est inébranlable, et il est prêt à défendre l''héritage Zugustien avec détermination.', 'Zozz', 'Terre', 'img/zozz_fidele_empereur_augustus.png', 'img/small/zozz_fidele_empereur_augustus.png'),
    (26,'Zeanne d''Arc', 'Zeanne d''Arc est une héroïne légendaire. Elle incarne la bravoure et la détermination dans le monde de "Zozzémon".\n\nElle est dotée d''une capacité spéciale unique, "Ferveur Sacrée," qui lui permet d''augmenter la puissance de son équipe lors des combats, reflétant sa capacité à inspirer ses troupes. Zeanne d''Arc est vénérée dans le monde de "Zozzémon," prête à mener ses coéquipiers vers la victoire avec courage et dévouement, l''héroïne historique a mené son pays à la gloire.', 'Zeanne d''Arc', 'Terre', 'img/zeanne_darc.png', 'img/small/zeanne_darc.png'),
    (27,'Zozz Tiers Liste des Rois de France', 'Zozz Tiers Liste des Rois de France est une encyclopédie ambulante des rois de France. Il incarne la connaissance historique et l''érudition dans le monde de "Zozzémon".\n\nIl est doté d''une capacité spéciale unique, "Sagesse Royale," qui lui permet d''augmenter la précision de son équipe lors des combats, reflétant sa capacité à partager sa sagesse historique. Zozz Tiers Liste des Rois de France est vénéré dans le monde de "Zozzémon," prêt à guider ses coéquipiers avec sa connaissance des rois de France tout au long de l''histoire.', 'Zozz', 'Terre', 'img/zozz_tiers_liste_des_rois_de_france.png', 'img/small/zozz_tiers_liste_des_rois_de_france.png'),
    (28,'Zozz Haine envers Le Bref', 'Zozz Haine envers le Bref est animé par une détermination farouche et une antipathie envers son ennemi juré, "Zalexis Le Bref."\n\nDoté d''une capacité spéciale unique, "Vengeance Brûlante," il canalise sa haine pour augmenter sa vitesse et son pouvoir d''attaque lorsqu''il se trouve en face de "Le Bref." Zozz Haine envers le Bref est prêt à poursuivre son ennemi jusqu''au bout du monde pour obtenir vengeance et justice.', 'Zozz', 'Feu', 'img/zozz_haine_envers_le_bref.png', 'img/small/zozz_haine_envers_le_bref.png'),
    (29,'Zozz Terreur de la Baleine', 'Zozz Peur de la Baleine est constamment hanté par l''apparition redoutée de la baleine, une créature légendaire qui le fait trembler.\n\nDoté d''une capacité spéciale unique, "Résistance Épique," il renforce sa défense lorsqu''il sent la présence d''une carte de type Baleine. Zozz Terreur de la Baleine est prêt à se protéger à tout moment, prêt à affronter cette crainte qui le poursuit, même sur le champ de bataille.', 'Zozz', 'Eau', 'img/zozz_terreur_de_la_baleine.png', 'img/small/zozz_terreur_de_la_baleine.png'),
    (30,'La Baleine', 'La Baleine est une carte redoutée dans le monde de "Skull King." Elle incarne une créature légendaire des mers, symbole de puissance et de mystère.\n\nDotée d''une capacité spéciale unique, "Rage Océanique," elle peut déchaîner des attaques puissantes qui déferlent comme des vagues. La Baleine est prête à plonger dans l''action et à imposer sa suprématie sur le champ de bataille, tout comme elle le fait dans les eaux tumultueuses des océans.', 'Baleine', 'Eau', 'img/la_baleine.png', 'img/small/la_baleine.png'),
    (31,'Zobidique', 'Zobidique est une créature mystérieuse et puissante dans le monde de "Zozzémon." \n\nDotée d''une capacité spéciale unique, "Tourbillon Obscur," elle peut engendrer des tourbillons d''obscurité pour attaquer ses adversaires. Zobidique est prête à plonger dans l''action, enveloppant le champ de bataille dans un voile d''énigme et de puissance.', 'Baleine', 'Eau', 'img/zobidique.png', 'img/small/zobidique.png'),
    (32,'Le Zalestinien', 'Le Zalestinien est une créature légendaire qui incarne la lutte et la détermination du peuple Zalestinien face à l''intrusion de Zisrael, qui colonise contre leur gré leurs terres dans le monde de "Zozzémon."\n\nDoté d''une capacité spéciale unique, "Résistance Féroce," il renforce sa défense et sa résistance au combat pour protéger son territoire. Le Zalestinien est prêt à affronter tous les défis avec courage, incarnant la résilience du peuple Zalestinien et sa quête pour la justice.', 'Zalestinien', 'Terre', 'img/zalestinien.png', 'img/small/zalestinien.png'),
    (33,'Le Zisraélien', 'Le Zisraélien est une créature déterminée qui incarne la volonté de Zisrael dans le conflit avec la Zalestine, dans le monde de "Zozzémon."\n\nDoté d''une capacité spéciale unique, "Détermination Inébranlable," il renforce son attaque et son endurance au combat pour protéger les intérêts de Zisrael. Le Zisraélien est prêt à affronter tous les défis avec détermination, incarnant la résolution du peuple Zisraélien dans ce conflit.', 'Zisraélien', 'Terre', 'img/zisraélien.png', 'img/small/zisraélien.png'),
    (34,'Zuth l''Emmerdeur', 'Zuth l''Emmerdeur est une créature espiègle et agaçante dans le monde de "Zozzémon."\n\nDoté d''une capacité spéciale unique, "Farceur Chaotique," il peut semer la confusion et l''agacement parmi ses adversaires en perturbant leurs actions. Zuth l''Emmerdeur est prêt à jouer des tours et à semer la discorde sur le champ de bataille, utilisant sa vitesse pour embêter ses ennemis.', 'Zuth', 'Tenèbre', 'img/zuth_emmerdeur.png', 'img/small/zuth_emmerdeur.png'),
    (36,'Zhony le Zbire du Dictateur', 'Zhony le Zbire du Dictateur est un fidèle serviteur de Zozz Dictateur, prêt à exécuter ses ordres sans hésitation.\n\nDoté d''une capacité spéciale unique, "Obéissance Inébranlable," il renforce sa vitesse au combat pour accomplir rapidement les tâches assignées par Zozz Dictateur. Zhony le Zbire est prêt à suivre les directives de son dictateur avec une loyauté inébranlable, prêt à agir en toute circonstance.', 'Zbire', 'Terre', 'img/zhony_zbire.png', 'img/small/zhony_zbire.png'),
    (37,'Zozz de Béton', 'Zozz de Béton est une créature colossale, évoluée à partir du Zozz Anti-Germanique, et il est en train de couler une dalle de béton sur l''entièreté de l''Allemagne dans le monde de "Zozzémon."\n\nDoté d''une capacité spéciale unique, "Fondation Inébranlable," il renforce sa défense et son endurance au combat, tout comme il renforce la fondation de son projet de dalle de béton. Zozz de Béton est prêt à accomplir cette tâche monumentale avec une détermination implacable, incarnant la force et la solidité du béton.', 'Zozz', 'Terre', 'img/zozz_beton.png', 'img/small/zozz_beton.png'),
    (38,'Zozz Excédé par le Zuth', 'Zozz Excédé par le Zuth est un Zozz dont la patience est mise à rude épreuve par les farces et les taquineries incessantes du Zuth.\n\nDoté d''une capacité spéciale unique, "Frustration Éclair," il peut augmenter sa vitesse pour répondre rapidement aux provocations du Zuth. Zozz Excédé est prêt à riposter aux agacements avec une réactivité fulgurante, déterminé à mettre fin aux taquineries du Zuth.', 'Zozz', 'Lumière', 'img/zozz_excede.png', 'img/small/zozz_excede.png'),
    (39,'Zozz Zététique', 'Zozz Zététique est un être curieux et observateur, toujours en quête de vérité et de connaissance dans le monde de "Zozzémon."\n\nDoté d''une capacité spéciale unique, "Analyse Objective," il peut accroître sa vitesse pour explorer et comprendre rapidement son environnement. Zozz Zététique est prêt à enquêter sur les mystères qui se présentent, mettant en avant sa quête de compréhension et son approche logique.', 'Zozz', 'Lumière', 'img/zozz_zetetique.png', 'img/small/zozz_zetetique.png'),
    (40,'Zozz le Programmeur', 'Zozz le Programmeur est un être technologique brillant, maître de la logique et du code dans le monde de "Zozzémon."\n\nDoté d''une capacité spéciale unique, "Code Infaillible," il peut augmenter sa vitesse pour résoudre rapidement les problèmes techniques qui se présentent. Zozz le Programmeur est prêt à relever tous les défis informatiques, mettant en avant sa compétence en programmation et sa capacité à réagir avec efficacité.', 'Zozz', 'Lumière', 'img/zozz_programmeur.png', 'img/small/zozz_programmeur.png'),
    (41,'Zozz Insoumis', 'Zozz Insoumis incarne l''esprit de rébellion et de résistance dans le monde de "Zozzémon," en référence à la France Insoumise de Jean-Luc Mélenchon.\n\nDoté d''une capacité spéciale unique, "Esprit de Lutte," il augmente sa vitesse pour mener des actions de protestation rapides. Zozz Insoumis est prêt à défendre les droits et les valeurs qu''il chérit, déterminé à lutter pour un monde meilleur.', 'Zozz', 'Lumière', 'img/zozz_insoumis.png', 'img/small/zozz_insoumis.png'),
    (42,'Zélanchon', 'Zélanchon incarne la force et la détermination dans le monde de "Zozzémon."\n\nDoté d''une capacité spéciale unique, "Éloquence Impassible," il renforce sa défense et sa résistance au combat pour incarner la résolution inébranlable de Mélenchon. Zélanchon est prêt à défendre ses idéaux politiques avec un calme imperturbable.', 'Zélanchon', 'Lumière', 'img/zelanchon.png', 'img/small/zelanchon.png'),
    (43,'Zozz le Chaud', 'Zozz le Chaud est une créature impulsive, prête à se lancer dans n''importe quelle aventure ou entreprise dans le monde de "Zozzémon."\n\nDoté d''une capacité spéciale unique, "Audace Inébranlable," il augmente son attaque pour relever tous les défis avec confiance. Zozz le Chaud est prêt à se lancer dans l''inconnu, sans craindre les conséquences, déterminé à suivre son instinct.', 'Zozz', 'Terre', 'img/zozz_le_chaud.png', 'img/small/zozz_le_chaud.png');

CREATE SEQUENCE card_reference_sequence START 44;
