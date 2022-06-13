import java.util.LinkedList;

public class PC {

  // Lista de tamanho 2 para contemplar exemplo de produtor - consumidor
  LinkedList<Integer> list = new LinkedList<>();
  int capacity = 2;

  // Função produtor para ser chamada na thread
  public void produce() throws InterruptedException {
    int value = 0;
    while (true) {
      // synchronized para impedir outros metodos de executar enquanto executa
      // este escopo
      synchronized (this) {
        // se a list estiver cheia await
        while (list.size() == capacity) {
          wait();
        }

        System.out.println("Producer produced-" + value);

        // adicionar elementos na lista sempre aumentando o valor a ser adicionado
        list.add(value++);

        // notificar o consumidor para começar a consumir
        notify();

        // dorme 1000 milisegundos para executar mais lentamente
        Thread.sleep(1000);
      }
    }
  }

  // Função consumidor para ser chamada na thread
  public void consume() throws InterruptedException {
    while (true) {
      // synchronized para impedir outros metodos de executar enquanto executa
      // este escopo
      synchronized (this) {
        // se a list estiver vazia await
        while (list.size() == 0)
          wait();

        // remove o primeiro elemento da lista
        int val = list.removeFirst();

        System.out.println("Consumer consumed-" + val);

        // acorda o produtor para começa a produzir elementos e colocar na lista
        notify();

        // dorme 1000 milisegundos para executar mais lentamente
        Thread.sleep(1000);
      }
    }
  }
}
