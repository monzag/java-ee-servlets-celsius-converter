package com.monzag;

public class DegreeConvertor {

    public static Double convertCelsiusToFahreinheit(Double celsiusDegree) {
        Double result = (celsiusDegree * 1.8) + 32;

        return result;
    }
}
