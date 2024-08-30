# Kumojin Test Technique

Ce repository contient mon petit projet pour le test technique pour Kumojin.
Il a été réalisé avec les technologies suivantes :
  * Backend ([kumojin-be](https://github.com/NicolasOuldB/Kumojin-TestTech/tree/main/kumojin-be)) :
    - Java 17
    - SpringBoot
    - Base de données en mémoire (H2)
    - Maven
  * Frontend ([kumojin-fe](https://github.com/NicolasOuldB/Kumojin-TestTech/tree/main/kumojin-fe)) :
    - ReactJS
    - Typescript
    - Vite
    - Tailwind
    - NPM
  
## Que fait le projet ?

Le but du projet est d'avoir une application pour gérer des événements. Ces événements peuvent être créés et affichés dans une application web (sous forme de liste ou bien un événement en détails).
Pour ça, une architecture REST a été mise en place, la documentation est accessible via SwaggerUI. Lorsque l'utilisateur effectue une action dans l'application web, une requête HTTP est envoyée au backend et ce dernier effectue la requête associée en base de données.

## Comment faire fonctionner le projet ?

Il s'exécute entièrement en local. Après avoir cloné le repo, il faut dans un premier temps démarrer le backend (une fois les dépendances Maven téléchargées).
Pour démarrer le backend il suffit d'importer le projet dans IntelliJ et d'exécuter le main du projet qui est dans `KumojinBeApplication.java`. Une fois le projet démarré, on peut lancer le frontend.
Pour le frontend, il faut installer toutes les dépendances NPM (avec `npm install` à la racine du projet) et ensuite de lancer la commande `npm run dev`.
Une fois l'application démarrée, il faut ouvrir un navigateur et accéder à l'url (par défaut: [http://localhost:5173/](http://localhost:5173/)) et l'utilisateur devrait être automatiquement redirigé vers la page `/events`.
Il n'y a pas de données de base, il faut donc commencer par en ajouter via l'application web.

### En cas de problèmes CORS :
Dans `EventController.java` j'ai ajouté l'annotation : `@CrossOrigin(origins = "http://localhost:5173")`, l'url (et surtout le numéro de port) doit être celle sur laquelle l'application frontend est démarrée.

### Liens utiles :
La base de données est accessible à l'url [http://localhost:8080/h2-console](http://localhost:8080/h2-console) avec l'identifiant `admin` et mot de passe `admin`.
SwaggerUI est accessible à l'url [http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui)

## Difficultés rencontrées

Comme expliqué lors de notre entretient, durant l'ensemble de ma carrière j'ai quasiment toujours été amené à intervenir sur des applications déjà existantes.
Le plus dur dans ce projet a donc été toute la partie setup du projet, j'ai donc passé plus de temps à faire des recherches mais grâce à ça j'ai :
 * Appris à mettre en place Swagger
 * Appris à mettre en place RTK Query (Redux Toolkit)
 * Revue le setup de SpringBoot
 * Revue le setup des mocks avec SpringBoot
J'ai aussi passé pas mal de temps sur le setup de Jest pour mes tests unitaires frontend mais malheureusement je n'y suis pas parvenu (j'ai bloqué sur la partie setup mock redux ce qui m'empêchait de faire le rendu de mon component dans les tests).

## Ce qui peut-être amélioré

Évidemment, le frontend et le backend manquent cruellement de tests unitaires.
Dans l'état actuel, le `getEvents` (backend) renvoit la liste de tous les événements avec toutes leurs infos. Il serait intéressant de mettre en place une pagination et aussi de renvoyer une version 'allégée' des événements (pour que le `getEventDetails` ait plus de sens).
Les composants côté React ne sont pas suffisamment découpés, le modal pour afficher le détails des événements est trop gros, la page événement aussi. Il faudrait sortir le tableau dans un composants à part et le rendre générique afin de pouvoir le réutiliser à plusieurs endroits si nécessaire.
La partie Design (CSS) est extrêmement basique. L'utilisation de MaterialUI permet d'avoir des composants responsives mais ça mériterait plus d'attention pour avoir un rendu parfait sur tous les supports.
Il n'y a pas d'internationalisation actuellement. L'application est unilingue, il faudrait mettre en place un outils comme [i18n-react](https://react.i18next.com/) que j'ai déjà utilisé par le passé.
Il n'y a pas de gestion des erreurs (ni backend ni frontend). L'ajout d'alerte dans l'application web serait un plus pour avertir l'utilisateur lors de problèmes.
La validation des champs est basique (limite de taille du `name` d'un événement, date de début < à date de fin) et devrait être améliorée.

## Conclusion

Il s'agit plus d'une coquille que d'une application complète, mais le backend a les bases pour créer une bonne API Rest et pour le frontend, même si ils ne sont pas tous pleinement, tous les outils de bases sont présents et configurés pour aller plus loin (`react-router` avec ses routes, Redux-Toolkit et RTK Query pour la gestion du state + appels HTTP, Tailwind et MaterialUI pour la partie CSS/responsive).

Merci d'avoir pris le temps de lire ce readme et d'avoir regarder le projet. J'ai hâte de pouvoir en discuter de vive voix.
Nicolas
