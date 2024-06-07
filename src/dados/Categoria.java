package dados;

public enum Categoria {
    ACA("Acao"),
    DRA("Drama"),
    FIC("Ficcao"),
    ROM("Romance");
    private final String nome;
    Categoria(String nome){
        this.nome=nome;
    }
    public String getNome() {
        return nome;
    }
}
