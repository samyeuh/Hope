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

- **Affichage synthétique des informations.**
- **Consultation détaillée des données.**
- **Ajout, modification, et suppression des informations.**
- **Gestion des propositions d’ajout avec validation par un administrateur.**
- **Recherche multicritère** (par domaine, titre, description).

### Robustesse

- **Gestion des erreurs avec des messages appropriés.**
- **Logs pour le suivi des actions et des erreurs.**
- **Validation des données côté serveur et côté client.**

## Réponses aux questions

### Degré de conformité aux principes de l'approche Clean Code

#### Volet : Nommage

- **Noms des classes** : Des noms comme `DataHope`, `User`, `HomeService` et `LoginController` reflètent clairement leur rôle.
- **Noms des méthodes** : Des noms explicites comme `findUserByUsername`, `getPreviewsData`, et `updateData`.
- **Noms des variables** : Des noms concis et évocateurs tels que `dataObj` pour les objets de type `DataHope` ou `query` pour les requêtes de recherche.

**Amélioration possible** : Passer les noms des colonnes de la base de données en anglais pour maintenir une cohérence globale.

#### Volet : Gestion des erreurs

- **Validation des entrées** : Par exemple, dans `LoginService`, les entrées utilisateur sont validées pour éviter les erreurs courantes.
- **Gestion des exceptions** : Dans `UDService`, une exception `UsernameNotFoundException` est levée avec un message clair si un utilisateur n'est pas trouvé.
- **Gestion des données inexistantes** : Utilisation d'objets `Optional` pour éviter les retours `null`

### Principes SOLID

1. **Single Responsibility Principle (SRP)**
   - Chaque classe a une seule responsabilité bien définie (ex. `LoginService` pour l'authentification, `HomeService` pour la gestion des données de la page d'accueil).

2. **Open/Closed Principle (OCP)**
   - Favorise l’extensibilité sans modification du code existant (ex. utilisation de `JpaRepository`). La classe est étendue sans jamais être réécrite.

3. **Liskov Substitution Principle (LSP)**
   - Les services implémentant `JpaRepository` respectent les contrats des interfaces. On peut donc remplacer la classe mêre par une classe fille.

4. **Interface Segregation Principle (ISP)**
   - Interfaces spécifiques et bien séparées (ex. `UserRepository` et `DataRepository`).

**Amélioration possible** : Modulariser la configuration de sécurité dans `SecurityConfig`.

## Déploiement

L’application est accessible via une URL publique. Vous pouvez tester toutes ses fonctionnalités en utilisant les comptes ci-dessous.


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
   mysql -u root -p <hope_schema.sql>
   ```

### Lancement
1. Compilez et lancez le projet avec Maven :
   ```bash
   ./mvnw spring-boot:run
   ```
2. Accédez à l’application via [http://localhost:8080](http://localhost:8080).

### Comptes de test
- **Administrateur**:
    - Login: `admin1fake`
    - Mot de passe: `password123`
- **Enseignant**:
    - Login: `enseignant1fake`
    - Mot de passe: `password123`
- **Étudiant**:
    - Login: `etudiant1fake`
    - Mot de passe: `password123`
      
INSERT INTO user (username, first_name, last_name, role, password)
VALUES
    ('admin1fake', 'Alice', 'Admin', 'admin', 'password123'),
    ('etudiant1fake', 'Bob', 'Etudiant', 'étudiant', 'password123'),
    ('etudiant2fake', 'Claire', 'Etudiant', 'étudiant', 'password123'),
    ('enseignant1fake', 'David', 'Enseignant', 'enseignant', 'password123'),
    ('enseignant2fake', 'Eve', 'Enseignant', 'enseignant', 'password123'),



## Technologies Utilisées
- **Java** : JDK 17 ou supérieur
- **Framework** : Spring Boot 3.0
- **Frontend**: Thymeleaf(aspect dynamique) + HTML(structure page) + CSS(style)
- **Sécurité**: Thymeleaf + Spring Security avec gestion des rôles et des permissions
- **Documentation API**: Swagger UI
- **Suivi des erreurs**: SLF4J
- **SGBD** : MariaDB 11.6.2
- **Gestion de version** : Git (repos sur GitHub/GitLab)
- **Outils supplémentaires** : Swagger pour la documentation de l'API REST
- **IDE** : IntelliJ IDEA 2023.2.1

## Documentation API
La documentation complète de l’API REST est accessible via : [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

## Contributions
Les suggestions et rapports de bugs sont les bienvenus. Veuillez créer une *issue* sur le dépôt Git.

## Auteurs
Projet développé par **[groupe 4]**, dans le cadre du module ALTN72 - Développement Full Stack en Java.

## Licence
Ce projet est distribué sous licence MIT. Consultez le fichier `LICENSE` pour plus de détails.



## Conformité avec l’approche Clean Code
Le projet HOPE a été développé en suivant les principes de Clean Code, en mettant particulièrement l’accent sur le **nommage** et la **gestion des erreurs**. Voici des exemples concrets illustrant cette conformité :

### Nommage
- **Noms des variables** :
  Les noms sont explicites et décrivent précisément leur rôle.
  ```java
  String userFirstName; // Représente le prénom de l'utilisateur.
  int toolUsageCount;   // Nombre de fois qu’un outil a été utilisé.
  ```
- **Noms des classes** :
  Les classes utilisent un format PascalCase et décrivent leur responsabilité métier.
  ```java
  public class UserRepository {
      
  }
  ```
- **Noms des méthodes** :
  Les noms suivent le pattern verbe-action pour décrire leur fonctionnalité.
  ```java
   public String processLogin(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
      // Operation effectué suite au click du bouton se connecter.
  }
  ```

### Gestion des erreurs
- **Exceptions personnalisées** :
  Des exceptions spécifiques sont utilisées pour décrire les erreurs avec précision.
  ```java
  public class ResourceNotFoundException extends ResponseStatusException {
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(HttpStatus.NOT_FOUND, String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue));
    }
  }
  ```
- **Gestion centralisée des exceptions** :
  Une gestion uniforme des erreurs est mise en place avec un contrôleur dédié.
  ```java
  @ControllerAdvice
  public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getReason());
    }
  }
  ```
Ces choix permettent de garantir un code lisible, maintenable et résilient, respectant ainsi les principes fondamentaux du Clean Code.



## Conformité aux principes SOLID
Le projet HOPE a été conçu en appliquant les principes SOLID lorsque cela était pertinent dans le contexte de notre stack technique basée sur Java et Spring Boot. Voici une analyse détaillée de ces principes :

### 1. Single Responsibility Principle (SRP)
- Chaque classe remplit une seule responsabilité clairement définie.
  Exemple :
  ```java
  public class HomeService {
      // Gestion des opérations sur la page d'accueil, tel que la recherche d'information pour retrouver un élément.
  }

  public class HomeController {
      // Gestion des endpoints liés à la page d'accueille.
  }
  ```
  **Pertinence** : Respecté pour garantir la maintenabilité et limiter le couplage entre les modules.

### 2. Open/Closed Principle (OCP)
- Les classes sont conçues pour être extensibles sans nécessiter leur modification.
  Exemple :


### 3. Liskov Substitution Principle (LSP)
- Les classes dérivées peuvent remplacer les classes parent sans altérer le comportement attendu.
  Exemple :

  **Pertinence** : Respecté en assurant l’interchangeabilité des implémentations.

### 4. Interface Segregation Principle (ISP)
- Les interfaces sont spécifiques à leurs clients.
  Exemple :
  ```java
  public interface UserAuthentication {
      boolean login(String username, String password);
  }

  public interface UserAuthorization {
      boolean hasPermission(String action);
  }
  ```
  **Pertinence** : Respecté pour découper les interfaces liées aux responsabilités spécifiques.

### 5. Dependency Inversion Principle (DIP)
- Les modules de haut niveau ne dépendent pas des modules de bas niveau, mais d’abstractions.
  Exemple :
  ```java
  @Controller
  public class SignUpController {

    private final SignUpService signUpService;
    private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);


    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }
  ```
  **Pertinence** : Respecté en injectant les dépendances via le constructeur (Spring gère ces injections via @Autowired).
