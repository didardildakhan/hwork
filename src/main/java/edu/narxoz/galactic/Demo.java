package edu.narxoz.galactic;
import edu.narxoz.galactic.bodies.Planet;
import edu.narxoz.galactic.cargo.Cargo;
import edu.narxoz.galactic.drones.HeavyDrone;
import edu.narxoz.galactic.drones.LightDrone;
import edu.narxoz.galactic.dispatcher.Dispatcher;
import edu.narxoz.galactic.task.DeliveryTask;
public class Demo{
    public static void main(String[] args){
        Planet earth = new Planet("earth", 0, 0, "air");
        Planet mars = new Planet("mars", 100, 100, "co2");
        LightDrone ld = new LightDrone("a", 50);
        HeavyDrone hd = new HeavyDrone("b", 200);
        Cargo c1 = new Cargo(120, "heavy box");
        Cargo c2 = new Cargo(30, "light box");
        DeliveryTask t1 = new DeliveryTask(earth, mars, c1);
        DeliveryTask t2 = new DeliveryTask(earth, mars, c2);
        Dispatcher d = new Dispatcher();
        System.out.println("assign light cargo to ld: " + d.assignTask(t1, ld).reason());
        System.out.println("assign light cargo to hd: " + d.assignTask(t2, hd).reason());
        System.out.println("delivery time: " + t2.estimateTime() + " minutes");
        System.out.println("completing task: " + d.completeTask(t2).reason());
        System.out.println("final drone status: " + hd.getStatus());
        System.out.println("final task state: " + t2.getState()); 
    }
}
