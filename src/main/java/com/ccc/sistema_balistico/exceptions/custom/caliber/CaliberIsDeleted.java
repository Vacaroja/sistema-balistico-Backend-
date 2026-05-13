package com.ccc.sistema_balistico.exceptions.custom.caliber;

public class CaliberIsDeleted extends RuntimeException {
    public CaliberIsDeleted() {
        super("the selected Caliber was deleted");
    }
}
