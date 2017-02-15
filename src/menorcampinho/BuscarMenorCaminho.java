package menorcampinho;

import java.util.ArrayList;

public class BuscarMenorCaminho
{

    private String AltoParaisoDeGoias = "Alto Paraiso de Goiás";
    private String Anapolis = "Anápolis";
    private String Barreiras = "Barreiras";
    private String BeloHorizonte = "Belo Horizonte";
    private String Brasilia = "Brasilia";
    private String BuritiAlegre = "Buriti Alegre";
    private String CaldasNovas = "Caldas Novas";
    private String Catalao = "Catalão";
    private String Cavalcante = "Cavalcante";
    private String ColinasDoSul = "Colinas do Sul";
    private String CorumbaDeGoias = "Corumba de Goias";
    private String Cristalina = "Cristalina";
    private String Formosa = "Formosa";
    private String Pirenopolis = "Pirenópolis";
    private String Goiania = "Goiânia";
    private String Ipameri = "Ipameri";
    private String Luziania = "Luziânia";
    private String Morrinhos = "Morrinhos";
    private String PQNacionalChapadaDosVeadeiros = "PQ. Nacional Chapada dos Veadeiros";
    private String Piracanjuba = "Piracanjuba";
    private String PiresDoRio = "Pires do Rio";
    private String Porungatu = "Porungatu";
    private String Posse = "Posse";
    private String RioQuente = "Rio Quente";
    private String RioVerde = "Rio Verde";
    private String SaltoDoItiringa = "Salto do Itiringa";
    private String SaoJorge = "São Jorge";
    private String Vianopolis = "Vianópolis";
    private String cidadeInicial = AltoParaisoDeGoias;
    private ArrayList<CidadeVisitada> caminhosCompletos = new ArrayList<CidadeVisitada>();

    private ArrayList<Cidade> cidades = new ArrayList<Cidade>();

    public StringBuilder buscarMenorCaminho()
    {
        caminhosCompletos.clear();
        StringBuilder resultado = new StringBuilder();
        for (Cidade umaCidade : cidades)
        {
            if (umaCidade.getNome().equals(cidadeInicial))
            {
                testaNovosCaminhos(new CidadeVisitada(umaCidade.getNome(), null, 0));
            }
        }
        resultado.append("Caminhos completos: '" + caminhosCompletos.size() + "'\n");
        int menorCaminho = Integer.MAX_VALUE;
        CidadeVisitada menorPercurso = null;

        for (CidadeVisitada cidadeVisitada : caminhosCompletos)
        {
            if (getQuantidadeDeCidadesVisitadas(cidadeVisitada) > cidades.size())
            {
                if (calcularDistanciaDoPercurso(cidadeVisitada) < menorCaminho)
                {
                    menorCaminho = calcularDistanciaDoPercurso(cidadeVisitada);
                    menorPercurso = cidadeVisitada;
                }
                resultado.append("\nInício... " + imprimirCaminho(cidadeVisitada) + "\n");
                resultado.append("Distância do Percurso: " + calcularDistanciaDoPercurso(cidadeVisitada) + "\n");
                resultado.append("N° de cidades visitadas: '" + (getQuantidadeDeCidadesVisitadas(cidadeVisitada) - 1) + "'\n");
            }
        }
        resultado.append("\n *************Menor Caminho Encontrado*************\n");
        resultado.append("\nInício... " + imprimirCaminho(menorPercurso) + "\n");
        resultado.append("Distância do Percurso: " + calcularDistanciaDoPercurso(menorPercurso) + "\n");
        resultado.append("\n **************************************************\n");
        return resultado;
    }

    public ArrayList<Cidade> getCidades()
    {
        return cidades;
    }

    public void setCidadeInicial(String novaCidadeInicial)
    {
        this.cidadeInicial = novaCidadeInicial;
    }

    public int calcularDistanciaDoPercurso(CidadeVisitada umaCidadeVisitada)
    {
        if (umaCidadeVisitada.cidadeAnterior == null)
        {
            return umaCidadeVisitada.distanciaDaCidadeAnteriorAtehAqui;
        } else
        {
            return umaCidadeVisitada.distanciaDaCidadeAnteriorAtehAqui + calcularDistanciaDoPercurso(umaCidadeVisitada.cidadeAnterior);
        }
    }

    public void testaNovosCaminhos(CidadeVisitada umaCidadeVisitada)
    {
        for (Cidade umaCidade : cidades)
        {
            if (umaCidade.getNome().equals(umaCidadeVisitada.getCidadeAtual()))
            {
                if (umaCidade.getCidadesVisinhas().size() > 0)
                {
                    umaCidadeVisitada.addCidadePorVisitar(umaCidade.getCidadesVisinhas());
                    for (CidadeVisinha umaCidadeVisinha : umaCidade.getCidadesVisinhas())
                    {
                        if (umaCidadeVisinha.getCidade().getNome().equalsIgnoreCase(cidadeInicial) && getQuantidadeDeCidadesVisitadas(umaCidadeVisitada) >= cidades.size())//Completou o ciclo
                        {
//                            caminhosCompletos.add(umaCidadeVisitada);
                            caminhosCompletos.add(new CidadeVisitada(umaCidadeVisinha.getCidade().getNome(), umaCidadeVisitada, umaCidadeVisinha.distanciaDaCidadeAnteriorAtehAqui));
                        } else if (!isCidadeVisitada(umaCidadeVisitada, umaCidadeVisinha.getCidade().getNome()))
                        {
                            testaNovosCaminhos(new CidadeVisitada(umaCidadeVisinha.getCidade().getNome(), umaCidadeVisitada, umaCidadeVisinha.distanciaDaCidadeAnteriorAtehAqui));
                        }
                    }
                }
            }
        }
    }

    public boolean isCidadeVisitada(CidadeVisitada cidadeAtual, String cidadeAVisitar)
    {

        if (cidadeAtual.cidadeAnterior == null)
        {
            return false;
        } else
        {
            if (cidadeAtual.getCidadeAnterior().getCidadeAtual().equalsIgnoreCase(cidadeAVisitar))
            {
                return true;
            } else
            {
                return isCidadeVisitada(cidadeAtual.getCidadeAnterior(), cidadeAVisitar);
            }
        }
    }

    public String imprimirCaminho(CidadeVisitada cidadeAtual)
    {
        if (cidadeAtual.cidadeAnterior == null)
        {
//            System.out.print(" '"+cidadeAtual.cidadeAtual+"' ...Fim!\n");
            return " '" + cidadeAtual.cidadeAtual + "' ...Fim!\n ";
        } else
        {
//            System.out.print("<< '"+cidadeAtual.cidadeAtual+"' = '"+cidadeAtual.distanciaDaCidadeAnteriorAtehAqui+"' ");
            return (" '" + cidadeAtual.cidadeAtual + "' = '" + cidadeAtual.distanciaDaCidadeAnteriorAtehAqui + "' >> ") + imprimirCaminho(cidadeAtual.getCidadeAnterior());
        }
    }

    public int getQuantidadeDeCidadesVisitadas(CidadeVisitada cidadeAtual)
    {
        int cidadesVisitadas = 1;
        if (cidadeAtual.cidadeAnterior == null)
        {

        } else
        {
            cidadesVisitadas += getQuantidadeDeCidadesVisitadas(cidadeAtual.getCidadeAnterior());
        }
        return cidadesVisitadas;

    }

    public void carregarDados()
    {
        criarCidades();
        criarCaminhos();
    }

    public void criarCidades()
    {
        cidades.add(new Cidade(AltoParaisoDeGoias));
        cidades.add(new Cidade(Anapolis));
//        cidades.add(new Cidade(Barreiras));
//        cidades.add(new Cidade(BeloHorizonte));
        cidades.add(new Cidade(Brasilia));
//        cidades.add(new Cidade(BuritiAlegre));
//        cidades.add(new Cidade(CaldasNovas));
//        cidades.add(new Cidade(Catalao));
//        cidades.add(new Cidade(Cavalcante));
//        cidades.add(new Cidade(ColinasDoSul));
        cidades.add(new Cidade(CorumbaDeGoias));
//        cidades.add(new Cidade(Cristalina));
        cidades.add(new Cidade(Formosa));
        cidades.add(new Cidade(Goiania));
//        cidades.add(new Cidade(Ipameri));
        cidades.add(new Cidade(Luziania));
//        cidades.add(new Cidade(Morrinhos));
//        cidades.add(new Cidade(PQNacionalChapadaDosVeadeiros));
        cidades.add(new Cidade(Piracanjuba));
        cidades.add(new Cidade(Pirenopolis));
        cidades.add(new Cidade(PiresDoRio));
//        cidades.add(new Cidade(Porungatu));
//        cidades.add(new Cidade(Posse));
//        cidades.add(new Cidade(RioQuente));
//        cidades.add(new Cidade(RioVerde));
//        cidades.add(new Cidade(SaltoDoItiringa));
//        cidades.add(new Cidade(SaoJorge));
        cidades.add(new Cidade(Vianopolis));
    }

    public void criarCaminhos()
    {
        addCaminho(AltoParaisoDeGoias, Brasilia, 222);
        addCaminho(Brasilia, AltoParaisoDeGoias, 222);
        addCaminho(AltoParaisoDeGoias, Pirenopolis, (172 + 50 + 33 + 89 + 16));
        addCaminho(Pirenopolis, AltoParaisoDeGoias, (172 + 50 + 33 + 89 + 16));
        addCaminho(AltoParaisoDeGoias, Formosa, (172 + 27));
        addCaminho(Formosa, AltoParaisoDeGoias, (172 + 27));
        addCaminho(AltoParaisoDeGoias, CorumbaDeGoias, (172 + 50 + 33 + 89));
        addCaminho(CorumbaDeGoias, AltoParaisoDeGoias, (172 + 50 + 33 + 89));
//        addCaminho(AltoParaisoDeGoias, ColinasDoSul, (36 + 36));
//        addCaminho(ColinasDoSul, AltoParaisoDeGoias, (36 + 36));
//        addCaminho(AltoParaisoDeGoias, PQNacionalChapadaDosVeadeiros, (36));
//        addCaminho(PQNacionalChapadaDosVeadeiros, AltoParaisoDeGoias, (36));
        addCaminho(CorumbaDeGoias, Anapolis, (46));
        addCaminho(Formosa, Brasilia, (27 + 50));
        addCaminho(Brasilia, Formosa, (27 + 50));
        addCaminho(Formosa, CorumbaDeGoias, (27 + 50 + 33 + 89));
        addCaminho(CorumbaDeGoias, Formosa, (27 + 50 + 33 + 89));
        addCaminho(Anapolis, CorumbaDeGoias, (46));
        addCaminho(Pirenopolis, Anapolis, (30 + 33));
        addCaminho(Anapolis, Pirenopolis, (30 + 33));
        addCaminho(Pirenopolis, CorumbaDeGoias, (16));
        addCaminho(CorumbaDeGoias, Pirenopolis, (16));
        addCaminho(Pirenopolis, Brasilia, (33 + 89 + 16));
        addCaminho(Brasilia, Pirenopolis, (33 + 89 + 16));
        addCaminho(Anapolis, Brasilia, (116 + 26));
        addCaminho(Brasilia, Anapolis, (116 + 26));
//        addCaminho(Barreiras, Formosa, (570));
//        addCaminho(Formosa, Barreiras, (570));
//        addCaminho(Barreiras, Posse, (320));
//        addCaminho(Posse, Barreiras, (320));
//        addCaminho(BeloHorizonte, Cristalina, (607));
//        addCaminho(Cristalina, BeloHorizonte, (607));
//        addCaminho(BuritiAlegre, CaldasNovas, (86));
//        addCaminho(CaldasNovas, BuritiAlegre, (86));
//        addCaminho(BuritiAlegre, Morrinhos, (73));
//        addCaminho(Morrinhos, BuritiAlegre, (73));
//        addCaminho(CaldasNovas, Catalao, (97));
//        addCaminho(Catalao, CaldasNovas, (97));
//        addCaminho(CaldasNovas, Goiania, (356));
//        addCaminho(Goiania, CaldasNovas, (356));
        addCaminho(Goiania, Anapolis, (54));
        addCaminho(Anapolis, Goiania, (54));
//        addCaminho(CaldasNovas, Ipameri, (61));
//        addCaminho(Ipameri, CaldasNovas, (61));
//        addCaminho(CaldasNovas, Piracanjuba, (79));
//        addCaminho(Piracanjuba, CaldasNovas, (79));
//        addCaminho(CaldasNovas, PiresDoRio, (75));
//        addCaminho(PiresDoRio, CaldasNovas, (75));
//        addCaminho(CaldasNovas, RioQuente, (28));
//        addCaminho(RioQuente, CaldasNovas, (28));
//        addCaminho(CaldasNovas, Vianopolis, (132));
//        addCaminho(Vianopolis, CaldasNovas, (132));
//        addCaminho(Catalao, Cristalina, (239));
//        addCaminho(Cristalina, Catalao, (239));
//        addCaminho(Catalao, Ipameri, (53));
//        addCaminho(Ipameri, Catalao, (53));
//        addCaminho(Cavalcante, PQNacionalChapadaDosVeadeiros, (23));
//        addCaminho(PQNacionalChapadaDosVeadeiros, Catalao, (23));
//        addCaminho(ColinasDoSul, SaoJorge, (36));
//        addCaminho(SaoJorge, ColinasDoSul, (36));
//        addCaminho(Ipameri, Cristalina, (185));
//        addCaminho(Cristalina, Ipameri, (185));
//        addCaminho(Luziania, Cristalina, (73));
//        addCaminho(Cristalina, Luziania, (73));
//        addCaminho(PiresDoRio, Cristalina, (140));
//        addCaminho(Cristalina, PiresDoRio, (140));
//        addCaminho(Formosa, Posse, (250));
//        addCaminho(Posse, Formosa, (250));
//        addCaminho(Formosa, SaltoDoItiringa, (36));
//        addCaminho(SaltoDoItiringa, Formosa, (36));
//        addCaminho(Goiania, RioVerde, (235));
//        addCaminho(RioVerde, Goiania, (235));
//        addCaminho(Goiania, Morrinhos, (127));
//        addCaminho(Morrinhos, Goiania, (127));
        addCaminho(Goiania, Piracanjuba, (62 + 28));
        addCaminho(Piracanjuba, Goiania, (62 + 28));
        addCaminho(Goiania, Piracanjuba, (52 + 36));
        addCaminho(Piracanjuba, Goiania, (52 + 36));
        addCaminho(Goiania, Piracanjuba, (52 + 33 + 23 + 30));
        addCaminho(Piracanjuba, Goiania, (52 + 33 + 23 + 30));
        addCaminho(Goiania, PiresDoRio, (52 + 33 + 52));
        addCaminho(PiresDoRio, Goiania, (52 + 33 + 52));
//        addCaminho(Goiania, RioQuente, (157));
//        addCaminho(RioQuente, Goiania, (157));
        addCaminho(Goiania, Vianopolis, (52 + 33 + 60));
        addCaminho(Vianopolis, Goiania, (52 + 33 + 60));
        addCaminho(Goiania, Vianopolis, (54 + 79));
        addCaminho(Vianopolis, Goiania, (54 + 79));
//        addCaminho(Ipameri, PiresDoRio, (57));
//        addCaminho(PiresDoRio, Ipameri, (57));
        addCaminho(Luziania, Vianopolis, (102));
        addCaminho(Vianopolis, Luziania, (102));
        addCaminho(Vianopolis, Anapolis, (79));
        addCaminho(Anapolis, Vianopolis, (79));
        addCaminho(Brasilia, Luziania, (55));
        addCaminho(Luziania, Brasilia, (55));
//        addCaminho(Morrinhos, Piracanjuba, (93));
//        addCaminho(Piracanjuba, Morrinhos, (93));
//        addCaminho(Morrinhos, RioQuente, (55));
//        addCaminho(RioQuente, Morrinhos, (55));
        addCaminho(Piracanjuba, PiresDoRio, (30 + 23 + 52));
        addCaminho(PiresDoRio, Piracanjuba, (30 + 23 + 52));
        addCaminho(Piracanjuba, PiresDoRio, (33 + 36 + 52));
        addCaminho(PiresDoRio, Piracanjuba, (33 + 36 + 52));
        addCaminho(Vianopolis, PiresDoRio, (83));
        addCaminho(PiresDoRio, Vianopolis, (83));
//        addCaminho(Piracanjuba, RioQuente, (38));
//        addCaminho(RioQuente, Piracanjuba, (38));
        addCaminho(Piracanjuba, Vianopolis, (30 + 23 + 60));
        addCaminho(Vianopolis, Piracanjuba, (30 + 23 + 60));
        addCaminho(Piracanjuba, Vianopolis, (33 + 36 + 60));
        addCaminho(Vianopolis, Piracanjuba, (33 + 36 + 60));
//        addCaminho(Pirenopolis, Porungatu, (372));
//        addCaminho(Porungatu, Pirenopolis, (372));
//        addCaminho(PiresDoRio, Vianopolis, (83));
//        addCaminho(Vianopolis, PiresDoRio, (83));
//        addCaminho(RioQuente, Vianopolis, (121));
//        addCaminho(Vianopolis, RioQuente, (121));
    }

    public void addCaminho(String cidadeOrigem, String cidadeDestino, int distanciaEntreAsCidades)
    {
        boolean encontrouCidadeOrigem = false;
        boolean encontrouCidadeDestino = false;
        for (Cidade umaCidadeOrigem : cidades)
        {
            if (umaCidadeOrigem.getNome().equalsIgnoreCase(cidadeOrigem))
            {
                encontrouCidadeOrigem = true;
                for (Cidade umaCidadeDestino : cidades)
                {
                    if (umaCidadeDestino.getNome().equalsIgnoreCase(cidadeDestino))
                    {
                        encontrouCidadeDestino = true;
                        umaCidadeOrigem.addCidadeVisinha(new CidadeVisinha(umaCidadeDestino, umaCidadeOrigem, distanciaEntreAsCidades));
                        break;
                    }
                }
                break;
            }
        }
        if (!encontrouCidadeOrigem)
        {
            System.out.print("Cidade de Origem nao encontrada: '" + cidadeOrigem + "'");
        }
        if (!encontrouCidadeDestino)
        {
            System.out.print("Cidade de Destino nao encontrada: '" + cidadeDestino + "'");
        }
    }
}
