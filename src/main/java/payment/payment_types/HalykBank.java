package payment.payment_types;

import payment.PaymentStrategy;

public class HalykBank implements PaymentStrategy {
    @Override
    public void pay() {
        System.out.println("The payment was successfully completed by the Halyk Bank");
    }
}
