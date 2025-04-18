/*------------------------------------------------------------
Fichier: cpr.c

Noms: Tisham Islam, Samy Touabi
Numero d'etudiant: 300189261, 300184721

Description: Ce programme contient le code pour la creation
			d'un processus enfant et y attacher un tuyau.
		L'enfant envoyera des messages par le tuyau
		qui seront ensuite envoyes a la sortie standard.

Explication du processus zombie
(point 5 de "A completer" dans le devoir):
	Un processus zombie est un processus enfant qui ferme (avec le commande exit())
	avant que le processus parent peut voir que le processus enfant a fermé.
	Ceci génère un processus zombie car le processus enfant fait rien et il
	ne se termine pas.
	On exécute cpr avec le processus enfant et exit() après
	le processus termine, mais le processus parent sleep() pour 5
	secondes au même temps, donc le processus parent ne peut pas voir
	que le processus enfant est terminé, qui résulte en un processus zombie.

-------------------------------------------------------------*/
#include <stdio.h>
#include <sys/select.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <signal.h>
#include <string.h>
#define BUFFER_SIZE 64 // For some reason, different buffer sizes give us some weird characters on the Unix command line
#define READ_END 0
#define WRITE_END 1

/* Prototype */
void creerEnfantEtLire(int);

/*-------------------------------------------------------------
Function: main
Arguments:
	int ac - nombre d'arguments de la commande
	char **av - tableau de pointeurs aux arguments de commande
Description:
	Extrait le nombre de processus a creer de la ligne de
	commande. Si une erreur a lieu, le processus termine.
	Appel creerEnfantEtLire pour creer un enfant, et lire
	les donnees de l'enfant.
-------------------------------------------------------------*/

int main(int ac, char **av)
{
	int numeroProcessus;

	if (ac == 2)
	{
		if (sscanf(av[1], "%d", &numeroProcessus) == 1)
		{
			creerEnfantEtLire(numeroProcessus);
		}
		else
			fprintf(stderr, "Ne peut pas traduire argument\n");
	}
	else
		fprintf(stderr, "Arguments pas valide\n");
	return (0);
}

/*-------------------------------------------------------------
Function: creerEnfantEtLire
Arguments:
	int prcNum - le numero de processus
Description:
	Cree l'enfant, en y passant prcNum-1. Utilise prcNum
	comme identificateur de ce processus. Aussi, lit les
	messages du bout de lecture du tuyau et l'envoie a
	la sortie standard (df 1). Lorsqu'aucune donnee peut
	etre lue du tuyau, termine.
-------------------------------------------------------------*/

void creerEnfantEtLire(int prcNum)
{

	/* S.V.P. completez cette fonction selon les
		instructions du devoirs. */
	int nextNum = prcNum - 1;
	int pid;
	char numStr[3]; // Supposément, on aura pas un prcNum avec plus de 3 chiffres
	char buff[BUFFER_SIZE];

	// Commencement du processus
	printf("Processus %d commence\n", prcNum);
	fflush(stdout); // flush pour immédiatement print à la console

	if (prcNum > 1) // Pour les processus de n à 2
	{

		int pfd[2]; // Créer un pipe

		if (pipe(pfd) != 0)
		{
			printf("Erreur avec le pipe.\n");
		}

		pid = fork(); // Créer le processus enfant

		if (pid < 0)
		{ // Error
			printf("Erreur avec le fork().\n");
			exit(-1);
		}
		else if (pid == 0) // Processus enfant exécute ce code
		{												
			close(pfd[READ_END]);						// Fermée le reading du pipe
			dup2(pfd[WRITE_END], 1);					// Rediriger à l'écriture
			sprintf(numStr, "%d", nextNum);		// Convertir l'entier nextNum à un string
			execl("./cpr", "./cpr", numStr, NULL);	// Exécuter cpr num-1
			exit(-1);
		}
		else // Processus parent exécute ce code
		{					
			close(pfd[1]);	// Fermer le writing du pipe
			while (read(pfd[READ_END], buff, BUFFER_SIZE) > 0)
			{
				printf("%s", buff);
				fflush(stdout);
				memset(buff, 0, BUFFER_SIZE); // Clear string
			}
			sleep(5);
			printf("Processus %d termine\n", prcNum);
			fflush(stdout);
		}
	}
	else
	{
		// Exécution du dernier processus (processus 1)
		sleep(5);
		printf("Processus %d termine\n", prcNum);
		fflush(stdout);
	}
}
