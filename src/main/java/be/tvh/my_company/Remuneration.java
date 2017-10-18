package be.tvh.my_company;

/**
 * Created by tim036 on 2/10/2017.
 */
public abstract class Remuneration extends BaseEntity {
    static int maxAmount;
    private double cost;

    public Remuneration() {
        super();
    }

    public Remuneration(double cost) {
        super();
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public static int getMaxAmount() {
        return maxAmount;
    }
//
//    void setMaxAmount(int maxAmount){
//        this.maxAmount = maxAmount;
//    }

    @Override
    public String toString() {
        return "Remuneration{" +
                "cost=" + cost +
                '}';
    }
}
