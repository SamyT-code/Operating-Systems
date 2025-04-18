/*------------------------------------------------------------
Fichier: Consumer.java

Noms: Tisham Islam, Samy Touabi
Numeros d'étudiant: 300189261, 300184721
Date: Primtemps/été 2022

Description: Ce programme contient le code pour lire la séquence
             de la mémoire partagée et l'afficher. 
-------------------------------------------------------------*/

public class Consumer extends Thread{

    private long[] cat; // Tableau des nombres Catalan
    private boolean tooBig;

    public Consumer(long[] cat, boolean tooBig){
        this.cat = cat;
        this.tooBig = tooBig;
    }

    @Override
    public void run(){
        System.out.println("Le consommateur lit la sequence de la memoire partagee et l'affiche:");
        for (int i = 0; i < cat.length; i++) {
            System.out.println("C"+i+": "+cat[i]); // Afficher la séquence
        }
        if(tooBig)
            System.out.println("Seul les 11 premiers nombres ont ete imprimes, car a partir de C10, les nombres sont trop gros...");
    }

    

}