package app;

import dados.Categoria;
import dados.Midia;
import dados.Midiateca;
import dados.Video;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;

public class ACMEMidia {
    Midiateca midiateca= new Midiateca();
    private Categoria categoria;
    private Scanner entrada;
    private PrintStream saidaPadrao = System.out;

    public ACMEMidia(){
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader("entrada.txt"));
            entrada = new Scanner(streamEntrada);
            entrada.useDelimiter("[;\\n]");
            PrintStream streamSaida = new PrintStream(new File("dadosout.txt"), StandardCharsets.UTF_8);
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

    }
    public void mostrarDadosMidia(){

    }
    public void mostrarDadosMidiaPorCategoria(){

    }
    public void mostrarDadosVideoPorQualidade(){

    }
    public void mostarDadosMusicaPorDuracao(){

    }
    public void removerMidia(){

    }
    public void somatorioTotalMidia(){

    }
}
