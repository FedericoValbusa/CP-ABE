import it.unisa.dia.gas.jpbc.Element;

public class SecK {
    public Element d0;
    public Element[] d;

    public SecK(Element d0, Element[] d){
        this.d0 = d0;
        this.d = d;
    }

    public Element getdo(){
        return this.d0;
    }

    public Element[] getd(){
        return this.d;
    }
}
