import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;

public class MPubK {
    public Pairing pairing;
    public it.unisa.dia.gas.jpbc.Element generator;
    public it.unisa.dia.gas.jpbc.Element y;
    public it.unisa.dia.gas.jpbc.Element[] list;
    
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
