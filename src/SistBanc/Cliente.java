package SistBanc;

public class Cliente extends Thread {
    private static int clienteContador = 0;

    private int clienteNumero;
    private Conta conta; //erro em conta pq a classe conta n tá pronta
    private Loja[] lojas; //erro em loja pq a classe loja n tá pronta

    public Cliente(Conta conta, Loja[] lojas) {
        this.clienteNumero = ++clienteContador; //incrementa o numero do cliente
        this.conta = conta; //recebe os valores
        this.lojas = lojas; //recebe os valores
    }

    @Override
    public void run() { //executa a thread
        while (conta.getSaldo() > 0) { //repete enquanto houver saldo na conta do cliente
            double compra = Math.random() < 0.5 ? 100 : 200; //realiza um compra aleatoria de 100 ou 200
            int lojaCompra = (int) (Math.random() * lojas.length);
            Loja loja = lojas[lojaCompra];
            synchronized (loja.getConta()) {
                if (conta.receber(compra)) {
                    loja.getConta().investir(compra);
                    System.out.printf("Cliente %d, compra realizada na loja %d\nValor = R$ %.2f\n", clienteNumero, lojaCompra, compra); //retorna o valor da compra
                } else {
                    break; // Se não houver saldo suficiente, sai do loop
                }
            }
        }
    }
}
