package sptech.api.model.componente;

import com.github.britooo.looca.api.core.Looca;

public class MonitoramentoCPU extends Hardware {
    private Looca looca = new Looca();
    private String cpuId = looca.getProcessador().getId();
    private Long cpuFreq = looca.getProcessador().getFrequencia(); // Em Hz
    private Double cpuUso = looca.getProcessador().getUso();

    public MonitoramentoCPU(String nomeHardware, String unidadeDeMedida, Double medida, String descricaoHardware) {
        super(nomeHardware, unidadeDeMedida, medida, descricaoHardware);
    }

    public MonitoramentoCPU() {
    }

    public double getCpuFreqGHz() {
        return cpuFreq / 1_000_000_000.0; // Converte de Hz para GHz
    }

    public double getCpuUsoGHz() {
        double cpuFreqGHz = getCpuFreqGHz();
        return (cpuFreqGHz * cpuUso) / 100; // Calcula o uso em GHz baseado na frequência máxima
    }

    public String getIdCPU(){
        return cpuId;
    }

}
