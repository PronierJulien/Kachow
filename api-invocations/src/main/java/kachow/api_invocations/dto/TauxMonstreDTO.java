package kachow.api_invocations.dto;

public class TauxMonstreDTO {
    private MonstreInvocDTO monstreInvocDTO;
    private double tauxInvocation;

    public MonstreInvocDTO getMonstreInvoc() {
        return this.monstreInvocDTO;
    }

    public void setMonstreInvocDTO(MonstreInvocDTO monstreInvocDTO) {
        this.monstreInvocDTO = monstreInvocDTO;
    }

    public double getTauxInvocation() {
        return this.tauxInvocation;
    }

    public void setTauxInvocation(double tauxInvocation) {
        this.tauxInvocation = tauxInvocation;
    }
}