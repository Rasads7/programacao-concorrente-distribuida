package SistBanc;

public class Banco {
    public static void main(String[] args) {
        Conta[] contasClientes = new Conta[5]; //cria as contas do cliente
        for (int i = 0; i < contasClientes.length; i++) {
            contasClientes[i] = new Conta(1000); //valor incial
        }

        Loja[] lojas = new Loja[2]; //cria as lojas
        for (int i = 0; i < lojas.length; i++) {
            lojas[i] = new Loja(0); //valor inicial
        }

        Funcionario[] funcionarios = new Funcionario[4]; //cria os funcionários
        for (int i = 0; i < funcionarios.length; i++) {
            Conta salarioLoja = lojas[i / 2].getConta(); //conta: salário
            Conta contaInvestimento = new Conta(0); //investimento
            funcionarios[i] = new Funcionario(salarioLoja, contaInvestimento);
            funcionarios[i].start();
        }

        Cliente[] clientes = new Cliente[5]; //cria os clientes
        for (int i = 0; i < clientes.length; i++) {
            clientes[i] = new Cliente(contasClientes[i], lojas);
            clientes[i].start();
        }

        // executa todas as threads: cliente
        for (Cliente cliente : clientes) {
            try {
                cliente.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // executa o pagamento das lojas
        for (Loja loja : lojas) {
            loja.pagarSalarios(); //executa o pagamento dos funcionários
        }

        // Exibe os saldos finais
        System.out.println("Resultado das operações:");
        for (int i = 0; i < contasClientes.length; i++) {
            System.out.println("Cliente " + (i + 1) + ": " + contasClientes[i].getSaldo());
        }
        for (int i = 0; i < lojas.length; i++) {
            System.out.println("Loja " + (i + 1) + ": " + lojas[i].getConta().getSaldo());
        }
        for (int i = 0; i < funcionarios.length; i++) {
            System.out.println("Funcionário " + (i + 1) + ", loja " + (i / 2 + 1) + ", Salário: " + funcionarios[i].getSalario().getSaldo() + ", Investimento: " + funcionarios[i].getInvestimento().getSaldo());
        }
    }
}