package sptech.com.Model.Componentes;

import com.github.britooo.looca.api.core.Looca;

public class Memoria {
    Looca looca = new Looca();
    com.github.britooo.looca.api.group.memoria.Memoria memoria = looca.getMemoria();

    public com.github.britooo.looca.api.group.memoria.Memoria exibeMemoria() {
        return memoria;
    }
}
