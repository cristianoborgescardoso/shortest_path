package menorcampinho;

import java.util.ArrayList;

public class CidadeVisitada
{

    String cidadeAtual;
    CidadeVisitada cidadeAnterior;
    int distanciaDaCidadeAnteriorAtehAqui;
    private ArrayList<CidadeVisitada> cidadesPorVisitar = new ArrayList<CidadeVisitada>();

    public CidadeVisitada(String cidadeAtual, CidadeVisitada cidadeAnterior, int distanciaDaCidadeAnteriorAtehAqui)
    {
        this.cidadeAtual = cidadeAtual;
        this.cidadeAnterior = cidadeAnterior;
        this.distanciaDaCidadeAnteriorAtehAqui = distanciaDaCidadeAnteriorAtehAqui;
    }

    public CidadeVisitada getCidadeAnterior()
    {
        return cidadeAnterior;
    }

    public void setCidadeAnterior(CidadeVisitada cidadeAnterior)
    {
        this.cidadeAnterior = cidadeAnterior;
    }

    public String getCidadeAtual()
    {
        return cidadeAtual;
    }

    public void setCidadeAtual(String cidadeAtual)
    {
        this.cidadeAtual = cidadeAtual;
    }

    public ArrayList<CidadeVisitada> getCidadesPorVisitar()
    {
        return cidadesPorVisitar;
    }

    public void setCidadesPorVisitar(ArrayList<CidadeVisitada> cidadesPorVisitar)
    {
        this.cidadesPorVisitar = cidadesPorVisitar;
    }

    public int getDistanciaDaCidadeAnteriorAtehAqui()
    {
        return distanciaDaCidadeAnteriorAtehAqui;
    }

    public void setDistanciaDaCidadeAnteriorAtehAqui(int distanciaDaCidadeAnteriorAtehAqui)
    {
        this.distanciaDaCidadeAnteriorAtehAqui = distanciaDaCidadeAnteriorAtehAqui;
    }

    public void addCidadePorVisitar(CidadeVisitada cidadeVisitada)
    {
        this.cidadesPorVisitar.add(cidadeVisitada);
    }

    public void addCidadePorVisitar(ArrayList<CidadeVisinha> cidadesVisinhas)
    {
        for (CidadeVisinha umaCidadeVisinha : cidadesVisinhas)
        {
            this.cidadesPorVisitar.add(new CidadeVisitada(umaCidadeVisinha.getCidade().getNome(), this, umaCidadeVisinha.distanciaDaCidadeAnteriorAtehAqui));
        }
    }
}
