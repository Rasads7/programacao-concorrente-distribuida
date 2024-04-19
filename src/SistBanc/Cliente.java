package SistBanc;

public class Cliente extends Thread {
    private static int clienteContador = 0; //Contador de clientes

    private int clienteNumero;
    private Conta conta;
    private Loja[] lojas;

    public Cliente(Conta conta, Loja[] lojas) {
        this.clienteNumero = ++clienteContador; //incrementa o número do cliente
        this.conta = conta; //recebe a conta: cliente
        this.lojas = lojas; //recebe as lojas 
    }

    @Override
    public void run() { //executa a thread
        while (conta.getSaldo() > 0) { //repete enquanto houver saldo na conta do cliente
            double compra = Math.random() < 0.5 ? 100 : 200; //realiza um compra aleatória de 100 ou 200
            int lojaCompra = (int) (Math.random() * lojas.length); //escolhe uma loja
            Loja loja = lojas[lojaCompra];
            synchronized (loja.getConta()) { //fecha o acesso
                if (conta.receber(compra)) {
                    loja.getConta().investir(compra); //realiza a compra
                    System.out.printf("Cliente %d, compra realizada na loja %d\nValor = R$ %.2f\n", clienteNumero, lojaCompra, compra); //retorna o valor da compra
                } else {
                    break; // Se não houver saldo suficiente, sai do loop
                }
            }
        }
    }
}