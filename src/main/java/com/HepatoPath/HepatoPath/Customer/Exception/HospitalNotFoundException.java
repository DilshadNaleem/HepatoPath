package com.HepatoPath.HepatoPath.Customer.Exception;

public class HospitalNotFoundException extends RuntimeException {
    public HospitalNotFoundException(String noNearbyHospitalsFound) {
        super("No hospitals found near the specific location");
    }
}
