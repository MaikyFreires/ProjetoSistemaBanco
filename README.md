# ProjetoSistemaBanco

Claro! Vamos detalhar um pouco mais cada parte do código:

### Banco
- A classe `Banco` possui um `Lock` que é utilizado para garantir que as transferências entre contas sejam feitas de forma segura, evitando condições de corrida.
- O método `transferencia` recebe a conta de origem, a conta de destino e o valor a ser transferido. Antes de realizar a transferência, ele verifica se a conta de origem possui saldo suficiente. Se sim, realiza a transferência debitando o valor da conta de origem e creditando na conta de destino. Caso contrário, exibe uma mensagem informando que a transferência não foi realizada devido a saldo insuficiente.

### Cliente
- A classe `Cliente` extende a classe `Thread`, o que permite que cada cliente seja executado em uma thread separada.
- No construtor, o cliente recebe um nome, um saldo inicial, o banco onde tem conta e as lojas onde realizará as compras.
- O método `run` é responsável por simular as compras do cliente. Enquanto o saldo da conta do cliente for maior que zero, ele itera sobre as lojas, realizando transferências para pagar pelas compras. Após cada compra, o cliente aguarda um segundo antes de realizar a próxima.

### Conta
- A classe `Conta` representa uma conta bancária, com um saldo inicial e métodos para creditar e debitar valores.
- Os métodos `creditar` e `debitar` são sincronizados para garantir que as operações de crédito e débito sejam feitas de forma segura, evitando problemas de concorrência.
- O método `getTitular` não está implementado, mas poderia ser adicionado para retornar o titular da conta se necessário.

### Loja
- A classe `Loja` extende a classe `Thread`, permitindo que cada loja seja executada em uma thread separada.
- No construtor, a loja recebe um nome e um saldo inicial.
- A loja possui um método `pagarSalarios` que é responsável por pagar os salários dos funcionários. O método verifica se a loja possui saldo suficiente para pagar os salários e, caso contrário, solicita um empréstimo.
- O método `receberPagamento` é sincronizado para garantir que o saldo total da loja seja atualizado de forma segura.

### Funcionário
- A classe `Funcionario` representa um funcionário de uma loja, com uma conta bancária de salário e uma conta de investimento.
- O método estático `getSalario` retorna o valor do salário fixo dos funcionários.
- O método `receberPagamento` é responsável por receber o salário e investir uma parte dele, debitando o valor do investimento da conta de salário e creditando na conta de investimento.

### Emprestimo
- A classe `Emprestimo` representa um empréstimo com uma taxa de juros e um número de parcelas fixos.
- O método `solicitarEmprestimo` simula a solicitação de um empréstimo, calculando o valor total a ser pago e as parcelas. Neste exemplo, o empréstimo é sempre aprovado.

### SistemaBancario
- A classe `SistemaBancario` é a classe principal que cria o banco, as lojas, os funcionários e os clientes, e inicia as threads para simular suas interações.
- Os funcionários são associados às contas das lojas para receberem os salários.
- Os clientes são criados com um saldo inicial e realizam compras nas lojas, pagando pelos produtos através de transferências bancárias.
- No final, as lojas realizam o pagamento dos salários dos funcionários.

Esse código é uma simplificação e serve para ilustrar como um sistema bancário poderia ser implementado em Java, utilizando threads para simular a interação entre as entidades do sistema.
