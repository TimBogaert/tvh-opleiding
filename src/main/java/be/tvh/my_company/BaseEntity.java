package be.tvh.my_company;

import java.util.UUID;

/**
 * Created by tim036 on 2/10/2017.
 */
public abstract class BaseEntity {
    private final UUID identifier;
//    private final long identifier;

    public BaseEntity() {
        identifier = UUID.randomUUID();
//        Random random = new Random();
//        long number = random.nextLong();
//        identifier = number < 0 ? number * -1 : number;
    }

//    public String getIdentifierString() {
////        return identifier.toString();
//    }

    public UUID getIdentifier() {
        return identifier;
//        return identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        return identifier != null ? identifier.equals(that.identifier) : that.identifier == null;
    }

    @Override
    public int hashCode() {
        return identifier != null ? identifier.hashCode() : 0;
    }
}
