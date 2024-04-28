package projeto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Loja extends Thread {
    private String nome;
    private Conta conta;
    private double saldoTotal;
    private Lock lock;
    private List<Funcionario> funcionarios;
    private Emprestimo emprestimo;

    public Loja(String nome, double saldoInicial) {
        this.nome = nome;
        this.conta = new Conta(saldoInicial);
        this.saldoTotal = saldoInicial;
        this.lock = new ReentrantLock();
        this.funcionarios = new ArrayList<>();
        this.emprestimo = new Emprestimo();
    }

    public String getNome() {
        return nome;
    }

    public Conta getConta() {
        return conta;
    }

    public boolean atingiuSaldoMinimo() {
        return conta.getSaldo() >= 1400;
    }

    public void pagarSalarios() {
        lock.lock();
        try {
            double valorSalario = Funcionario.getSalario();
            for (Funcionario funcionario : funcionarios) {
                if (saldoTotal >= valorSalario) {
                    conta.debitar(valorSalario);
                    funcionario.receberPagamento(valorSalario);
                    System.out.println("Salário pago para o " + funcionario.getNome() + " da " + nome);
                    saldoTotal -= valorSalario;
                } else {
                    System.out.println("Saldo insuficiente na " + nome + " para pagar o salário do  " + funcionario.getNome());
                    if (solicitarEmprestimo(valorSalario)) {
                        // Se o empréstimo for aprovado, tenta pagar o salário novamente
                        conta.debitar(valorSalario);
                        funcionario.receberPagamento(valorSalario);
                        System.out.println("Salário pago para o " + funcionario.getNome() + " da " + nome + " após empréstimo.");
                        saldoTotal -= valorSalario;
                    } else {
                        break; // Interrompe o loop se o saldo for insuficiente para pagar um funcionário
                    }
                }
            }

            // Exibir o saldo final da conta da loja
            System.out.println("Saldo final da conta da " + nome + ": R$" + saldoTotal);
        } finally {
            lock.unlock();
        }
    }

    public synchronized void receberPagamento(double valor) {
        lock.lock();
        try {
            saldoTotal += valor;
        } finally {
            lock.unlock();
        }
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public boolean solicitarEmprestimo(double valor) {
        lock.lock();
        try {
            if (emprestimo.solicitarEmprestimo(valor)) {
                Funcionario funcionarioSemReceber = null;
                for (Funcionario funcionario : funcionarios) {
                    if (!funcionario.recebeuSalario()) {
                        funcionarioSemReceber = funcionario;
                        break;
                    }
                }
    
                if (funcionarioSemReceber != null) {
                    double valorFaltante = Funcionario.getSalario() - saldoTotal;
                    if (valorFaltante > 0) {
                        if (valor >= valorFaltante) {
                            // Aprovar empréstimo apenas pelo valor faltante
                            conta.creditar(valorFaltante);
                            saldoTotal += valorFaltante;
                            funcionarioSemReceber.receberPagamento(valorFaltante);
                            System.out.println("Salário pago para o " + funcionarioSemReceber.getNome() + " da " + nome);
                            return true; // Empréstimo aprovado e funcionário pago
                        } else {
                            System.out.println("Empréstimo não aprovado. Valor insuficiente para cobrir o saldo em aberto.");
                            return false; // Empréstimo não aprovado, valor insuficiente
                        }
                    } else {
                        System.out.println("Todos os funcionários foram pagos.");
                        return false; // Todos os funcionários já receberam
                    }
                } else {
                    System.out.println("Todos os funcionários foram pagos.");
                    return false; // Todos os funcionários já receberam
                }
            } else {
                System.out.println("Empréstimo não aprovado.");
                return false; // Empréstimo não aprovado
            }
        } finally {
            lock.unlock();
        }
    }
    
    
}
