package dados;

public enum Categoria {
    ACA("Acao"),
    DRA("Drama"),
    FIC("Ficcao"),
    ROM("Romance");
    private String nome;
    private Categoria(String nome){
        this.nome=nome;
    }
    public String getNome() {
        return nome;
    }
    public static Categoria categoriaString(String nome) {
        if (nome.equals("Acao")) {
            return ACA;
        }
        else if (nome.equals("Drama")) {
            return DRA;
        }
        else if (nome.equals("Ficcao")) {
            return FIC;
        }
        else if (nome.equals("Romance")){
            return ROM;
        }
        else {
            return null;}

    }

}
