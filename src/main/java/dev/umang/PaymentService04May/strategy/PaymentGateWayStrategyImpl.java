package dev.umang.PaymentService04May.strategy;

public class PaymentGateWayStrategyImpl implements PaymentGateWaySelectionStrategy{
    @Override
    public int choosePaymentGatWay() {
        //the logic of you choosing the gateway comes
        //Out of 10, 7 should go to r
        //Math.random(1-10) if(val <= 7) return 1 else return 2
        int randomNo = (int)Math.random();
        randomNo = randomNo % 10; //(0-9)
        if(randomNo <= 6){ //0-6 7/10
            return 1;
        }else return 2;
    }
}
//0-9 , 0-6