/*------------------------------------------------------------
Fichier: Etudiant.java

Noms: Tisham Islam, Samy Touabi
Numeros d'etudiant: 300189261, 300184721
Date: Primtemps/été 2022

Nous avons pris du code de: https://www.youtube.com/watch?v=WUu7fovMpsg

Description: Ce programme contient le code pour la création d'un
             TA ainsi que ses méthodes.
-------------------------------------------------------------*/

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TA implements Runnable{

    private Semaphore lockTA;
    private Semaphore lockEtudiant;

    public TA(Semaphore lockTA, Semaphore lockEtudiant){
        this.lockTA = lockTA;
        this.lockEtudiant = lockEtudiant;
    }

    @Override
    public void run() {

        while(true){
            try {
                lockTA.acquire();
                System.out.println("TA se reveille");
                aiderEtudiant();

                lockEtudiant.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }

    }

    private void aiderEtudiant() {
        int duration = new Random().nextInt(5);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
