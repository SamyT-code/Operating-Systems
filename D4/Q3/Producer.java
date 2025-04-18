/*------------------------------------------------------------
Fichier: Producer.java

Noms: Tisham Islam, Samy Touabi
Numeros d'étudiant: 300189261, 300184721
Date: Primtemps/été 2022

Description: Ce programme contient le code pour la génération
             de la séquence de Catalan pour l'écrire dans la
             mémoire partagée. 
-------------------------------------------------------------*/

public class Producer{

    public static void main(String[] args) {
        
        int num = Integer.parseInt(args[0]);
        boolean tooBig = false;

        if(num > 11){ // Imprimer les 11 premiers nombres seulement, car ceux après sont trop grands
            num = 11;
            tooBig = true;
        }

        long[] cat = new long[num];

        System.out.println("Le producteur genere la sequence de Catalan...");
        populerCatalan(cat);

        Consumer c = new Consumer(cat, tooBig); // Créer le Thread Consumer
        c.start(); // Lancer le Thread Consumer pour afficher la séquence
    }

    public static void populerCatalan(long[] cat){ // Cette fonction créer un tableau des max premiers numéros Fibonacci
        for (int i = 0; i < cat.length; i++) {
            cat[i] = fact(2*i)/((fact(i+1))*fact(i));
        }
    }

    public static long fact(long number) {
        long count = 1;
        for (int i = 2; i <= number; i++) {
            count = count * i;
        }
        return count;
    }

}