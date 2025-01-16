-- Création de la table tool
CREATE TABLE IF NOT EXISTS tool (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    Titre VARCHAR(255) NOT NULL,
                                    Domaine VARCHAR(255) NOT NULL,
                                    Lien TEXT NOT NULL,
                                    Description_simple TEXT NULL,
                                    Description_detaillee LONGTEXT NULL,
                                    Acces LONGTEXT NULL,
                                    VISIBLE BOOLEAN DEFAULT TRUE
);

-- Création de la table User
CREATE TABLE IF NOT EXISTS user (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    username VARCHAR(255) COLLATE utf8mb4_bin NOT NULL,
                                    first_name VARCHAR(255) NOT NULL,
                                    last_name VARCHAR(255) NOT NULL,
                                    role VARCHAR(50) NOT NULL,
                                    password VARCHAR(255) NOT NULL
);

-- Création de la table Feedback
CREATE TABLE IF NOT EXISTS feedback (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        user_id BIGINT NOT NULL,
                                        tool_id INT NOT NULL,
                                        commentaire TEXT NOT NULL,
                                        date DATE DEFAULT CURRENT_DATE,
                                        FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                                        FOREIGN KEY (tool_id) REFERENCES tool(id) ON DELETE CASCADE
);

-- Insertion des données dans la table tool
INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces)
VALUES
    ('GitHub Global Campus (GitHub Education)', 'Bouquet de services',
     'https://education.github.com/discount_requests/student_application',
     NULL, 'Sélection d\'outils et de services pour booster la productivité et les compétences des étudiants.',
     'a) Aller sur le lien indiqué\nb) Remplir les détails requis\nc) Profiter des services.'),
    ('Coding Rooms', 'Codage / Développement',
     'https://www.codingrooms.com/',
     NULL, 'Exercices pratiques de programmation, en ligne.',
     'L\'enseignant partage un lien d\'invitation unique.'),
    ('Nowledgeable', 'Codage / Développement',
     'https://nowledgeable.com/',
     NULL, 'Exercices pratiques de programmation, en ligne.',
     'a) Allez sur : https://nowledgeable.com/login\nb) Inscrivez-vous ou connectez-vous.'),
    ('Jupyter notebook', 'Codage / Développement',
     'https://jupyter.org/',
     NULL, 'Jupyter Notebook est une application web qui vous permet de créer et partager des documents contenant du code.',
     'OPTION A\na) Installer PyCharm Pro\nb) Ouvrir le terminal et exécuter : jupyter notebook'),
    ('repl.it', 'Codage / Développement',
     'https://replit.com/',
     NULL, 'Exercices pratiques de programmation (notamment Python).',
     'a) Créer un compte (option gratuite)\nb) Vous pouvez travailler sur vos projets en ligne.');

INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces) VALUES ('Google Colaboratory (Colab)', 'Codage / Développement', 'https://colab.research.google.com/?hl=fr', NULL, 'Colab est une plateforme très pratique pour exécuter du code Python sans avoir besoin d''installer Python localement. Elle est souvent utilisée pour les démonstrations, les TP et les projets en Machine Learning car elle donne accès à des ressources de calcul gratuites (CPU, GPU, TPU).', 'a) Se connecter à Colab avec son compte Google
b) Créer un nouveau notebook
c) Commencer à coder en Python directement dans le navigateur.');
INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces) VALUES ('Environnements virtuels', 'Hyperviseurs', '???', NULL, 'Machines virtuelles', 'OPTION I : Utilisation d''un hyperviseur
a) Installer VirtualBox ou VMWare
b) Télécharger un fichier image *.iso
c) L''ouvrir dans l''hyperviseur

OPTION II : Utilisation de Vagrant
a) Installer Vagrant (gratuit)
b) Créer sa VM en passant par une box de Vagrant');
INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces) VALUES ('Environnements virtuels', 'Containers', 'https://www.docker.com/', NULL, 'Containers Docker', 'a) Installer Docker
b) Aller sur le Docker Hub pour récupérer une ou des image(s) Docker correspondant au(x) besoin(s)
c) Créer un ou des container(s) à partir de cette/ces image(s)');
INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces) VALUES ('TryHackme', 'Cyber sécurité', 'https://tryhackme.com/', NULL, 'TryHackMe est une excellente ressource pour se former à la cybersécurité de manière pratique. Les exercices, souvent présentés sous forme de scénarios réalistes, permettent d''apprendre en s''amusant. Des challenges concrets permettent de pratiquer de façon ludique.  #cyber #hacking', 'a) Aller sur le site https://tryhackme.com/
b) Cliquer sur connexion
c) Entrer vos identifiants
d) Commencer à travailler

Remarque : Il y a 3 plans correspondant à 3 tarifs différents. Le plan gratuit devrait convenir pour la plupart des cours.');
INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces) VALUES ('Hack The Box', 'Cyber sécurité', 'https://www.hackthebox.com/', NULL, 'Hack The Box est une plateforme de cybersécurité réputée pour ses challenges de type "capture de drapeaux" (CTF). Elle propose des machines virtuelles à attaquer légalement pour s''entraîner au hacking éthique.', 'Créer un compte standard sur le site via le lien indiqué');
INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces) VALUES ('AWS', 'Cloud provider', 'https://signin.aws.amazon.com/signup?request_type=register', NULL, 'Fournisseur Cloud', NULL);
INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces) VALUES ('AWS Academy', 'Formations en ligne', 'https://aws.amazon.com/fr/training/awsacademy/', NULL, 'Plateforme d''AWS de formations en ligne', NULL);
INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces) VALUES ('Azure', 'Cloud provider', 'https://azure.microsoft.com/en-us', NULL, 'Fournisseur Cloud', NULL);
INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces) VALUES ('Integral Calculator', 'Mathématiques', 'https://www.integral-calculator.com/', NULL, 'Calcul d''intégrales en ligne', NULL);
INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces) VALUES ('eMathHelp', 'Mathématiques', 'https://www.emathhelp.net/en/', NULL, 'Résolution de problèmes mathématiques, étape par étape', NULL);
INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces) VALUES ('MultisimLive', 'Electronique', 'https://www.multisim.com/', NULL, 'Simulation de programmation de cartes electroniques', NULL);
INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces) VALUES ('MATLAB & Simulink (MathWorks)', 'Mathématiques', 'https://fr.mathworks.com/products/matlab/student.html', NULL, 'Calcul numérique / Analyse de données', 'Un accompagnement clair et détaillé est proposé dans les cours utilsant l''outil');
INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces) VALUES ('Patchwork3D & AccelVR (Lumiscaphe)', 'Réalité virtuelle / augmentée', 'https://resources.lumiscaphe.com/Software_Suite/2023/en/accel-vr.html <br>https://resources.lumiscaphe.com/Software_Suite/2023/en/patchwork-3d.html', NULL, 'Modélisation 3D / Rendu RV', 'S''informer auprès de l''enseignant');
INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces) VALUES ('Kaggle', 'Data Science', 'https://www.kaggle.com/', NULL, 'Kaggle est une plateforme web qui fournit des outils et des ressources puissants pour aider à progresser en Data Science. Vous trouverez plus de 50 000 jeux de données publics et 400 000 notebooks publics disponibles pour tous.', 'S''informer auprès de l''enseignant');
INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces) VALUES ('Notion', 'Gestion de projets et collaboration', 'https://www.notion.so/fr-fr', NULL, 'Notion est une application/plateforme de prise de notes, de gestion de projet et de collaboration. Notion est conçu pour permettre aux utilisateurs d''organiser leurs informations de manière flexible, en utilisant une variété de formats tels que des notes, des bases de données relationnelles, des listes de tâches, des calendriers et des tableaux, le tout dans un seul espace de travail intégré.', 'S''informer auprès de l''enseignant');
INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces) VALUES ('Trello', 'Gestion de projets et collaboration', 'https://trello.com/home', NULL, 'Trello est une plateforme de gestion de projet très visuelle et intuitive. Elle est idéale pour les travaux de groupe, car elle permet de suivre facilement l''avancement des tâches et de communiquer efficacement entre les membres de l''équipe.', 'a) Créer un compte (version gratuite)
b) Créer un espace puis un premier tableau
c) Commencer à utiliser la plateforme');
INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces) VALUES ('Code Ocean', 'Data Science', 'https://codeocean.com/', NULL, 'Code Ocean une plateforme centralisée pour la création, le partage, la publication, la préservation et la réutilisation de code et de données exécutables. Avec Code Ocean, les chercheurs peuvent facilement analyser, organiser et exécuter des travaux de recherche et les publier dans des dépôts et des revues.', 'S''informer auprès de l''enseignant');
INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces) VALUES ('Marp', 'Génération de documents', 'https://marp.app/', NULL, 'Création de slides à prtir de documents Markdown', 'La documentation sur le site officiel est très détaillée et suffisamment précise pour permettre une prise en main autonome');
INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces) VALUES ('LaTeX', 'Génération de documents', 'https://www.latex-project.org/', NULL, 'LaTeX est un langage et un système de composition de documents', 'La documentation sur le site officiel est très détaillée et suffisamment précise pour permettre une prise en main autonome');
INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces) VALUES ('Material for mkdocs', 'Génération de documents', 'https://squidfunk.github.io/mkdocs-material/', NULL, 'Création de documentation (de code)', 'La documentation sur le site officiel est très détaillée et suffisamment précise pour permettre une prise en main autonome');
INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces) VALUES ('Looping', 'Bases de données', 'https://www.looping-mcd.fr/', NULL, 'Modélisation conceptuelle de données', 'Télécharger et installer');
INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces) VALUES ('MongoDB Compass', 'Bases de données', 'https://www.mongodb.com/products/tools/compass', NULL, 'GUI pour MongoDB', 'Télécharger et installer');
INSERT INTO tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces) VALUES ('Wireshark', 'Réseaux', 'https://www.wireshark.org/', NULL, 'Wireshark est un analyseur de paquets libre et gratuit. Il est notamment utilisé dans le dépannage et l’analyse de réseaux informatiques et le développement de protocoles.', 'Télécharger et installer');

-- Insertion des données dans la table User
INSERT INTO user (username, first_name, last_name, role, password)
VALUES
    ('admin1fake', 'Alice', 'Admin', 'admin', 'password123'),
    ('etudiant1fake', 'Bob', 'Etudiant', 'étudiant', 'password123'),
    ('etudiant2fake', 'Claire', 'Etudiant', 'étudiant', 'password123'),
    ('enseignant1fake', 'David', 'Enseignant', 'enseignant', 'password123'),
    ('enseignant2fake', 'Eve', 'Enseignant', 'enseignant', 'password123'),
    ('admin1', 'Alice', 'Admin', 'admin', '$2a$10$zy1c9V58g0FLR3Ifd3OLIeq/DR4QGF28xLvbLeGxT0KiP5E2T2Bua'),
    ('etudiant1', 'Bob', 'Etudiant', 'etudiant', '$2a$10$FLJSkmEhQhseg6/MMtkFJuf7Nh230N0ip5eIIewZ01PKA5plqx35e'),
    ('etudiant2', 'Claire', 'Etudiant', 'etudiant', '$2a$10$jp5JhCdMTTR1jt9riQ/tyO./Q0almYVYlFaAE/zRcfHds8tKmAQQS'),
    ('enseignant1', 'David', 'Enseignant', 'enseignant', '$2a$10$K1GCijlOCMbpOtt76cJj2.h62X3/esMDlmSDjqk56Iro0F9at4Tcy'),
    ('enseignant2', 'Eve', 'Enseignant', 'enseignant', '$2a$10$ac67WIDnG3KPCG/vxMD8Xe3KytrRcWcrZR0bjl0qjuPDKAElmLMvm');

-- Insertion des données dans la table Feedback
INSERT INTO feedback (user_id, tool_id, commentaire)
VALUES
    (2, 1, 'Un outil indispensable pour les étudiants en informatique !'),
    (3, 2, 'Très utile pour des exercices de codage en temps réel.'),
    (4, 3, 'Excellent pour gérer des classes en ligne.'),
    (5, 4, 'Jupyter Notebook est parfait pour les projets en Python.'),
    (2, 2, 'Samy le goat !'),
    (2, 5, 'Repl.it facilite le codage directement depuis un navigateur.');
