# End Portal Skin (Forge 1.20.1)

Mod **100 % client** : tout ton skin prend l'apparence du portail de l'End (le champ d'étoiles animé,
avec le même shader que le vrai bloc). Corps, bras, jambes, tête et couches extérieures du skin.
L'armure, la cape et les objets tenus restent visibles par-dessus.

- **Touche K** : activer / désactiver l'effet (modifiable dans Options > Contrôles > End Portal Skin).
- Seul **toi** vois l'effet : les autres joueurs voient ton skin normal. Rien à installer sur le serveur.
- Fonctionne en vue 3e personne, en vue 1re personne (bras) et dans l'aperçu de l'inventaire.
- Avec certains packs de shaders (Iris / Oculus), le rendu du portail peut différer.

## Obtenir le fichier .jar

### Option A : sur ton PC (Java 17 ou plus récent)

```
./gradlew build          # Linux / macOS
gradlew.bat build        # Windows
```

Le mod est généré dans `build/libs/endportalskin-1.0.0.jar`.
La première compilation télécharge Forge et Minecraft (quelques minutes).

### Option B : sans rien installer, avec GitHub

1. Crée un dépôt GitHub et envoie-y tout le contenu de ce dossier.
2. Onglet **Actions** : la compilation se lance toute seule (ou bouton « Run workflow »).
3. Quand c'est vert, télécharge l'artefact `endportalskin-jar` en bas de la page du run.

## Installer

Copie le `.jar` dans `.minecraft/mods`, avec **Forge 1.20.1** (47.x) installé, puis lance le jeu.
