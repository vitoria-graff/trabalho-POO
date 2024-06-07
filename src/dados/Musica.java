package dados;

public class Musica extends Midia{
    private final double duracao;
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
        double preco = 0;
        if (getCategoria() == Categoria.ACA) {
            preco = duracao * 0.90;
        } else if (getCategoria()== Categoria.DRA) {
            preco = duracao * 0.70;
        } else if (getCategoria() == Categoria.FIC) {
            preco = duracao * 0.50;
        } else if (getCategoria() == Categoria.ROM) {
            preco = duracao * 0.30;
        }
        return preco;
    }

    @Override
    public String toString() {
        return super.toString() +String.format("%.2f",duracao)+ ","+String.format("%.2f",calculaLocacao());
    }
}
