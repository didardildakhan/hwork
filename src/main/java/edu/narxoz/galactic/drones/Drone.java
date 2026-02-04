package edu.narxoz.galactic.drones;

public abstract class Drone {
    private String id;
    private DroneStatus status;
    private double maxPayloadKg;

    protected Drone(String id, double maxPayloadKg) {
        if (maxPayloadKg <= 0) {
            throw new IllegalArgumentException("must be bigger than 0");
        }
        this.id = id;
        this.status = DroneStatus.IDLE;
        this.maxPayloadKg = maxPayloadKg;
    }

    public String getId() {
        return id;
    }

    public DroneStatus getStatus() {
        return status;
    }

    public double getMaxPayloadKg() {
        return maxPayloadKg;
    }

    public void setStatus(DroneStatus status) {
        this.status = status;
    }

    public abstract double speedKmPerMin();
}