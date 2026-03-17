package payment;

/*
- Get rid of generics, replace with users
- make safer with a payment manager

*/

public class Wallet<T> {
    private double balance;
    private T owner;
    
    public Wallet(T owner){
        this.balance = 0;
        this.owner = owner;
    }
    public Wallet(T owner, double amount){
        this.balance = amount;
        this.owner = owner;
    }

    public double getBalance(){
        return balance;
    }

    // dangerous! but most simple
    public boolean modifyBalance(double amt){
        if (amt < -this.balance){
            System.out.println("Insufficient funds!");
            return false; // insufficient funds!
        }
        this.balance += amt;
        return true;
    }

    // also would be useful to have a manager to complete a handshake for maximum security
    public boolean sendMoneyTo(Wallet w, double amount){
        if (amount > balance) {
            System.out.println("Insufficient funds!");
            return false;
        }
        w.modifyBalance(amount);
        this.balance -= amount;
        return true;
    }
}
