package controller;

//extends Thread
//parametros por construtor
//metodo run

public class ThreadId extends Thread {
	
	private int idThread;
	
	public ThreadId(int idThread) {
		this.idThread = idThread;
	}
	
	@Override
	public void run() {
		System.out.println(idThread);
	}

}
