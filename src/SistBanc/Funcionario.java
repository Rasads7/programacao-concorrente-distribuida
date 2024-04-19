package SistBanc;

public class Funcionario extends Thread {
    private static int funcContador = 0; //Contador de funcionários

    private int funcNumero;
    private Conta salario; 
    private Conta investimento;

    public Funcionario(Conta salario, Conta investimento) {
        this.funcNumero = ++funcContador; //incrementa o número do funcionário
        this.salario = salario; //recebe a conta: salário
        this.investimento = investimento; //recebe a conta: investimento
    }

    @Override
    public void run() { //executa a thread
        salario.receber(1400); // Pagamento do salário
        double valorInvestimento = 1400 * 0.2; //Investimento de 20%
        investimento.investir(valorInvestimento);
        System.out.printf("Funcionário %d da loja %d:\nSalário = %.2f, Investimento = %.2f \n", funcNumero, funcNumero / 2, salario.getSaldo(), investimento.getSaldo()); //retorna os valores em relação ao funcionário 
    }
    
    public Conta getSalario() {
        return salario;
    }

    public Conta getInvestimento() {
        return investimento;
    }
}