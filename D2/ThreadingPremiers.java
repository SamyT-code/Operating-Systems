/*------------------------------------------------------------
Fichier: ThreadingPremiers.java

Noms: Tisham Islam, Samy Touabi
Numeros d'etudiant: 300189261, 300184721
Date: Primtemps/été 2022

Description: Ce programme contient le code pour afficher tous
             nombres premiers inférieurs ou égaux au nombre entré 
             par l'utilisateur ainsi que le comportement du fil
             enfant.
-------------------------------------------------------------*/

public class ThreadingPremiers extends Thread {

    private int max; // Variable privée du nombre maximum

    public ThreadingPremiers(int max){ // Constructeur pour Threading qui prend le nombre maximum
        this.max = max;
    }

    @Override
    public void run(){
        trouverPremiers(max); // Appeler la fonction trouverPremiers avec max
    }

    public void trouverPremiers(int max){ // Cette fonction imprime tous les nombres premiers de 2 à max
        for (int n = 2; n <= max; n++){
            boolean estPremier = true; // On commence par présumer que n est premier

            for (int facteur = 2; facteur <= Math.sqrt(n); facteur++){ // Cette boucle vérifie si n est premier
                if (n % facteur == 0) {
                    estPremier = false; // Si n a un facteur autre que un et lui même, alors n n'est pas premier
                    break; // Inutile de continuer la boucle, on sait que n n'est pas premier
                }
            }

            if (estPremier) { // Si n est premier, l'imprimer
                System.out.println(n);
            }
        }
    }

}