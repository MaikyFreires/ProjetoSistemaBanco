package projeto;
class Funcionario extends Thread {
    private String nome;
    private Conta contaSalario;
    private Conta contaInvestimento;

    public Funcionario(String nome) {
        this.nome = nome;
        this.contaSalario = new Conta(0); // Saldo inicial da conta de salário é zero
        this.contaInvestimento = new Conta(0); // Saldo inicial da conta de investimento é zero
    }

    public static double getSalario() {
        return 1400.0;
        
    }
    public String getNome() {
        return nome;
    }
    public Conta getContaSalario() {
        return contaSalario;
    }

    public void setContaSalario(Conta contaSalario) {
        this.contaSalario = contaSalario;
    }

    public double getSaldoInvestimento() {
        return contaInvestimento.getSaldo();
    }
    public boolean recebeuSalario() {
        return contaSalario.getSaldo() > 0;
    }
    public double getValorConta(double valorSalario) {
        double valorInvestimento = valorSalario * 0.2; // 20% do salário para investimento
        double valorConta = valorSalario - valorInvestimento;
        return valorConta;
    }

    public void receberPagamento(double valorSalario) {
        contaSalario.creditar(valorSalario);
        double valorInvestimento = valorSalario * 0.2; // 20% do salário para investimento
        contaSalario.debitar(valorInvestimento); // Deduzindo o valor do investimento
        double valorConta = getValorConta(valorSalario);
        contaInvestimento.creditar(valorInvestimento);
        System.out.println(nome + " recebeu o salário e investiu R$" + valorInvestimento);
        System.out.println("Valor na conta após investir: R$" + valorConta);
    }
    
    public void pedirDemissao() {
        System.out.println( nome + " pediu demissão.");
    }
}
