package sptech.api.model.componente;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import com.github.britooo.looca.api.group.rede.RedeParametros;

import java.util.List;

public class MonitoramentoRede extends Hardware{
    Looca looca = new Looca();
    String rede = looca.getRede().getGrupoDeInterfaces().getInterfaces().toString();
    RedeParametros redeParametros = looca.getRede().getParametros();
    List<RedeInterface> interfaces = looca.getRede().getGrupoDeInterfaces().getInterfaces();

    public MonitoramentoRede() {
    }

    public String exibeRede() {
        return rede;
    }

    public RedeParametros exibeRedeP() {
        return redeParametros;
    }
}
