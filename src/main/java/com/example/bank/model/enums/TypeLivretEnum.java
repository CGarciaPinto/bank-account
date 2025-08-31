package com.example.bank.model.enums;

public enum TypeLivretEnum {
    LIVRET_A("A", 22950.0),
    LIVRET_B("B", 50000.0),
    LIVRET_C("C", 100000.0);

    private final String code;
    private final double plafondDepot;

    TypeLivretEnum(String code, double plafondDepot){
        this.code = code;
        this.plafondDepot = plafondDepot;
    }

    public String getCode() {
        return code;
    }

    public double getPlafondDepot() {
        return plafondDepot;
    }
}
