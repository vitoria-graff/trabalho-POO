package dados;

import java.util.ArrayList;

public class Midiateca {
    private int contador;
    private ArrayList<Midia>midias;
    public Midiateca(){
        contador=0;
        midias=new ArrayList<>();
    }
    public boolean cadastraMidia(Midia midia){

        return midias.add(midia);
    }
    public Midia consultaPorCodigo(int codigo){
        for(Midia midia  :midias ) {
            if(midia.getCodigo() == codigo)
                return midia ;
        }
        return null;
    }
    public ArrayList<Midia> consultaPorCategoria(Categoria categoria){
        ArrayList<Midia> midiasPorCat =new ArrayList<>();
        for (Midia midia: midias){
            if (midia.getCategoria()== categoria){
                midiasPorCat.add(midia);
            }
        }
        return midiasPorCat;
    }
    public boolean removeMidia(int codigo){
        for (Midia midia: midias){
            if (midia.getCodigo()==codigo){
                midias.remove(midia);
                return true;
            }
        }
        return false;
    }
}
