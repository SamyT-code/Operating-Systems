/*------------------------------------------------------------
Fichier: Premiers.java

Noms: Tisham Islam, Samy Touabi
Numeros d'etudiant: 300189261, 300184721
Date: Primtemps/été 2022

Description: Ce programme contient le code pour la création d'un
             fil séparé qui affichera tous les nombres premiers
             inférieurs ou égaux au nombre entré par l'utilisateur
             dans la ligne de commade.
-------------------------------------------------------------*/

public class Premiers{

    public static void main(String[] args) {
        
        try {

            int max = Integer.parseInt(args[0]); // Prendre le premier argument de la cmd et le convertir à int
            ThreadingPremiers t = new ThreadingPremiers(max); // Créer le fil

            System.out.println("Voici les nombres premiers inferieurs ou egaux a " + max + ":");
            t.start(); // Lancer le fil

        } catch (Exception e) { // Exception au cas ou l'argument de cmd est invalide
            System.out.println("Argument de ligne de commande invalide");
        }
        
    }
    
}