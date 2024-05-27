package sptech.api.model.componente;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;

public class MonitoramentoMemoria extends Hardware{

    private Looca looca = new Looca();
    private Memoria memoria = looca.getMemoria();

    public MonitoramentoMemoria(String nomeHardware, String unidadeDeMedida, Double medida, String descricaoHardware) {
        super(nomeHardware, unidadeDeMedida, medida, descricaoHardware);
    }

    public MonitoramentoMemoria() {
    }

    public double getMemoriaEmUsoGB() {
        return memoria.getEmUso() / (1024.0 * 1024.0 * 1024.0);
    }

    public void exibeMemoria() {
        System.out.printf("Memória em Uso: %.2f GB%n", getMemoriaEmUsoGB());
    }
}
