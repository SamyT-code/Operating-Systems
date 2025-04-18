/*------------------------------------------------------------
Fichier: Etudiant.java

Noms: Tisham Islam, Samy Touabi
Numeros d'etudiant: 300189261, 300184721
Date: Primtemps/été 2022

Nous avons pris du code de: https://www.youtube.com/watch?v=WUu7fovMpsg

Description: Ce programme contient le code pour la création d'un
             Etudiant ainsi que ses méthodes.
-------------------------------------------------------------*/

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Etudiant implements Runnable{

    private Bureau bureau;

    public Etudiant(Bureau bureau){
        this.bureau = bureau;
    }

    @Override
    public void run() {
        programmer(); // L'étudiant fait de la programmation autonome pour un certain temps
        demander(); // L'étudiant veut demander de l'aide
    }

    private void programmer() {
        int duration = new Random().nextInt(10); // Programmation aléatoire pour un temps aléatoire
        // System.out.println("Etudiant " + Thread.currentThread().getName() + " programme pour " + duration + " secondes");

        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Etudiant " + Thread.currentThread().getName() + " demande apres " + duration + " secondes");
    }

    private void demander() {
        bureau.accepter();
    }
    
}
