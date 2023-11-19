package payment.payment_types;

import payment.PaymentStrategy;

public class JusanBank implements PaymentStrategy {
    @Override
    public void pay() {
        System.out.println("The payment was successfully completed by the Jusan Bank");
    }
}
