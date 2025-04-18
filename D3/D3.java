/*------------------------------------------------------------
Fichier: D3.java

Noms: Tisham Islam, Samy Touabi
Numeros d'etudiant: 300189261, 300184721
Date: Primtemps/été 2022

Nous avons pris du code de: https://www.youtube.com/watch?v=WUu7fovMpsg

Description: Ce programme contient le code pour la création d'un
             fil séparé d'étudiants.
-------------------------------------------------------------*/

import java.util.concurrent.Semaphore;

public class D3{

    public static void main(String[] args) {

        int n = Runtime.getRuntime().availableProcessors(); // n = nombre d'étudiants
        Thread[] threads = new Thread[n]; // Créer un tableau de fils

        Semaphore lockTA = new Semaphore(0);
        Semaphore lockEtudiant = new Semaphore(0);

        Bureau bureau = new Bureau(lockTA, lockEtudiant, 3);

        TA ta = new TA(lockTA, lockEtudiant);
        Thread threadTA = new Thread(ta);
        threadTA.start();

        for (int i = 0; i < n; i++) {
            Thread thread = new Thread(new Etudiant(bureau)); // Chaque fil est un objet d'étudiant
            thread.setName("Etudiant-" + i);
            threads[i] = thread;
        }

        for (int i = 0; i < n; i++) {
            threads[i].start(); // Commencer chaque fil
        }

        for (int i = 0; i < n; i++) {
            try {
                threads[i].join(); // join les fils
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        threadTA.interrupt();
        System.out.println("Le TA a fini d'aider les etudiants !");
        System.exit(0); // Pour ne pas avoir d'erreur d'exceprion, exit le programme

    }
    
} 