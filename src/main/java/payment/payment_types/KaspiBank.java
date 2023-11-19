package payment.payment_types;

import payment.PaymentStrategy;

public class KaspiBank implements PaymentStrategy {
    @Override
    public void pay() {
        System.out.println("The payment was successfully completed by the Kaspi Bank");
    }
}
