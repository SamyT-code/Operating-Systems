/*------------------------------------------------------------
Fichier: ThreadingFibonacci.java

Noms: Tisham Islam, Samy Touabi
Numeros d'etudiant: 300189261, 300184721
Date: Primtemps/été 2022

Description: Ce programme contient le code pour entrer dans le
             tableau fib les max premiers nombres Fibonacci ainsi
             que le comportement du fil enfant.
-------------------------------------------------------------*/

public class ThreadingFibonacci extends Thread {
    
    private int num; // Variable privée du nombre maximum
    private long[] fib;

    public ThreadingFibonacci(int num, long[] fib){ // Constructeur pour Threading qui prend le nombre maximum
        this.num = num;
        this.fib = fib;
    }

    @Override
    public void run(){
        trouverFibonacci(num, fib); // Appeler la fonction pour trouver les num premiers nombres Fibonacci
    }

    public void trouverFibonacci(int max, long[] fib){ // Cette fonction créer un tableau des max premiers numéros Fibonacci
        for (int i = 2; i < fib.length; i++){
            fib[i] = Math.abs(fib[i-1] + fib[i-2]);
        }
    }

}
