package projeto;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
class Banco {
    private Lock lock;

    public Banco() {
        this.lock = new ReentrantLock();
    }

    public void transferencia(Conta origem, Conta destino, double valor) {
        lock.lock();
        try {
            if (origem.getSaldo() >= valor) {
                origem.debitar(valor);
                destino.creditar(valor);
            } else {
                System.out.println("Transferência não realizada devido a saldo insuficiente na conta de origem.");
            }
        } finally {
            lock.unlock();
        }
    }
}