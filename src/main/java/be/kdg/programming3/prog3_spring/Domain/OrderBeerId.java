package be.kdg.programming3.prog3_spring.Domain;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class OrderBeerId {
    private int orderId;
    private int beerId;

    public OrderBeerId() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    public OrderBeerId(int orderId, int beerId) {
        this.orderId = orderId;
        this.beerId = beerId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OrderBeerId that)) return false;
        return orderId == that.orderId && beerId == that.beerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, beerId);
    }
}
