# Kanban Board Simulator 2.0
[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com) [![forthebadge](https://forthebadge.com/images/badges/built-with-swag.svg)](https://forthebadge.com) [![forthebadge](https://forthebadge.com/images/badges/makes-people-smile.svg)](https://forthebadge.com)

#### Backend d'un projet de simulation du fonctionnement d'un Kanban board.

### Pré-requis
- Version du jdk : Ce projet a été testé avec la version **17 du JDK (Java JDK 17)**. Certains réglages mineurs pourraient s'avérés nécessaires pour la compatibilité avec des versions antérieures du JDK.


- La **base de données** utilisée est **PostgreSQL** dans sa **version 14**.
    - les identifiants de connexion à la base sont les suivants:
        - Database : **tp2sir**
        - Username : **demo**
        - Password : **demo**


- Dans le projet, nous avons génnérer les getteurs et les setteurs avec **lombok**
    - Pour l'intégration de **lombok dans Eclipse ou Intellij** veuillez vous référer au lien suivant : **[Setting up Lombok with Eclipse and Intellij](https://www.baeldung.com/lombok-ide)**

### Installation
Pour peupler la base de données avec des données de test, rien de plus simple :)

- Remplir la base de donnée avec des données de test :

  Un fichier : **_JpaTest_, permettant de créer et d'ajouter quelques données à la base,** a été fourni dans le package **_db_** du projet.

## Démarrage

- Pour lancer le serveur :
  Exécuter le fichier **RestServer** se trouvant à la racine du package _**java**_ du projet.

## Versions
**Dernière version stable :** Sur la branche **Master**

##Test
* Le projet peut être testé, en local, via **Swagger** (Cliquer **[ICI](http://localhost:8080/api#/)** après avoir lancé le server en local).

###Postman :
    Ajout de plusieurs utilisateurs: (URL) http://localhost:8080/user/many
        
        JSON Format: 

        {
            "userList": [
                {
                    "firstName": "TOSSOU",
                    "lastName": "Cyriaue"
                },
                {
                    "firstName": "DOSSOU-KOKO",
                    "lastName": "Eulodie"
                },
                {
                    "firstName": "DANTON",
                    "lastName": "Judes"
                },
                {
                    "firstName": "MONSOU",
                    "lastName": "Rosine"
                }
            ]
        }

        Ajout de plusieurs Tableau: (URL) http://localhost:8080/board/many
        JSON Format: 

        {
            "boardList": [
                {
                    "title": "string"
                },
                {
                    "title": "string"
                }
            ]
        }

        ... (Veuillez vous référer au swagger pour avoir le format Json des différent endpoints MERCI. :)


    
## Auteurs
Membres du binôme:
* **PADONOU Dieu-Donné**
* **DJEDJEMEL Arnauld**

## License

[![MIT license](https://img.shields.io/badge/License-MIT-blue.svg)](https://lbesson.mit-license.org/) 
