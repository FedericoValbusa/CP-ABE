import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;


public class App {

    MPubK pubKey;
    MSecK secKey;
    Pairing pairing;
    long order_G1;
    Element g; 
    long alpha;
    long[] t;
    // numero di attributi possibili
    int n = 3;

    public void Setup(){
        // Inizializza il pairing con una curva ellittica
        Pairing pairing = PairingFactory.getPairing("a_181_603.properties"); //abbiamo messo il file nella cartella 
        Field G1 = pairing.getG1();
        Field GT = pairing.getGT();
        order_G1 = G1.getOrder().longValue();
        System.out.println("ordine della curva G1: " + (order_G1));
        System.out.println("ordine della curva GT: " + (GT.getOrder()));
        //generatore di G1 e quindi G2
        g = G1.newRandomElement();
        while (g.isOne()) {
           g = G1.newRandomElement();
           //System.out.println("while" + g);
        }
        t = new long[n];
        it.unisa.dia.gas.jpbc.Element[] T = new it.unisa.dia.gas.jpbc.Element[n]; 
        Random rand = new SecureRandom();
        for (int i = 0; i < n; i++) {
           t[i] = rand.nextLong(order_G1 - 2) + 1;
           T[i] = g.pow(new BigInteger(String.valueOf(t[i])));
        }
        alpha = rand.nextLong(order_G1 - 2) + 1;
        it.unisa.dia.gas.jpbc.Element y1 = pairing.pairing(g, g);
        it.unisa.dia.gas.jpbc.Element y = y1.pow(new BigInteger(String.valueOf(alpha)));
        //quindi possiamo generare chiave pubblica e chiave privata
        pubKey.generator = g;
        pubKey.list = T;
        pubKey.y = y;
        pubKey.pairing = pairing;
        //e anche la chiave privata 
        secKey.alpha = alpha;
        secKey.t = t;
    }


    public secK KeyGen(MPubK m_pub_k, MSecK m_sec_k, boolean[] attributes){
        Random rand = new SecureRandom();
        long r = rand.nextLong(order_G1 - 2) + 1;
        Element d0 = g.pow(new BigInteger(String.valueOf(alpha - r)));
        int listLength = attributes.length;
        Element[] individualList = new Element[listLength];
        
        int j=0;
        for (int i=0; i<n; i++){
            if(attributes[i]){
                BigInteger expR = new BigInteger(String.valueOf(r));
                BigInteger expT = new BigInteger(String.valueOf(t[i]));
                individualList[j] = g.pow(expR.multiply(expT.modInverse(new BigInteger(String.valueOf(order_G1)))));
                j++;
            }
        }
        return new secK(d0, individualList);    
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