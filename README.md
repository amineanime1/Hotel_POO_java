# Gestion d'Hôtel en Java

## Description
Ce projet est une application de bureau de gestion d’hôtel développée en Java. L'application permet à des administrateurs et des clients de gérer les chambres et les réservations.

## Fonctionnalités
- **Administrateur**:
  - Gérer les chambres (créer, modifier, supprimer)
  - Gérer les réservations (accepter, décliner)
- **Client**:
  - Demander une réservation de chambre
  - Modifier sa réservation
  - Annuler sa réservation

## Authentification
Tous les utilisateurs doivent s’authentifier avant d’utiliser l’application.

## Gestion des Exceptions
- Un administrateur ne peut pas modifier ou supprimer une chambre déjà réservée.
- Un client ne peut annuler sa réservation qu'avant la date de début de cette réservation.

## Architecture
- **Modèle-Vue-Contrôleur (MVC)**
- **Stockage des données**: Collections de type Map
- **Interface graphique**: Accessible via des interfaces graphiques

## Structure du projet
- **demo**: Contient tout le code source, y compris le fichier `pom.xml` pour la connexion à la base de données en utilisant Maven.
- **screenshots**: Contient des captures d'écran de l'interface graphique.
- **hotelManagement**: Contient un fichier `sqlproj` pour la gestion de la base de données.
- **Model**: Contient les modèles de données.
- **View**: Contient les vues de l'application.
- **Controller**: Contient les contrôleurs de l'application.
- **Main**: Contient le point d'entrée de l'application et des images d'icônes.

## Prérequis
- Java 11+
- Maven
- Une base de données relationnelle (MySQL, PostgreSQL, etc.)

## Installation
Clonez le dépôt et utilisez Maven pour gérer les dépendances et compiler le projet:
```sh
git clone https://github.com/votre-utilisateur/gestion-hotel.git
cd gestion-hotel/demo
mvn install
```
## Utilisation
Lancez l'application à partir du fichier Main.java dans le dossier main.

## Contributeurs
- **IZEM Mohamed Amine** - amne.zem@gmail.com
- **HAMAMTI Yacine** - hamamtiyacine1@gmail.com
- **IMANSOURA Ramy** - imansoura.ramy@gmail.com


**Bonne Journée ! :)**
