package payment.payment_types;

import payment.PaymentStrategy;

public class HalykBank implements PaymentStrategy {
    @Override
    public void pay() {
        System.out.println("The payment will complete by the Halyk Bank");
    }
}
