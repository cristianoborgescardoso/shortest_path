package menorcampinho;

import java.util.ArrayList;

public class Cidade
{

    private String nome;
    private ArrayList<CidadeVisinha> cidadesVisinhas = new ArrayList<CidadeVisinha>();

    public Cidade(String nome)
    {
        this.nome = nome;
    }

    public ArrayList<CidadeVisinha> getCidadesVisinhas()
    {
        return cidadesVisinhas;
    }

    public void setCidadesVisinhas(ArrayList<CidadeVisinha> cidadesVisinhas)
    {
        this.cidadesVisinhas = cidadesVisinhas;
    }

    public void addCidadeVisinha(CidadeVisinha cidadeVisinha)
    {
        this.cidadesVisinhas.add(cidadeVisinha);
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }
}
