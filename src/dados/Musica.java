package dados;

public class Musica extends Midia{
    private double duracao;
    private Categoria categoria;
    public Musica(int codigo, String titulo, int ano, Categoria categoria, double duracao) {
        super(codigo, titulo, ano, categoria);
        this.duracao = duracao;
    }
    public double getDuracao() {
        return duracao;
    }

    @Override
    public double calculaLocacao() {
        double preco =0;
        if (categoria == Categoria.ACA){
            preco =duracao* 0.90;
        }
        else if (categoria == Categoria.DRA){
            preco = duracao* 0.70;
        }
        else if (categoria == Categoria.FIC){
            preco = duracao* 0.50;
        }
        else if (categoria== Categoria.ROM){
            preco = duracao*0.30;
        }
        return preco;
    }
}
