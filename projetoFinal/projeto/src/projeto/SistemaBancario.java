package projeto;
public class SistemaBancario {
    public static void main(String[] args) {
        // Criando o banco
        Banco banco = new Banco();

        // Criando as lojas
        Loja loja1 = new Loja("Loja 1", 0);
        Loja loja2 = new Loja("Loja 2", 0);

        // Criando os funcionários
        Funcionario funcionario1Loja1 = new Funcionario("Funcionario 1");
        Funcionario funcionario2Loja1 = new Funcionario("Funcionario 2");
        Funcionario funcionario1Loja2 = new Funcionario("Funcionario 1");
        Funcionario funcionario2Loja2 = new Funcionario("Funcionario 2");

        // Definindo contas das lojas para pagar salários
        funcionario1Loja1.setContaSalario(loja1.getConta());
        funcionario2Loja1.setContaSalario(loja1.getConta());
        funcionario1Loja2.setContaSalario(loja2.getConta());
        funcionario2Loja2.setContaSalario(loja2.getConta());

        // Adicionando funcionários às lojas
        loja1.adicionarFuncionario(funcionario1Loja1);
        loja1.adicionarFuncionario(funcionario2Loja1);
        loja2.adicionarFuncionario(funcionario1Loja2);
        loja2.adicionarFuncionario(funcionario2Loja2);

        // Iniciando threads dos funcionários
        funcionario1Loja1.start();
        funcionario2Loja1.start();
        funcionario1Loja2.start();
        funcionario2Loja2.start();

        // Criando os clientes
        Cliente cliente1 = new Cliente("Cliente 1", 1000, banco, loja1, loja2);
        Cliente cliente2 = new Cliente("Cliente 2", 1000, banco, loja1, loja2);
        Cliente cliente3 = new Cliente("Cliente 3", 1000, banco, loja1, loja2);
        Cliente cliente4 = new Cliente("Cliente 4", 1000, banco, loja1, loja2);
        Cliente cliente5 = new Cliente("Cliente 5", 1000, banco, loja1, loja2);

        // Iniciando threads dos clientes
        cliente1.start();
        cliente2.start();
        cliente3.start();
        cliente4.start();
        cliente5.start();

        // Esperando a finalização das threads dos clientes
        try {
            cliente1.join();
            cliente2.join();
            cliente3.join();
            cliente4.join();
            cliente5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Transferência dos salários dos funcionários pelas lojas
        loja1.pagarSalarios();
        loja2.pagarSalarios();
        

    }
}