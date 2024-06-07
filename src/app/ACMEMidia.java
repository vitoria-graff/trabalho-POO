package app;

import dados.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ACMEMidia {
    Midiateca midiateca= new Midiateca();
    private Categoria categoria;
    private Scanner entrada;
    private final PrintStream saidaPadrao = System.out;

    public ACMEMidia(){
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader("entrada.txt"));
            entrada = new Scanner(streamEntrada);
            entrada.useDelimiter("[;\\n]");
            PrintStream streamSaida = new PrintStream(new File("saida.txt"), StandardCharsets.UTF_8);
            System.setOut(streamSaida);
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);
        entrada.useLocale(Locale.ENGLISH);
    }
    public void executa(){
        cadastrarVideo();
        cadastrarMusica();
        mostrarDadosMidia();
        mostrarDadosMidiaPorCategoria();
        mostrarDadosVideoPorQualidade();
        mostarDadosMusicaPorDuracao();
        removerMidia();
        somatorioTotalMidia();
        mostrarMusicaMaisProximaMedia();
        mostrarMidiaMaisNova();
    }
    public void cadastrarVideo(){
        while(true){
            int codigo= Integer.parseInt(entrada.nextLine());
            if(codigo== -1)
                break;
            String titulo= entrada.nextLine();
            int ano= Integer.parseInt(entrada.nextLine());
            Categoria categoria = Categoria.valueOf(entrada.nextLine());
            int qualidade = Integer.parseInt(entrada.nextLine());
            if(midiateca.consultaPorCodigo(codigo)== null){
                Midia midia = new Video(codigo,titulo,ano,categoria,qualidade);
                midiateca.cadastraMidia(midia);
                System.out.println("1:" + codigo +","+titulo+","+ano+","+categoria.getNome()+","+qualidade);
            }
            else {
                System.out.println("1:Erro-video com codigo repetido: " + codigo);

            }
        }
    }
    public void cadastrarMusica(){
        while(true){
            int codigo= Integer.parseInt(entrada.nextLine());
            if(codigo== -1)
                break;
            String titulo= entrada.nextLine();
            int ano= Integer.parseInt(entrada.nextLine());
            Categoria categoria = Categoria.valueOf(entrada.nextLine());
            double duracao = Double.parseDouble(entrada.nextLine());
            if(midiateca.consultaPorCodigo(codigo)== null){
                Midia midia = new Musica(codigo,titulo,ano,categoria,duracao);
                midiateca.cadastraMidia(midia);
                System.out.println("2:" + codigo +","+titulo+","+ano+","+categoria.getNome()+","+duracao);
            }
            else {
                System.out.println("2:Erro-musica com codigo repetido: " + codigo);

            }
        }
    }
    public void mostrarDadosMidia(){
        int codigo = Integer.parseInt(entrada.nextLine());
        Midia midia = midiateca.consultaPorCodigo(codigo);
        if (midia != null) {
            System.out.println("3:" + midia);
        }
        else {
            System.out.println("3:Código inexistente");
        }

    }
    public void mostrarDadosMidiaPorCategoria(){
        try {
            Categoria categoria = Categoria.valueOf(entrada.nextLine().trim());
            ArrayList<Midia> midias = midiateca.consultaPorCategoria(categoria);
            if (midias.isEmpty()) {
                System.out.println("4:Nenhuma midia encontrada.");
            } else {
                for (Midia midia : midias) {
                    System.out.println("4:" + midia.toString());
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("4:Nenhuma midia encontrada.");
        }
    }

    public void mostrarDadosVideoPorQualidade(){
        int qualidade = Integer.parseInt(entrada.nextLine().trim());
        ArrayList<Video>videos = new ArrayList<>();
        for (Midia midia : midiateca.getMidias()) {
            if (midia instanceof Video video) {
                if (video.getQualidade() == qualidade) {
                    videos.add(video);
                }
            }
        }
        if(videos.isEmpty()) {
            System.out.println("5:Qualidade inexistente.");
        } else{
            for(Video video :videos) {
                System.out.println("5:" +video.toString());
            }
        }
    }
    public void mostarDadosMusicaPorDuracao(){
        Musica duracao = null;
        for (Midia midia : midiateca.getMidias()) {
            if (midia instanceof Musica musica) {
                if (duracao == null || musica.getDuracao() > duracao.getDuracao()) {
                    duracao = musica;
                }
            }
        }
        if (duracao == null) {
            System.out.println("6:Nenhuma música encontrada.");
        } else {
            System.out.println("6:" +duracao.getTitulo()+ ","+String.format("%.2f",duracao.getDuracao()));
        }
    }
    public void removerMidia(){
        int codigo = Integer.parseInt(entrada.nextLine());
        Midia midia = midiateca.consultaPorCodigo(codigo);
        if (midia == null) {
            System.out.println("7:Codigo inexistente.");
        } else {
            System.out.println("7:" + midia);
            midiateca.removeMidia(codigo);
        }
    }
    public void somatorioTotalMidia(){
        double somatorioLocacoes = 0;
        for (Midia midia : midiateca.getMidias()) {
            somatorioLocacoes += midia.calculaLocacao();
        }
        if (somatorioLocacoes == 0) {
            System.out.println("8:Nenhuma midia encontrada.");
        } else {
            System.out.println("8:" + String.format("%.2f", somatorioLocacoes));
        }
    }
    public void mostrarMusicaMaisProximaMedia() {
        ArrayList<Musica>musicas =new ArrayList<>();
        double somatorio= 0;
        for (Midia midia : midiateca.getMidias()) {
            if (midia instanceof Musica musica) {
                musicas.add(musica);
                somatorio += musica.calculaLocacao();
            }
        }
        if (musicas.isEmpty()) {
            System.out.println("9:Nenhuma musica encontrada.");
        } else {
            double mediaLocacoes = somatorio / musicas.size();
            Musica musicaMaisProx= musicas.get(0);
            double diferencaMaisProx = Math.abs(musicaMaisProx.calculaLocacao() - mediaLocacoes);
            for (Musica musica : musicas) {
                double diferenca = Math.abs(musica.calculaLocacao() - mediaLocacoes);
                if (diferenca < diferencaMaisProx) {
                    musicaMaisProx = musica;
                    diferencaMaisProx = diferenca;
                }
            }
            System.out.print("9:" + String.format("%.2f",mediaLocacoes) + ",");
            System.out.println(musicaMaisProx);
        }
    }
    public void mostrarMidiaMaisNova() {
        Midia midiaMaisNova = null;
        int anoRecente = 0;
        for (Midia midia: midiateca.getMidias()) {
            if (midiaMaisNova ==null || midia.getAno()> anoRecente) {
                midiaMaisNova = midia;
                anoRecente = midia.getAno();
            }
        }
        if (midiaMaisNova == null) {
            System.out.println("10:Nenhuma midia encontrada.");
        } else {
            System.out.println("10:"+ midiaMaisNova.getCodigo() + "," + midiaMaisNova.getTitulo() + "," + midiaMaisNova.getAno());
        }
    }
}
