package sptech.com.Model.Componentes;

import com.github.britooo.looca.api.core.Looca;

public class Cpu {
    Looca looca = new Looca();
    String cpuFabricante = looca.getProcessador().getFabricante();
    String cpuId = looca.getProcessador().getId();
    String cpuIdentificador = looca.getProcessador().getIdentificador();
    String cpuNome = looca.getProcessador().getNome();
    String cpuMicro = looca.getProcessador().getMicroarquitetura();
    Long cpuFreq = looca.getProcessador().getFrequencia();
    Integer cpuFisicas = looca.getProcessador().getNumeroCpusFisicas();
    Integer cpuLogicas = looca.getProcessador().getNumeroCpusLogicas();
    Double cpuUso = looca.getProcessador().getUso();

    public String exibeCpu() {
        String cpuInfo = String.format("""
        Fabricante: %s
        Id: %s
        Identificador: %s
        Nome: %s
        Micro-arquitetura: %s
        Frequência: %s
        Cpus Físicas: %d
        Cpus Lógicas: %d
        Uso da Cpu: %.2f""",
                cpuFabricante, cpuId, cpuIdentificador, cpuNome, cpuMicro, cpuFreq, cpuFisicas, cpuLogicas, cpuUso);
        return cpuInfo;
    }
}
