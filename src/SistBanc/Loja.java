package SistBanc;

public class Loja {
    private Conta conta; //Conta da loja

    public Loja(double saldoInicial) {
        this.conta = new Conta(saldoInicial); //cria o objeto conta: loja
    }

    public Conta getConta() {
        return conta;
    }

    public void pagarSalarios() {
    	//executa o pagamento dos funcionários e retorna uma mensagem de confirmação
        System.out.println("Pagamento dos funcionários efetuado");
    }
}