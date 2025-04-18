/*------------------------------------------------------------
Fichier: Fibonacci.java

Noms: Tisham Islam, Samy Touabi
Numeros d'etudiant: 300189261, 300184721
Date: Primtemps/été 2022

Description: Ce programme contient le code pour la création d'un
             fil séparé qui entrera dans un tableau les max premiers
             nombres Fibonacci (pas plus que F93) et attendra que
             le fil enfant se termine avant d'imprimer les données
             du tableau.
-------------------------------------------------------------*/

public class Fibonacci {

    public static void main(String[] args) {
        
        int num = Integer.parseInt(args[0]);
        boolean tooBig = false;

        if(num > 93){ // Imprimer les 93 premiers nombres seulement, car ceux après sont trop grands
            num = 93;
            tooBig = true;
        }

        long[] fib = new long[num]; // Tableau de type long
        fib[0] = 0; // Initialiser les données initiales F0 et F1
        fib[1] = 1;

        ThreadingFibonacci t = new ThreadingFibonacci(num, fib); // Créer le

        System.out.println("Lancement du fil enfant pour trouver les " + num + " premiers nombres Fibonacci..." );
        t.start(); // Lancer le fil

        try {
            t.join(); // Attendre que le fil enfant se termine
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Terminaison du fil enfant...");
        System.out.println("Voici les " + num + " premiers nombres de la sequence Fibonacci: ");
        for(int i = 0; i < fib.length; i++){
            System.out.println("F"+i+" : " + fib[i]); // Imprimer les nombres du tableau
        }

        if(tooBig)
            System.out.println("Seul les 93 premiers nombres ont ete imprimes, car a partir de F94, les nombres sont trop gros...");
        
    }
    
}
