
public class ThreadExample {
    public static void main(String[] args) throws InterruptedException {
        // Objeto da classe que contem produtor e consumidor
        final PC pc = new PC();

        // cria thread produtor
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // cria thread consumidor
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // iniciar as threads
        t1.start();
        t2.start();

        // t1 termina antes de t2
        t1.join();
        t2.join();
    }

}