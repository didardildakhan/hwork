package edu.narxoz.galactic.dispatcher;

import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.drones.DroneStatus;
import edu.narxoz.galactic.task.DeliveryTask;
import edu.narxoz.galactic.task.TaskState;

public class Dispatcher {

    public Result assignTask(DeliveryTask task, Drone drone) {
        if (task == null || drone == null) {
            return new Result(false, "task or drone is null");
        }
        if (drone.getStatus() != DroneStatus.IDLE) {
            return new Result(false, "drone is not idle");
        }
        if (task.getCargo().getWeightKg() > drone.getMaxPayloadKg()) {
            return new Result(false, "cargo too heavy for drone");
        }
        if (task.getState() != TaskState.CREATED) {
            return new Result(false, "task state is not created");
        }
        task.setAssignedDrone(drone);
        task.setState(TaskState.ASSIGNED);
        drone.setStatus(DroneStatus.IN_FLIGHT);
        return new Result(true, "");
    }

    public Result completeTask(DeliveryTask task) {
        if (task == null) {
            return new Result(false, "task is null");
        }
        if (task.getState() != TaskState.ASSIGNED) {
            return new Result(false, "task state is not assigned");
        }
        Drone drone = task.getAssignedDrone();
        if (drone == null) {
            return new Result(false, "no drone assigned to task");
        }
        if (drone.getStatus() != DroneStatus.IN_FLIGHT) {
            return new Result(false, "drone is not in flight");
        }
        task.setState(TaskState.DONE);
        drone.setStatus(DroneStatus.IDLE);
        return new Result(true, "");
    }
}