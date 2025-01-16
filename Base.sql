-- Création de la table Tool
CREATE TABLE IF NOT EXISTS Tool (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    Titre VARCHAR(255) NOT NULL,
                                    Domaine VARCHAR(255) NOT NULL,
                                    Lien TEXT NOT NULL,
                                    Description_simple TEXT NULL,
                                    Description_detaillee TEXT NULL,
                                    Acces TEXT NULL,
                                    Feedback_utilisateurs TEXT NULL
);

-- Création de la table User
CREATE TABLE IF NOT EXISTS User (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    username VARCHAR(255) NOT NULL,
                                    first_name VARCHAR(255) NOT NULL,
                                    last_name VARCHAR(255) NOT NULL,
                                    role VARCHAR(50) NOT NULL,
                                    password VARCHAR(255) NOT NULL
);

-- Création de la table Feedback
CREATE TABLE IF NOT EXISTS Feedback (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        user_id BIGINT NOT NULL,
                                        tool_id INT NOT NULL,
                                        commentaire TEXT NOT NULL,
                                        FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE,
                                        FOREIGN KEY (tool_id) REFERENCES Tool(id) ON DELETE CASCADE
);

-- Insertion des données dans la table Tool
INSERT INTO Tool (Titre, Domaine, Lien, Description_simple, Description_detaillee, Acces, Feedback_utilisateurs)
VALUES
    ('GitHub Global Campus (GitHub Education)', 'Bouquet de services',
     'https://education.github.com/discount_requests/student_application',
     '?', 'Sélection d\'outils et de services pour booster la productivité et les compétences des étudiants.',
     'a) Aller sur le lien indiqué\nb) Remplir les détails requis\nc) Profiter des services.', NULL),
    ('Coding Rooms', 'Codage / Développement',
     'https://www.codingrooms.com/',
     '?', 'Exercices pratiques de programmation, en ligne.',
     'L\'enseignant partage un lien d\'invitation unique.', NULL),
    ('Nowledgeable', 'Codage / Développement',
     'https://nowledgeable.com/',
     '?', 'Exercices pratiques de programmation, en ligne.',
     'a) Allez sur : https://nowledgeable.com/login\nb) Inscrivez-vous ou connectez-vous.', NULL),
    ('Jupyter notebook', 'Codage / Développement',
     'https://jupyter.org/',
     '?', 'Jupyter Notebook est une application web qui vous permet de créer et partager des documents contenant du code.',
     'OPTION A\na) Installer PyCharm Pro\nb) Ouvrir le terminal et exécuter : jupyter notebook', NULL),
    ('repl.it', 'Codage / Développement',
     'https://replit.com/',
     '?', 'Exercices pratiques de programmation (notamment Python).',
     'a) Créer un compte (option gratuite)\nb) Vous pouvez travailler sur vos projets en ligne.', NULL);

-- Insertion des données dans la table User
INSERT INTO User (username, first_name, last_name, role, password)
VALUES
    ('admin1', 'Alice', 'Admin', 'admin', 'password123'),
    ('etudiant1', 'Bob', 'Etudiant', 'étudiant', 'password123'),
    ('etudiant2', 'Claire', 'Etudiant', 'étudiant', 'password123'),
    ('enseignant1', 'David', 'Enseignant', 'enseignant', 'password123'),
    ('enseignant2', 'Eve', 'Enseignant', 'enseignant', 'password123');

-- Insertion des données dans la table Feedback
INSERT INTO Feedback (user_id, tool_id, commentaire)
VALUES
    (2, 1, 'Un outil indispensable pour les étudiants en informatique !'),
    (3, 2, 'Très utile pour des exercices de codage en temps réel.'),
    (4, 3, 'Excellent pour gérer des classes en ligne.'),
    (5, 4, 'Jupyter Notebook est parfait pour les projets en Python.'),
    (2, 5, 'Repl.it facilite le codage directement depuis un navigateur.');
