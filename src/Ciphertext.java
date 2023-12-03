import it.unisa.dia.gas.jpbc.Element;

public class Ciphertext {
   
    Element c0;
    Element c1;
    
    //La nostra policy è una lista di interi. In particolare un intero i è nella
    //lista se per decifrare un elemento serve l'attributo i-esimo
    int[] attributeList;
    Element[] list;

    public Ciphertext(Element c0, Element c1, int[] attributeList, Element[] list){
        this.c0 = c0;
        this.c1 = c1;
        this.attributeList = attributeList;
        this.list = list;
    }
}
