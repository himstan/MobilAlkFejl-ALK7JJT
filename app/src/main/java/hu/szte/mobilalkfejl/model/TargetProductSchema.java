package hu.szte.mobilalkfejl.model;

public class TargetProductSchema {

    public TargetProductSchema(String baseType, String schemaLocation, String type) {
        this.baseType = baseType;
        this.schemaLocation = schemaLocation;
        this.type = type;
    }

    protected String baseType;
    protected String schemaLocation;
    protected String type;
}
