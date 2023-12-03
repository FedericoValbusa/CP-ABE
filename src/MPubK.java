import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;

public class MPubK {
    public Pairing pairing;
    public Element generator;
    public Element y;
    public Element[] list;
    
    public MPubK(Pairing p, it.unisa.dia.gas.jpbc.Element g, it.unisa.dia.gas.jpbc.Element y2, it.unisa.dia.gas.jpbc.Element[] l){
        this.pairing=p;
        this.generator=g;
        this.list=l;
        this.y=y2;

    } 

    public Pairing getPairing(){
        return this.pairing;
    }

    public Element getGenerator(){
        return this.generator;
    }

    public Element gety(){
        return this.y;
    }

    public Element[] getList(){
        return this.list;
    }
}
