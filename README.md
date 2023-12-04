### Introduction

**Concocte : Une Application de Quiz Flexible**

Concocte est une application mobile conçue pour créer des quiz personnalisés. Son objectif est de permettre aux utilisateurs de générer des quiz en fonction de leurs propres préférences et choix. Cette application se concentre sur la flexibilité et l'adaptabilité, offrant une expérience de quiz qui peut varier selon les goûts et les intérêts de chacun.

Le principe de Concocte repose sur l'intégration d'une API de quiz en ligne. Cette API fournit un accès à une variété de questions et de thèmes, permettant aux utilisateurs de composer des quiz uniques. Lorsque l'utilisateur démarre l'application, il est d'abord invité à remplir un formulaire. Ce formulaire est essentiel à l'expérience personnalisée offerte par Concocte, car il permet de définir les paramètres du quiz, comme le thème et le nombre de questions. Ces informations sont ensuite utilisées pour générer une requête adaptée à l'API.

Une fois la réponse de l'API reçue, le quiz commence. L'application présente chaque question de manière simple, avec un compteur de score et d'avancement pour garder une trace des performances de l'utilisateur. Après avoir répondu à toutes les questions, une alerte s'affiche indiquant le score final et propose un bouton pour revenir au formulaire initial, donnant ainsi à l'utilisateur la possibilité de recommencer avec de nouveaux paramètres.

En résumé, Concocte vise à offrir une expérience de quiz modulaire, où les utilisateurs peuvent explorer différents thèmes et configurations pour une expérience variée.

### Développement Technique


**1. Architecture et Gestion des Données**
- **Classe `ListQuestions`** :
    - Gère la liste des questions du quiz, le score et l'index de la question en cours. Cette classe assure les fonctionnalités de base pour le déroulement du quiz, telles que l'ajout de questions, le passage à la question suivante et la gestion du score.
- **Classe `Question`** :
    - Chaque instance représente une question de quiz, incluant le texte, l'explication, la réponse et les choix. Cette classe est essentielle pour le stockage et la manipulation des données de chaque question.

**2. Interface Utilisateur et Fragments**
- **`FormQuizzFragment.java`** :
    - Ce fragment gère le formulaire initial où les utilisateurs définissent les paramètres du quiz. Il joue un rôle crucial dans l'initialisation du quiz, en recueillant les choix de l'utilisateur et en lançant le quiz.
- **`QuizzFragment.java`** :
    - Ce fragment prend le relais pour afficher le quiz, gérer les questions, et présenter les choix de réponse. Il intègre également les fonctionnalités pour la mise à jour du score et l'arrêt du quiz.
- **Liaison des Données avec l'Interface Utilisateur** :
    - La méthode `displayQuestion()` dans `QuizzFragment.java` est centrale pour lier les questions et les choix à l'interface utilisateur. Elle gère l'affichage des questions et la mise à jour des scores, contribuant à l'interaction de base avec l'utilisateur.

### Challenges et Limitations
### Conclusion

