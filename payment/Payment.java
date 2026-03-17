package payment;

import java.util.Iterator;
/*

Need to implement:
- possible Wallet class for Users to be an endpoint to manage money from
- refund -> cancel?
- remove methodType? 


*/

public class Payment<T, V> {
    private class PaymentIterator implements Iterator<Integer>{
        private int count = 0;

        public boolean hasNext(){
            return true;
        }

        public Integer next(){
            count += 1;
            return count;
        }
    
    }

    private int paymentId;
    private T from;
    private V to;
    private double amount;
    private String methodType;
    private boolean isSuccessful;
    private final PaymentIterator iter = new PaymentIterator();

    public Payment(T from, V to, double amount){
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.paymentId = iter.next();
    }

    public boolean processPayment(double amount){
        double diff = this.amount - amount;
        boolean status = diff <= 0 ? true: false;
        double leftover = Math.abs(diff);
        this.isSuccessful = status;
        // not processed, then failed. 
        if (!status) return status;

        // send the change over back to T
        // this.to.sendMoneyTo(from, amount)
        // this.from.takeMoneyFrom(to, amount);

        return status;

    }

    public void refund(){
        // this.from.sendMoneyTo(to, this.amount);
        // this.to.takeMoneyFrom(from, this.amount);

    }

    public String generateString(){
        return String.format("PaymentID: %d\nAmount Requested: %0.2f\nCurrentStatus: %b\n", paymentId, amount, isSuccessful);
    }


}