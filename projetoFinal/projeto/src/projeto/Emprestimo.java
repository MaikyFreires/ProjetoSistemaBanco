package projeto;
class Emprestimo {
    private double taxaJuros;
    private int numeroParcelas;

    public Emprestimo() {
        this.taxaJuros = 0.1; // 10% de juros
        this.numeroParcelas = 12; // 12 parcelas
    }

    public boolean solicitarEmprestimo(double valor) {
        double valorTotal = valor * (1 + taxaJuros);
        double valorParcela = valorTotal / numeroParcelas;
        System.out.println("Solicitação de empréstimo de R$" + valor + " com juros de " + (taxaJuros * 100) + "% em " + numeroParcelas + " parcelas de R$" + valorParcela);
        return true; // Por simplicidade, sempre aprova o empréstimo
    }
}
