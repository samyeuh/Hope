Rédaction du ReadMe

# HOPE: Hub des Outils et Plateformes pour les Enseignements

## Introduction
HOPE est une application web visant à remplacer un fichier Excel existant pour homogénéiser l’utilisation des outils et plateformes pédagogiques. Cette solution fournit une interface intuitive pour les enseignants, étudiants et administrateurs, tout en facilitant la gestion et le partage d’informations éducatives.

## Fonctionnalités Principales

### Gestion des utilisateurs
- **Connexion sécurisée**
    - Gestion des erreurs de connexion avec messages explicatifs.
    - Session personnalisée affichant le nom de l’utilisateur.
- **Profils utilisateurs**
    - Enseignant, Étudiant, Administrateur avec droits spécifiques.

### Gestion des outils et plateformes
- Affichage synthétique des informations.
- Consultation détaillée des données.
- Ajout, modification, et suppression des informations.
- Gestion des propositions d’ajout avec validation par un administrateur.
- Recherche multicritère (par domaine, titre, description).

### Robustesse
- Gestion des erreurs avec des messages appropriés.
- Logs pour le suivi des actions et des erreurs.
- Validation des données côté serveur et côté client.

## Déploiement
L’application est accessible via une URL publique. Vous pouvez tester toutes ses fonctionnalités en utilisant les comptes ci-dessous.

## Prérequis
- **Java**: JDK 17 ou supérieur
- **Framework**: Spring Boot 3.0
- **SGBD**: MariaDB 10.4.28
- **Gestion de version**: Git (repos sur GitHub/GitLab)
- **Outils supplémentaires**: Swagger pour la documentation de l'API REST

## Instructions pour exécuter l'application

### Installation
1. Clonez le dépôt :
   ```bash
   git clone <URL_du_repo>
   ```
2. Naviguez dans le répertoire du projet :
   ```bash
   cd HOPE_project
   ```
3. Configurez votre base de données MariaDB avec le fichier SQL fourni :
   ```bash
   mysql -u root -p < hope_schema.sql
   ```

### Lancement
1. Compilez et lancez le projet avec Maven :
   ```bash
   ./mvnw spring-boot:run
   ```
2. Accédez à l’application via [http://localhost:8080](http://localhost:8080).

### Comptes de test
- **Administrateur**:
    - Login: `admin@hope.edu`
    - Mot de passe: `admin123`
- **Enseignant**:
    - Login: `teacher@hope.edu`
    - Mot de passe: `teacher123`
- **Étudiant**:
    - Login: `student@hope.edu`
    - Mot de passe: `student123`

## Technologies Utilisées
- Backend: Java avec Spring Boot
- Frontend: Angular 15 (optionnelle selon stack choisie)
- Base de données: MariaDB
- Sécurité: Spring Security avec gestion des rôles et des permissions
- Documentation API: Swagger UI
- Suivi des erreurs: SLF4J

## Documentation API
La documentation complète de l’API REST est accessible via : [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

## Contributions
Les suggestions et rapports de bugs sont les bienvenus. Veuillez créer une *issue* sur le dépôt Git.

## Auteurs
Projet développé par **[groupe 4]**, dans le cadre du module ALTN72 - Développement Full Stack en Java.

## Licence
Ce projet est distribué sous licence MIT. Consultez le fichier `LICENSE` pour plus de détails.
