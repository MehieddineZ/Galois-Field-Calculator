package application;

import java.util.*;
import java.util.Scanner;

public class Polynomial {
	public int degree;   // degree of polynomial (-1 for the zero polynomial)
    public int[] coef;   // coefficients p(x) = sum {coef[i]*x^i}
    public int m;
    
    public Polynomial(int b, int m) {
        if (b < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative: " + b);
        }
        degree = b;
        coef = new int[b+1];  
        this.m=m;
    } 
    
    public Polynomial(int b, int[]a, int m) {
    	if (b < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative: " + b);
        }
    	if (a.length != (b+1))
    		throw new IllegalArgumentException("Degree error");
    	degree = b;
    	coef = new int[b+1];
        for (int i = b; i>= 0; i--) {
        	coef[i]=a[b-i];
        }
        this.m=m;
    }
    
    public Polynomial getm() {
    	Polynomial irreduciblePoly = new Polynomial(m,m);
    	if (m==163) {
    		irreduciblePoly.coef[0]=1;
    		irreduciblePoly.coef[3]=1;
    		irreduciblePoly.coef[6]=1;
    		irreduciblePoly.coef[7]=1;
    		irreduciblePoly.coef[163]=1;  
    	}
    	else if (m==8) {
    		irreduciblePoly.coef[0]=1;
    		irreduciblePoly.coef[1]=1;
    		irreduciblePoly.coef[3]=1;
    		irreduciblePoly.coef[4]=1;
    		irreduciblePoly.coef[8]=1; 
    	}
    	return irreduciblePoly;
    }
    
    public void init() {	
		for (int i = 0; i < coef.length; i++) {
			coef[i]=0;
		}
    }
    
    public void reducedeg () {
        degree = -1;
        for (int i=coef.length-1; i>=0; i--) {
            if (coef[i] != 0) {
                degree = i;
                return;
            }
        }
    }
    
    public Polynomial pad() {
    	Polynomial paddedPoly = new Polynomial (m-1,m);
    	for (int i = 0; i<=degree; i++) {	        		
    		paddedPoly.coef[i] = coef[i];
        }
    	return paddedPoly;
    }
 
    void reducemodm() {
    	Polynomial result = new Polynomial (degree,m);		//Initialization: result = this
    	for(int i = 0; i<=degree; i++)
			result.coef[i]=coef[i];    	
    	Polynomial m= new Polynomial(result.degree,this.m);	//m will hold the value to be subtracted from result
    	Polynomial temp = getm();							//first, set m=m(x)=irreducible poly
    	int diff = result.degree-this.m;					//find the diff in degrees = amount by which we need to shift m left 
    	while (diff>=0) {									//then subtract shifted m from result and repeat
    		m.degree=result.degree;
    		m.init();
    		for(int i = 0; i<=temp.degree; i++)
    			m.coef[i]=temp.coef[i];      	
    		for(int i = result.degree; i>=diff; i--)
    			m.coef[i]=m.coef[i-diff];
    		for(int i = diff-1; i>=0; i--)
    			m.coef[i]=0;		
    		result=result.minus(m);
    		result.reducedeg();
    		diff = result.degree-this.m;
    	}
    	degree=result.degree;
    	for(int i = 0; i<=result.degree; i++)
			coef[i]=result.coef[i];  
    	}
    		    
    public Polynomial plus(Polynomial q) {
    	Polynomial maxdeg = new Polynomial(Math.max(degree, q.degree),m);
    	if (degree>=q.degree)
    		maxdeg = this;
    	else
    		maxdeg = q;
    	Polynomial result = new Polynomial(Math.max(degree, q.degree),m);
        for (int i = 0; i<=Math.min(degree, q.degree); i++)
        	result.coef[i] = coef[i]^q.coef[i];
        for (int i = Math.min(degree, q.degree)+1; i<=Math.max(degree, q.degree); i++)
        	result.coef[i] = maxdeg.coef[i];
        return result;
    }
    
    public Polynomial minus(Polynomial q) {
    	Polynomial maxdeg = new Polynomial(Math.max(degree, q.degree),m);
    	if (degree>=q.degree)
    		maxdeg = this;
    	else
    		maxdeg = q;
    	Polynomial result = new Polynomial(Math.max(degree, q.degree),m);
        for (int i = 0; i<=Math.min(degree, q.degree); i++)
        	result.coef[i] = coef[i]^q.coef[i];
        for (int i = Math.min(degree, q.degree)+1; i<=Math.max(degree, q.degree); i++)
        	result.coef[i] = maxdeg.coef[i];
        return result;
    }
    
    public Polynomial times(Polynomial q) {
    	Polynomial[] Result = new Polynomial[this.m];
    	Polynomial Multiplier = new Polynomial(degree,m);
    	for (int i = 0; i<=degree; i++) {	        		
    		Multiplier.coef[i] = coef[i];
        }
    	Multiplier.reducemodm();
        Multiplier = Multiplier.pad();
        //System.out.println("Multiplier is : " + Multiplier.toString());
        Result[0] = new Polynomial (this.m-1,this.m);
        for (int i = 0; i<=Result[0].degree; i++) {	        		
    		Result[0].coef[i] = Multiplier.coef[i];
        }
        Polynomial Multiplicand = new Polynomial (q.degree,this.m);
        for (int i = 0; i<=q.degree; i++) {	        		
    		Multiplicand.coef[i] = q.coef[i];
        }
        Multiplicand.reducemodm();
        Multiplicand = Multiplicand.pad();
        //System.out.println("Multiplicand is : " + Multiplicand.toString());
        Polynomial m = getm();
        m.coef[m.degree]=0;
        m.degree=this.m-1;
        for (int i = 1; i<=Multiplicand.degree; i++) { 
	        boolean xor = false;
	        if (Multiplier.coef[Multiplier.m-1] == 1)
	        	xor=true;
	        for (int j = Multiplier.degree; j>0; j--) {	        		
	        		Multiplier.coef[j] = Multiplier.coef[j-1];
	        }
	        Multiplier.coef[0]=0;
	        if (xor == true)
	        	Multiplier = Multiplier.plus(m);
	        Result[i]=new Polynomial (this.m-1,this.m);
	        for (int l = 0; l<=Result[i].degree; l++) {	        		
	    		Result[i].coef[l] = Multiplier.coef[l];
	        }
	        //System.out.println("Result[" + i + "] is : " + Result[i].toString());
        }
        Polynomial Product = new Polynomial(this.m-1,this.m);
        for (int i = 0; i<=Multiplicand.degree; i++) {
        	if (Multiplicand.coef[i] == 1) {
        		Product=Product.plus(Result[i]);
        	}
        }
        Product.reducedeg();
        return Product;
    }


    public ArrayList<Polynomial> dividedby(Polynomial q) {
    	if (q.degree == -1) {
    		throw new IllegalArgumentException("Divisor cannot be zero");
    	}
    	ArrayList<Polynomial> QuotandRem = new ArrayList<Polynomial>();
    	Polynomial Quotient = null, Remainder = null;
    	Polynomial Dividend = new Polynomial (degree,m);
    	for(int i = 0; i<=degree; i++)
			Dividend.coef[i] = coef[i];
    	Polynomial Divisor = new Polynomial (q.degree,m);
    	for(int i = 0; i<=q.degree; i++)
    		Divisor.coef[i] = q.coef[i]; 
    	Polynomial m = new Polynomial(Dividend.degree,this.m);
    	int diff = Dividend.degree-Divisor.degree;
    	if (diff>=0) {
    		Quotient = new Polynomial(Dividend.degree-Divisor.degree,this.m);
    		while (diff>=0) {
	    		Quotient.coef[diff]=1;
	    		m.degree=Dividend.degree;
	    		m.init();
	    		for(int i = 0; i<=Divisor.degree; i++)
	    			m.coef[i]=Divisor.coef[i];      	
	    		for(int i = Dividend.degree; i>=diff; i--)
	    			m.coef[i]=m.coef[i-diff];
	    		for(int i = diff-1; i>=0; i--)
	    			m.coef[i]=0;
	    		Dividend=Dividend.minus(m);
	    		Dividend.reducedeg();
	    		diff = Dividend.degree-Divisor.degree;
    		}
    		Remainder = new Polynomial (Dividend.degree,this.m);
        	for(int i = 0; i<=Dividend.degree; i++)
    			Remainder.coef[i]=Dividend.coef[i];
    		
    	}
    	else {
    		Quotient = new Polynomial(0,this.m);
    		Quotient.degree=-1;
    		Remainder=new Polynomial (degree,this.m);
        	for(int i = 0; i<=degree; i++)
        		Remainder.coef[i]=coef[i];
    	}
    	QuotandRem.add(Quotient);
    	QuotandRem.add(Remainder);
    	return QuotandRem;
    }
    
    
    public Polynomial findinversemodm() {
    	Polynomial P1,P2,A1,A2,A3,B1,B2,B3,Q,T1,T2,T3,Inverse = null,R,m;
    	ArrayList<Polynomial> QandR = new ArrayList<Polynomial>();
    	m = this.getm();
    	P1 = new Polynomial (m.degree,this.m);
    	for(int i = 0; i<=m.degree; i++)
			P1.coef[i] = m.coef[i];
    	P2 = new Polynomial (degree,this.m);
    	for(int i = 0; i<=degree; i++)
			P2.coef[i] = coef[i];
    	P2.reducemodm();
    	A1 = new Polynomial (P1.degree+P2.degree,this.m);
    	A1.coef[0]=1;
    	//System.out.println("A1 is : " + A1.toString());
    	A2 = new Polynomial (P1.degree+P2.degree,this.m);
    	//System.out.println("A2 is : " + A2.toString());
    	A3 = new Polynomial (P1.degree,this.m);
    	for(int i = 0; i<=P1.degree; i++)
			A3.coef[i]=P1.coef[i];
    	//System.out.println("A3 is : " + A3.toString());
    	B1 = new Polynomial (P1.degree+P2.degree,this.m);
    	//System.out.println("B1 is : " + B1.toString());
    	B2 = new Polynomial(P1.degree+P2.degree,this.m);
    	B2.coef[0]=1;
    	//System.out.println("B2 is : " + B2.toString());
    	B3 = new Polynomial(P2.degree,this.m);
    	for(int i = 0; i<=P2.degree; i++)
			B3.coef[i]=P2.coef[i];
    	//System.out.println("B3 is : " + B3.toString());
    	do {
	    	if (B3.degree == 0 && B3.coef[0]==0) {
	    		System.out.println("No Inverse");
	    		break;
	    	}
	    	else if (B3.degree == 0 && B3.coef[0]==1) {
	    		System.out.println("Inverse Found");
	    		Inverse = B2;
	    		break;
	    	}
    		QandR = A3.dividedby(B3);
    		Q = QandR.get(0);
    		A3.reducemodm();
    		//System.out.println("Q is : " + Q.toString());
    		T1 = A1.minus(Q.times(B1));
    		T1.reducedeg();
    		//System.out.println("T1 is : " + T1.toString());
    		T2 = A2.minus(Q.times(B2));
    		T2.reducedeg();
    		//System.out.println("T2 is : " + T2.toString());
    		T3 = A3.minus(Q.times(B3));
    		T3.reducedeg();
    		//System.out.println("T3 is : " + T3.toString());
    		A1 = B1;
    		//System.out.println("A1 is : " + A1.toString());
    		A2 = B2;
    		//System.out.println("A2 is : " + A2.toString());
    		A3 = B3;
    		//System.out.println("A3 is : " + A3.toString());
    		B1 = T1;
    		//System.out.println("B1 is : " + B1.toString());
    		B2 = T2;
    		//System.out.println("B2 is : " + B2.toString());
    		B3 = T3;
    		//System.out.println("B3 is : " + B3.toString());
    	} while (B3.degree>=0);
    	return Inverse;
    }
  
    public String toString() {
        if (degree == -1)
        	return "0";
        else if (degree ==  0)
        	return "" + coef[0];
        else if (degree ==  1)
        	return coef[1] + "x + " + coef[0];
        String s = coef[degree] + "x^" + degree;
        for (int i = degree-1; i>=0; i--) {
            if (coef[i] == 0)
            	continue;
            else if (coef[i]>0)
            	s = s + " + " + (coef[i]);
            else if (coef[i]<0)
            	s = s + " - " + (-coef[i]);
            if (i == 1)
            	s = s + "x";
            else if (i>1)
            	s = s + "x^" + i;
        }
        return s;
    }
    

  /*  
    public static void main(String[] args) {
    	Polynomial p2;
    	int [] a0 = {1,0,1,0,1,0,1,0,1,0,1,0};
        Polynomial p0 = new Polynomial(11,a0,8); 
        System.out.println(p0.toString());
        
        int [] a1 = {1,1,1,1,1,1};
        Polynomial p1 = new Polynomial(5,a1,8);
	    System.out.println(p1.toString());
	    System.out.println(p0.dividedby(p1).toString());
	    
	    p2 = p1.plus(p0);
	    System.out.println("Sum is : " + p2.toString());
	    
	    p2 = p1.minus(p0);
	    System.out.println("Subtraction is : " + p2.toString());
	   
	    p2=p0.times(p1);
	    System.out.println("Multiplication is : " + p2.toString());
	    
	    p0.reducemodm();
        System.out.println("Reduced polynomial is :" + p0.toString());
	    
	    ArrayList<Polynomial> LIST = new ArrayList<Polynomial>();
	    LIST = p0.dividedby(p1);
	    System.out.println("Quotient is : " + LIST.get(0).toString());
	    System.out.println("Remainder is : " + LIST.get(1).toString());
	    
	    Polynomial p3 = p1.findinversemodm();
	    System.out.println("Inverse is : " + p3.toString());
	    
        
    }*/
}