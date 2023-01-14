package com.myrefactor.pattern.isp;
/*
 * 
@startuml

interface Trackable {
    +double getLatitude()
    +double getLongitude()
    +double getFuelLevel()
    +double getTirePressure()
    +void addFuel(double amount)
    +void inflateTires(double pressure)
}

class DeliveryTruck implements Trackable {
    +GPSSensor gps
    +FuelSensor fuel
    +TirePressureSensor tires
    +DeliveryTruck()
    +double getLatitude()
    +double getLongitude()
    +double getFuelLevel()
    +void addFuel(double amount)
    +double getTirePressure()
    +void inflateTires(double pressure)
}

class ElectricTruck implements Trackable {
    +GPSSensor gps
    +FuelSensor fuel
    +TirePressureSensor tires
    +ElectricTruck()
    +double getLatitude()
    +double getLongitude()
    +double getFuelLevel()
    +void addFuel(double amount)
    +double getTirePressure()
    +void inflateTires(double pressure)
}

class GPSSensor {
    +double getLatitude()
    +double getLongitude()
}

class FuelSensor {
    +double getLevel()
    +void addFuel(double amount)
}

class TirePressureSensor {
    +double getPressure()
    +void inflate(double pressure)
}

DeliveryTruck }|..|| GPSSensor
DeliveryTruck }|..|| FuelSensor
DeliveryTruck }|..|| TirePressureSensor

ElectricTruck }|..|| GPSSensor
ElectricTruck }|..|| FuelSensor
ElectricTruck }|..|| TirePressureSensor

@enduml

 */
public class Main {

}
