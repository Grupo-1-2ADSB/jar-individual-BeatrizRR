package sptech.com.Model.Componentes;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.RedeParametros;

public class Rede {
    Looca looca = new Looca();
    String rede = looca.getRede().getGrupoDeInterfaces().getInterfaces().toString();
    RedeParametros redeParametros = looca.getRede().getParametros();

    public String exibeRede() {
        return rede;
    }

    public RedeParametros exibeRedeP() {
        return redeParametros;
    }
}
