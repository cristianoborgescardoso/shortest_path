package menorcampinho;

public class CidadeVisinha
{

    Cidade cidade;
    Cidade cidadeAnterior = null;
    int distanciaDaCidadeAnteriorAtehAqui;

    public CidadeVisinha(Cidade cidade, Cidade cidadeAnterior, int distanciaDaCidadeAnteriorAtehAqui)
    {
        this.cidade = cidade;
        this.cidadeAnterior = cidadeAnterior;
        this.distanciaDaCidadeAnteriorAtehAqui = distanciaDaCidadeAnteriorAtehAqui;
    }

    public Cidade getCidade()
    {
        return cidade;
    }

    public void setCidade(Cidade cidade)
    {
        this.cidade = cidade;
    }

    public Cidade getCidadeAnterior()
    {
        return cidadeAnterior;
    }

    public void setCidadeAnterior(Cidade cidadeAnterior)
    {
        this.cidadeAnterior = cidadeAnterior;
    }

    public int getDistanciaDaCidadeAnteriorAtehAqui()
    {
        return distanciaDaCidadeAnteriorAtehAqui;
    }

    public void setDistanciaDaCidadeAnteriorAtehAqui(int distanciaDaCidadeAnteriorAtehAqui)
    {
        this.distanciaDaCidadeAnteriorAtehAqui = distanciaDaCidadeAnteriorAtehAqui;
    }
}
