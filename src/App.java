import java.math.BigInteger;

import javax.swing.text.html.parser.Element;

import it.unisa.*;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
public class App {

    MPubK pubKey;
    Pairing pairing; 

    //dire alle altre che usiamo solo la curva con embedding degree 12
    //bisogna anche portare tutta la cartella jpbc dentro al progetto
    public void Setup(){
        //PairingFactory.getInstance().setUsePBCWhenPossible(true); //per usare PBC wrapper e aumentare le performance
        this.pairing = PairingFactory.getPairing("a_181_603.properties");
        Field G1 = pairing.getG1();
        Field GT = pairing.getGT();


        
    }





    //a is symm

    public static void main(String[] args){
    
   PairingFactory.getInstance().setUsePBCWhenPossible(true);
   Pairing pairing = PairingFactory.getPairing("a_181_603.properties");
   int degree = pairing.getDegree();
   System.out.println(pairing.isSymmetric());
   System.out.println("degree = "+ degree);

    /* Return Zr */
    Field Zr = pairing.getZr();

    /* Return G1 */
    Field G1 = pairing.getG1();

    /* Return G2 */
    Field G2 = pairing.getG2();

    /* Return GT */
    Field GT = pairing.getGT();

    BigInteger b1 = G1.getOrder();
    BigInteger b2 = G2.getOrder();
    it.unisa.dia.gas.jpbc.Element e = G1.newElement(degree);
    System.out.println("l'elemento generato è: "+e);
    System.out.println(G1.equals(G2));
    System.out.println(b1.equals(b2));
    System.out.println("I tre gruppi sono: "+G1.toString()+" ,"+G2.toString()+" "+GT.toString());
    

    System.out.println();

    it.unisa.dia.gas.jpbc.Element u = G1.newRandomElement(); 
    it.unisa.dia.gas.jpbc.Element v = G2.newRandomElement();

    it.unisa.dia.gas.jpbc.Element e1 = pairing.pairing(u, v); //pairing can be computed

    System.out.println("Element u = "+ u);
    System.out.println("Element v = "+ v);
    System.out.println("pairing e(u,v) = "+ e1);


    //CHECK BILINEARITY PROPERTY OF BILINEAR-PAIRING
    // e( u^a , v^b ) = e(u,v)^(a*b)
    it.unisa.dia.gas.jpbc.Element a = Zr.newRandomElement();
    it.unisa.dia.gas.jpbc.Element b = Zr.newRandomElement();

    System.out.println("Element a = "+ a);
    System.out.println("Element b = "+ b);

    it.unisa.dia.gas.jpbc.Element LHS = pairing.pairing(u.powZn(a), v.powZn(b));
    it.unisa.dia.gas.jpbc.Element RHS = e1.powZn(a.mul(b));

    System.out.println("LHS = "+LHS);
    System.out.println("RHS = "+RHS);

    if(LHS.isEqual(RHS))
    {
        System.out.println("BILINEAR");
    }
    else
    {
        System.out.println("NOT BILINEAR");
    }
}
}