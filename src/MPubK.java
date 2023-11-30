import javax.swing.text.html.parser.Element;

import it.unisa.*;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class MPubK {
    public Pairing pairing;
    public it.unisa.dia.gas.jpbc.Element generator;
    public it.unisa.dia.gas.jpbc.Element y;
    public Element[] list;
    
    public MPubK(Pairing p, it.unisa.dia.gas.jpbc.Element g, it.unisa.dia.gas.jpbc.Element y2, Element[] l){
        this.pairing=p;
        this.generator=g;
        this.list=l;
        this.y=y2;

    } 
}
