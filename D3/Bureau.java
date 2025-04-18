/*------------------------------------------------------------
Fichier: Etudiant.java

Noms: Tisham Islam, Samy Touabi
Numeros d'etudiant: 300189261, 300184721
Date: Primtemps/été 2022

Nous avons pris du code de: https://www.youtube.com/watch?v=WUu7fovMpsg

Description: Ce programme contient le code pour la création d'un
             Bureau ainsi que ses méthodes.
-------------------------------------------------------------*/

import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bureau {
    
    private Semaphore chaiseTA;
    private Semaphore lockTA;
    private Semaphore lockEtudiant;
    private AtomicInteger n; // n = nombre d'étudiants
    private int chaisesEnAttente;
    private Lock lock;

    public Bureau(Semaphore lockTA, Semaphore lockEtudiant, int chaisesEnAttente) {
        this.lockTA = lockTA;
        this.lockEtudiant = lockEtudiant;
        this.chaisesEnAttente = chaisesEnAttente;
        this.n = new AtomicInteger(0);
        this.chaiseTA = new Semaphore(1);
        this.lock = new ReentrantLock();
    }

    public void accepter(){
        lock.lock();
        if(n.get() == chaisesEnAttente){ // Toutes les chases sont occupés
            Date date = new Date();
            System.out.println(Thread.currentThread().getName() + " retourne programmer a: " + date); // L'étudiant retourne programmer seul
            lock.unlock();
            return;
        }

        lock.unlock();

        n.incrementAndGet(); // incrémenter le nombres d'étudiants en attente

        try {
            chaiseTA.acquire();
            n.decrementAndGet();
            System.out.println("Etudiant " + Thread.currentThread().getName() + " est aide a: " + new Date());
            lockTA.release();
            lockEtudiant.acquire();
            System.out.println("Etudiant " + Thread.currentThread().getName() + " a fini d'etre aider: " + new Date());
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            chaiseTA.release();
        }

    }

}
