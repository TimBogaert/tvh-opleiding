package be.tvh.my_company;

/**
 * Created by tim036 on 3/10/2017.
 */
public class HospitalisationInsurance extends Remuneration {
    static {
        maxAmount = 1;
    }

    public HospitalisationInsurance(double cost) {
        super(cost);
    }
}
