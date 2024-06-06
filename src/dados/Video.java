package dados;

public class Video extends Midia{
    private int qualidade;
    public Video(int codigo, String titulo, int ano, Categoria categoria, int qualidade) {
        super(codigo, titulo, ano, categoria);
        this.qualidade=qualidade;
    }
    public int getQualidade() {
        return qualidade;
    }

    @Override
    public double calculaLocacao() {
        double locacao = 0;

        if (getAno() == 2024){
            locacao+= 20;
        }
        else if (getAno()>=2000|| getAno()<=2023) {
            locacao+= 15;
        }
        else
            locacao+= 10;
            return locacao;
        }

    @Override
    public String toString() {
        return super.toString()+ qualidade+","+"R$ "+String.format("%.2f",calculaLocacao());
    }
}
