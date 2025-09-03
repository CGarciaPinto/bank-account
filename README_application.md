# Bank Account API

Une application d'exemple construite avec **Java 17**, **Spring Boot**, **PostgreSQL**, et containerisée avec **Docker**.  
Elle inclut un pipeline de **CI/CD GitLab** et une documentation API avec **Swagger**.

---

## Technologies utilisées
- Java 17  
- Spring Boot 3.5.5
- Maven 3.8.2
- PostgreSQL 17  
- Docker & Docker Compose  
- GitLab CI/CD   

---

## Prérequis
- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)  
- [Maven](https://maven.apache.org/)  
- [Docker](https://www.docker.com/products/docker-desktop/)    

---

## Exécution en local (IntelliJ ou terminal)
1. Cloner le dépôt :  
   ```bash
    git clone https://gitlab.com/exalt-it-dojo/candidats/carlos-garcia-pinto-bank-account-v2-cae83323-9f5e-4427-9646-735603143cb6.git
    cd carlos-garcia-pinto-bank-account-v2-cae83323-9f5e-4427-9646-735603143cb6
2. Construire et exécuter avec Maven :
    ```bash
    mvn spring-boot:run
3. L’application sera disponible sur :
    ```bash
    API : http://localhost:8080/api

## Exécution avec Docker
1. Construire les images :
    ```bash
    docker-compose build
2. Lancer les conteneurs :
    ```bash
    docker-compose up
    -- L'application sera disponible sur http://localhost:8080
3. Accès à l’application
    ```bash
    API : http://localhost:8080/api
4. Accès à la base de données :
    ```bash
    Hôte : localhost
    Port : 5432
    DB : bankdb
    Utilisateur : bankuser
    Mot de passe : admin
5. Arrêter les conteneurs (sans supprimer les données) :
    ```bash
    docker-compose down
6. Voir les logs en temps réel :
    ```bash
    docker-compose logs -f

## Configuration

**Profils Spring :**
- `dev` : pour l’exécution locale
- `docker` : pour l’exécution dans les conteneurs

Les propriétés de connexion à la base de données sont définies via les variables d’environnement dans `docker-compose.yml` et `.gitlab-ci.yml`.



