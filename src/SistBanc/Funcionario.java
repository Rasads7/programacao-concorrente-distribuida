package SistBanc;

public class Funcionario extends Thread {
    private static int funcContador = 0; //Contador de funcionarios

    private int funcNumero;
    private Conta salario; //erro em conta pq a classe conta n tá pronta
    private Conta investimento;

    public Funcionario(Conta salario, Conta investimento) { //mesmo "erro"
        this.funcNumero = ++funcContador; //incrementa o numero do funcionario
        this.salario = salario; //recebe os valores
        this.investimento = investimento; //recebe os valores
    }

    @Override
    public void run() { //executa a thread
        salario.receber(1400); // Pagamento do salario
        double valorInvestimento = 1400 * 0.2; //Investimento de 20%
        investimento.investir(valorInvestimento);
        System.out.printf("Funcionário %d da loja %d:\nSalário = %.2f, Investimento = %.2f \n", funcNumero, funcNumero / 2, salario.getSaldo(), investimento.getSaldo()); //retorna os valores em relacao ao funcionario 
    }
    
    public Conta getSalario() {
        return salario; 
    }

    public Conta getInvestimento() {
        return investimento;
    }
}