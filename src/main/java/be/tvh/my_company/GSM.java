package be.tvh.my_company;

/**
 * Created by tim036 on 3/10/2017.
 */
public class GSM extends Remuneration {
    static {
        maxAmount = 2;
    }

    private static final int MSISDN_LENGTH = 10;

    private String type;
    private String msisdn;

    public GSM(double cost, String type, String msisdn) {
        super(cost);
        this.type = type;
        setMsisdn(msisdn);
    }

    public GSM(double cost) {
        super(cost);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        if (msisdn.length() == MSISDN_LENGTH && msisdn.charAt(0) == '0') {
            this.msisdn = msisdn;
        }
    }

    @Override
    public String toString() {
        return super.toString() + "GSM{" +
                "type='" + type + '\'' +
                ", msisdn='" + msisdn + '\'' +
                '}';
    }


}
