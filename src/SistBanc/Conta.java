package SistBanc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Conta {
    private double saldo; 
    private final Lock lock;

    public Conta(double saldoInicial) { 
        this.saldo = saldoInicial; //atribui ao saldo o valor fornecido 
        this.lock = new ReentrantLock(); //bloqueio
    }

    public double getSaldo() { //obtém o saldo da conta
        return saldo;
    }

    public void investir(double valor) { //realiza os investimentos
        lock.lock(); //bloqueia o acesso a conta
        try {
            saldo += valor; //muda o saldo com o valor fornecido
        } finally {
            lock.unlock(); //libera
        }
    }

    public boolean receber(double valor) { //recebe valores na conta
        lock.lock(); // bloqueia
        try {
            if (saldo >= valor) { //verifica o valor do saldo
                saldo -= valor; //retira o valor recebido
                return true; //realizou a compra
            }
            return false; //saldo insuficiente
        } finally {
            lock.unlock(); //libera
        }
    }
}