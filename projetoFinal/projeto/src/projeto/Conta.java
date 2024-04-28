package projeto;
class Conta {
    private double saldo;
    private double saldoTotal;

    public Conta(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getSaldoFinal(){
        return saldoTotal;
    }

    public synchronized void creditar(double valor) {
        saldo += valor;
    }

    public synchronized void debitar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente para realizar a operação.");
        }
    }

    public String getTitular() {
        return ""; // Adicione um método para retornar o titular da conta se necessário
    }
}
