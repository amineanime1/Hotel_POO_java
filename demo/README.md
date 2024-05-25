# Gestion d'Hôtel - Démo

## Description
Ce répertoire contient tout le code source de l'application de gestion d'hôtel. Il inclut également le fichier `pom.xml` pour la configuration Maven et la connexion à la base de données.

## Structure
- `src/main/java`: Contient les fichiers source Java.
- `src/main/resources`: Contient les ressources de l'application (fichiers de configuration, etc.).
- `pom.xml`: Fichier de configuration Maven.

## Installation
Pour installer et compiler le projet, exécutez:
```sh
mvn install
```
## Exécution
Pour lancer l'application:
```
sh
mvn exec:java -Dexec.mainClass="com.yourpackage.Main"
```
## Base de Données
Assurez-vous que votre base de données est configurée correctement dans le fichier pom.xml.

## Captures d'écran
Les captures d'écran de l'interface graphique se trouvent dans le dossier Screenshots.
