package controller;

import java.util.concurrent.Semaphore;

public class ThreadCarro extends Thread {

	private int idCarro;
	private static int posChegada;
	private static int posSaida;
	private Semaphore semaforo;

	public ThreadCarro(int idCarro, Semaphore semaforo) {
		this.idCarro = idCarro;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {

		carroAndando();

// ------- Inicio Seção Critica -----------------

		try {
			semaforo.acquire();
			carroEstacionado();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			semaforo.release();
		}

// ------- Fim Seção Critica -----------------

		carroSaindo();

	}

	private void carroAndando() {
		int distanciaTotal = (int) (Math.random() * 1001 + 1000);
		int distanciaPercorrida = 0;
		int deslocamento = 100;
		int tempo = 1000;

		while (distanciaPercorrida < distanciaTotal) {
			distanciaPercorrida += deslocamento;
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("#" + idCarro + " já andou " + distanciaPercorrida + "m.");
		}
		posChegada++;
		System.out.println("#" + idCarro + " foi o " + posChegada + " o. a chegar");
	}

//Math.randon() <0 | 0,99999999... >
//De 0 a 1000
//(Min) 0 * 1000 = 0 || (Max) 0.99999 * 1001 = 1000.999 -> (int)1000

	private void carroEstacionado() {
		System.out.println("#" + idCarro + " estacionou");
		int tempo = (int) ((Math.random() * 2001) + 1000);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void carroSaindo() {
		posSaida++;
		System.out.println("#" + idCarro + " foi o " + posSaida + "o. s sair");
	}

}
