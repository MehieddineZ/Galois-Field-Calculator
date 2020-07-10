package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private Button add;
    
    @FXML
    private TextField Text1;
    @FXML
    private Button sub;
    
    
    @FXML
    private TextField Poly1;
    @FXML
    private TextField Poly2;
    @FXML
    private TextField Text4;
    @FXML
    private Button modRed;

    @FXML
    private Button Inverse;

    @FXML
    private Button Div;
    @FXML
    private CheckBox BinaryPoly2;

    @FXML
    private CheckBox HEXPoly2;

   
    @FXML
    private CheckBox Poly1Hex;

    @FXML
    private CheckBox BinaryPoly1;
    
    
    @FXML
    private TextField TEXTPoly1;

    @FXML
    private TextField TextPoly2;
    @FXML
    private CheckBox HEXResult;

    @FXML
    private CheckBox BINResult;
    @FXML
    private CheckBox Xresult;
    @FXML
    private Button Clear;
    @FXML
    private Button times;

    @FXML
    void ButtonPressed(ActionEvent event) {
    	
    	boolean check1= true;
    	boolean check2=true;
    	String s1;
    	int m;
    	int l1=0;
    	int l2=0;
    	char [] c1 = new char[Poly1.getText().length()];
    	
    	if (Poly2.getText().length() != 0)
    	{	
    		
    		if (BinaryPoly2.isSelected())
        	{
        		l2=Poly2.getText().length();
        	}
        	else if (HEXPoly2.isSelected())
        	{
        		l2=4*Poly2.getText().length();
        	}
    	}
    	int[] coef2= new int [l2];
    	if (l2 != 0)
    	{
    		char [] c2 = new char[Poly2.getText().length()];
    		c2=Poly2.getText().toCharArray();
    		if (BinaryPoly2.isSelected())
        	{
        		
        		for (int i =0 ; i<l2;i++)
        		{
        			coef2[i]=(int)c2[i]-48;
        			if (coef2[i] != 0 && coef2[i] != 1 )
        			{
        				check2 =false;
        				break;
        			}
        		}
        		
        	}
    		if (HEXPoly2.isSelected())
        	{
    			for (int i= 0 ; i<c2.length;i++)
       		 {
       			 if (c2[i] != '0'&&c2[i] != '1'&& c2[i] != '2'&&c2[i] != '3'&&c2[i] != '4'&&c2[i] != '5'&&c2[i] != '6'&&c2[i] != '7'&&c2[i] != '8'&&c2[i] != '9'&&c2[i] != 'a'&&c2[i] != 'A'&&c2[i] != 'b'&&c2[i] != 'B'&&c2[i] != 'c'&&c2[i] != 'C'&&c2[i] != 'd'&&c2[i] != 'D'&&c2[i] != 'e'&&c2[i] != 'E'&&c2[i] != 'f'&&c2[i] != 'F')
       			 {
       				 check2=false;
       				 break;
       			 }
       			
     
       		 }
        		if (check2==true)
        		{
        			toBin(coef2,c2);
        		}
        		
        	}
    		
    	}
    		
    	if (BinaryPoly1.isSelected())
    	{
    		l1=Poly1.getText().length();
    	}
    	else if (Poly1Hex.isSelected())
    	{
    		l1=4*Poly1.getText().length();
    	}
    	
    	
    	int [] coef1 = new int[l1];
    	
    	c1 = Poly1.getText().toCharArray();
    	
    	if (BinaryPoly1.isSelected())
    	{
    		
    		for (int i =0 ; i<l1;i++)
    		{
    			coef1[i]=(int)c1[i]-48;
    			if (coef1[i] != 0 && coef1[i] != 1 )
    			{
    				check1 =false;
    				break;
    			}
    		}
    		
    	}
    	
    	 if (Poly1Hex.isSelected())
    	 {
    		 for (int i= 0 ; i<c1.length;i++)
    		 {
    			 if (c1[i] != '0'&&c1[i] != '1'&& c1[i] != '2'&&c1[i] != '3'&&c1[i] != '4'&&c1[i] != '5'&&c1[i] != '6'&&c1[i] != '7'&&c1[i] != '8'&&c1[i] != '9'&&c1[i] != 'a'&&c1[i] != 'A'&&c1[i] != 'b'&&c1[i] != 'B'&&c1[i] != 'c'&&c1[i] != 'C'&&c1[i] != 'd'&&c1[i] != 'D'&&c1[i] != 'e'&&c1[i] != 'E'&&c1[i] != 'f'&&c1[i] != 'F')
    			 {
    				 check1=false;
    				 break;
    			 }
    			 
  
    		 }
    		 if (check1==true)
    		 {
    			 toBin(coef1,c1);
    		 }
    		
    	}
    	s1= Text4.getText();
		m = Integer.parseInt(s1);
		Polynomial P1 = new Polynomial(coef1.length-1,coef1,m);
		int coeftmp[]= {1,1};
		if (check1==true)
		{
			TEXTPoly1.setText(P1.toString());
		}
	
		Polynomial P2;
		
		if(coef2.length != 0)
		{
			P2 = new Polynomial(coef2.length-1,coef2,m);
			System.out.println(P2.toString());
			if (check2==true)
			{
			TextPoly2.setText(P2.toString());
			}
		}
		else
		{
			P2 = new Polynomial (coeftmp.length-1,coeftmp,m);;
		}
		System.out.println(P1.toString());
		
		
		System.out.print (check1);
		System.out.print (check2);
		
    	if (event.getSource()==add)
    	{
    		if (check1 == false || check2==false)
    		{
    			Text1.setText("Invalid Input");
    		}
    		else 
    		{
    			//TEXTPoly1.setText(P1.toString());
    			P1.reducemodm();
    			P2.reducemodm();
    			Polynomial P3 = P1.plus(P2);
    			P3.reducemodm();
    			int[] coefs = new int[P3.degree+1];
				for (int i =0 ; i< P3.degree+1;i++)
				{
					coefs[i]=P3.coef[i];
				}
    			if (Xresult.isSelected())
    			{
    				Text1.setText(P3.toString());
    			}
    			else if (HEXResult.isSelected())
    			{
    				int size;
    			
    				if (coefs.length%4==0)
    				{
    					size = coefs.length/4;
    				}
    				else 
    				{
    					size = coefs.length/4+1;
    				}
    				char[] Hex = new char[size];
    				toHex2(Hex,coefs);
    				String S = new String(Hex);
    				Text1.setText(S);
    				
    			}
    			else if (BINResult.isSelected())
    			{
    				char [] coef = new char[coefs.length];
    				intTochar(coefs,coef);
    				String SBIN = new String (coef);
    				Text1.setText(SBIN);
    			
    			
    			}
    		}
    		
    	}
    	if (event.getSource()==times)
    	{
    		boolean check3 =true;
    		if((P2.degree==0 && P2.coef[0]==0)||(P1.degree==0 && P1.coef[0]==0))
			{
				check3=false;
			}
    		if (check1 == false || check2==false || check3==false)
    		{
    			if (check1 == false || check2==false)
    			{
    				Text1.setText("Invalid Input");
    			}
    			else
    			{
    				Text1.setText("0");
    			}
    		}
    		else 
    		{
    			//TEXTPoly1.setText(P1.toString());
    			P1.reducemodm();
    			P2.reducemodm();
    			Polynomial P3 = P1.times(P2);
    			P3.reducemodm();
    			int[] coefs = new int[P3.degree+1];
				for (int i =0 ; i< P3.degree+1;i++)
				{
					coefs[i]=P3.coef[i];
				}
				
    			if (Xresult.isSelected())
    			{
    				Text1.setText(P3.toString());
    				
    			}
    			else if (HEXResult.isSelected())
    			{
    				int size;
    			
    				if (coefs.length%4==0)
    				{
    					size = coefs.length/4;
    				}
    				else 
    				{
    					size = coefs.length/4+1;
    				}
    				char[] Hex = new char[size];
    				toHex2(Hex,coefs);
    				String S = new String(Hex);
    				Text1.setText(S);
    				
    			}
    			else if (BINResult.isSelected())
    			{
    				char [] coef = new char[coefs.length];
    				intTochar(coefs,coef);
    				String SBIN = new String (coef);
    				Text1.setText(SBIN);
    			
    			
    			}
    		}
    		
    	}
    	if (event.getSource()==Div)
    	{
    		
    		/*if (coef1.length<coef2.length)
    		{
    			check3=false;
    		}
    		*/
    		if (check1 == false || check2==false)
    		{
    			Text1.setText("Invalid Input");
    		}
    		else
    		{
    		try {
    			P1.reducemodm();
    			P2.reducemodm();
    		Polynomial P3= P1.dividedby(P2).get(0);
    		//TEXTPoly1.setText(P1.toString());
    		if (Xresult.isSelected())
    		{
    			Text1.setText(P1.dividedby(P2).get(0).toString());
    		}
    		else if (HEXResult.isSelected())
    		{
    			int size;
    			
    			if (P3.coef.length%4==0)
    			{
    				size = P3.coef.length/4;
    			}
    			else 
    			{
    				size = P3.coef.length/4+1;
    			}
    			char[] Hex = new char[size];
    			toHex2(Hex,P3.coef);
    			String S = new String(Hex);
    			Text1.setText(S);
    				
    		}
    		else if (BINResult.isSelected())
    		{
    			char [] coef = new char[P3.coef.length];
    			intTochar(P3.coef,coef);
    			String SBIN = new String (coef);
    			System.out.print(SBIN);
    			Text1.setText(SBIN);
    			
    			
    		}
    		}
    		catch (Exception e)
    		{
    			Text1.setText("Error in Computing");
    		}
    		}
    		
    	}
    	if (event.getSource()==sub)
    	{
    		if (check1 == false || check2==false)
    		{
    			Text1.setText("Invalid Input");
    		}
    		else
    		{
    		P1.reducemodm();
    		P2.reducemodm();
    		Polynomial P3= P1.minus(P2);
    		P3.reducemodm();
			int[] coefs = new int[P3.degree+1];
			for (int i =0 ; i< P3.degree+1;i++)
			{
				coefs[i]=P3.coef[i];
			}
    	
    		if (Xresult.isSelected())
    		{
    			Text1.setText(P3.toString());
    		}
    		else if (HEXResult.isSelected())
    		{
    			int size;
    			
    			if (coefs.length%4==0)
    			{
    				size = coefs.length/4;
    			}
    			else 
    			{
    				size = coefs.length/4+1;
    			}
    			char[] Hex = new char[size];
    			toHex2(Hex,coefs);
    			String S = new String(Hex);
    			Text1.setText(S);
    				
    		}
    		else if (BINResult.isSelected())
    		{
    			char [] coef = new char[coefs.length];
    			intTochar(coefs,coef);
    			String SBIN = new String (coef);
    			Text1.setText(SBIN);
    			
    			
    		}
    		}
    	}
    	if (event.getSource()==Inverse)
    	
    	{
    		if (check1 == false)
    		{
    			Text1.setText("Invalid Input");
    		}
    		else
    		{
    	
    		Polynomial P3 = P1.findinversemodm();
    		/*for (int i =0 ; i<P3.coef.length;i++)
    		{
    			System.out.print(P3.coef[i]);
    		}*/
    		//TEXTPoly1.setText(P1.toString());
    		if (Xresult.isSelected())
    		{
    			Text1.setText(P3.toString());
    		}
    		else if (HEXResult.isSelected())
    		{
    			int size;
    			
    			if (P3.coef.length%4==0)
    			{
    				size = P3.coef.length/4;
    			}
    			else 
    			{
    				size = P3.coef.length/4+1;
    			}
    			char[] Hex = new char[size];
    			toHex2(Hex,P3.coef);
    			String S = new String(Hex);
    			Text1.setText(S);
    				
    		}
    		else if (BINResult.isSelected())
    		{
    			char [] coef = new char[P3.coef.length];
    			intTochar(P3.coef,coef);
    			
    			String SBIN = new String (coef);
    			
    			Text1.setText(SBIN);
    			
    			
    		}
    		}
    	}
    	if (event.getSource()==modRed)
    	{
    		if (check1 == false)
    		{
    			Text1.setText("Invalid Input");
    		}
    		else
    		{
    			if (Xresult.isSelected())
        		{
        			P1.reducemodm();
        			Text1.setText(P1.toString());
        		}
    			else
    			{
    				P1.reducemodm();
    				int[] coefs = new int[P1.degree+1];
    				for (int i =0 ; i< P1.degree+1;i++)
    				{
    					coefs[i]=P1.coef[i];
    				}
    			
    		 if (HEXResult.isSelected())
    		{
    			
    			int size;
    			
    			if (coefs.length%4==0)
    			{
    				size = coefs.length/4;
    			}
    			else 
    			{
    				size = coefs.length/4+1;
    			}
    			char[] Hex = new char[size];
    			toHex2(Hex,coefs);
    			String S = new String(Hex);
    			Text1.setText(S);				
    		}
    		else if (BINResult.isSelected())
    		{ 		
    			char [] coef = new char[coefs.length];
    			intTochar(coefs,coef);
    			String SBIN = new String (coef);
    			Text1.setText(SBIN);			
    		}
    		}
    		}
    	}
    	if (event.getSource() == Clear)
    	{
    		Text1.clear();
    		Poly1.clear();
    		Poly2.clear();
    		Text4.clear();
    		TEXTPoly1.clear();
    		TextPoly2.clear();
    		BinaryPoly2.setSelected(false);
    		HEXPoly2.setSelected(false);
    		Poly1Hex.setSelected(false);
    		BinaryPoly1.setSelected(false);
    		HEXResult.setSelected(false);
    		BINResult.setSelected(false);
    		Xresult.setSelected(false);
    		
    		
    	}

    }
    @FXML
    void HandleBinaryPoly1() {
    		if (BinaryPoly1.isSelected())
    		{
    			Poly1Hex.setSelected(false);
    		}
    }

    @FXML
    void HandleBinaryPoly2() {
    	if (BinaryPoly2.isSelected())
    	{
    		HEXPoly2.setSelected(false);
    	}

    }

    @FXML
    void HandleHexPoly2() {
    	if (HEXPoly2.isSelected())
    	{
    		BinaryPoly2.setSelected(false);
    	}

    }

    @FXML
    void HandlePoly1Hex() {
    	if (Poly1Hex.isSelected())
    	{
    		BinaryPoly1.setSelected(false);
    	}

    }
    //a is in binary b is in Hex
    public static void toBin(int a [], char b[])
    {
    	
    		int j=0;
    		
    		for(int i =0 ; i<b.length;i++)
    		{
    			if( b[i]=='0')
    			{
    				a[j]=0;
    				a[j+1]=0;
    				a[j+2]=0;
    				a[j+3]=0;
    				
    			}
    			else if( b[i]=='1')
    			{
    				a[j]=0;
    				a[j+1]=0;
    				a[j+2]=0;
    				a[j+3]=1;
    				
    			}
    			else if( b[i]=='2')
    			{
    				a[j]=0;
    				a[j+1]=0;
    				a[j+2]=1;
    				a[j+3]=0;
    				
    			}
    			else if( b[i]=='3')
    			{
    				a[j]=0;
    				a[j+1]=0;
    				a[j+2]=1;
    				a[j+3]=1;
    				
    			}
    			else if( b[i]=='4')
    			{
    				a[j]=0;
    				a[j+1]=1;
    				a[j+2]=0;
    				a[j+3]=0;
    				
    			}
    			else if( b[i]=='5')
    			{
    				a[j]=0;
    				a[j+1]=1;
    				a[j+2]=0;
    				a[j+3]=1;
    				
    			}
    			else if( b[i]=='6')
    			{
    				a[j]=0;
    				a[j+1]=1;
    				a[j+2]=1;
    				a[j+3]=0;
    				
    			}
    			else if( b[i]=='7')
    			{
    				a[j]=0;
    				a[j+1]=1;
    				a[j+2]=1;
    				a[j+3]=1;
    				
    			}
    			else if( b[i]=='8')
    			{
    				a[j]=1;
    				a[j+1]=0;
    				a[j+2]=0;
    				a[j+3]=0;
    				
    			}
    			else if( b[i]=='9')
    			{
    				a[j]=1;
    				a[j+1]=0;
    				a[j+2]=0;
    				a[j+3]=1;
    				
    			}
    			else if( (b[i]=='a')||(b[i]=='A'))
    			{
    				a[j]=1;
    				a[j+1]=0;
    				a[j+2]=1;
    				a[j+3]=0;
    				
    			}
    			else if( (b[i]=='b')||(b[i]=='B'))
    			{
    				a[j]=1;
    				a[j+1]=0;
    				a[j+2]=1;
    				a[j+3]=1;
    				
    			}
    			else if( (b[i]=='c')||(b[i]=='C'))
    			{
    				a[j]=1;
    				a[j+1]=1;
    				a[j+2]=0;
    				a[j+3]=0;
    				
    			}
    			else if( (b[i]=='d')||(b[i]=='D'))
    			{
    				a[j]=1;
    				a[j+1]=1;
    				a[j+2]=0;
    				a[j+3]=1;
    				
    			}
    			else if( (b[i]=='e')||(b[i]=='E'))
    			{
    				a[j]=1;
    				a[j+1]=1;
    				a[j+2]=1;
    				a[j+3]=0;
    				
    			}
    			else if( (b[i]=='f')||(b[i]=='F'))
    			{
    				a[j]=1;
    				a[j+1]=1;
    				a[j+2]=1;
    				a[j+3]=1;
    				
    			}
    		
    			
    			j=j+4;
    		}
    }


    
    /*public static void toHex(char [] Hex , int [] bin)
    
    {
    	int [] tempsum= new int[Hex.length];
    	int j=Hex.length-1;
    	int b1,b2,b3;
    	for (int i=bin.length-1 ; i>=0;i=i-4)	
    	{
    		
    		 if (i-1<0)
    		{
    			
    			b1=0;
    			b2=0;
    			b3=0;
    		}
    		else if (i-2<0)
    		{
    			b2=0;
    			b3=0;
    			b1=bin[i-1];
    		
    		}
    		else if (i-3<0)
    		{
    			b3=0;
    			b2=bin[i-2];
    			b1=bin[i-1];
    			
    		}
    		else 
    		{
    			b3=bin[i-3];
    			b2=bin[i-2];
    			b1=bin[i-1];
    			
    		}
    		
    		tempsum[j]=bin[i]+2*b1+4*b2+8*b3;
    		j--;
    	}
    	for (int i=0;i<Hex.length;i++)
    	{
    		if(tempsum[i]<10)
    		{
    			Hex[i]=(char)(tempsum[i]+48);
    		}
    		else if (tempsum[i]==10)
    		{
    			Hex[i]='A';
    		}
    		else if (tempsum[i]==11)
    		{
    			Hex[i]='B';
    		}
    		else if (tempsum[i]==12)
    		{
    			Hex[i]='C';
    		}
    		else if (tempsum[i]==13)
    		{
    			Hex[i]='D';
    		}
    		else if (tempsum[i]==14)
    		{
    			Hex[i]='E';
    		}
    		else 
    		{
    			Hex[i]='F';
    		}
    		
    	}
    	
    }*/
    @FXML
    void HandleXresult(ActionEvent event) {
    	if (Xresult.isSelected())
    	{
    		BINResult.setSelected(false);
    		HEXResult.setSelected(false);
    	}
    }
    @FXML
    void HandleHEXResult(ActionEvent event) {
    	if (HEXResult.isSelected())
    	{
    		
    		BINResult.setSelected(false);
    		Xresult.setSelected(false);
    	}
    }
    @FXML
    void HandleBinResult(ActionEvent event) {
    	if (BINResult.isSelected())
    	{
    		HEXResult.setSelected(false);
    		Xresult.setSelected(false);
    		
    	}

    }
   public static void intTochar(int [] a , char [] c)
    {
    	for (int i = 0 ; i<a.length; i++)
    	{
    		c[a.length-1-i]=(char)(a[i]+48);
    	}
    }
    public static void intTochar2(int [] a , char [] c)
    {
    	for (int i = 0 ; i<a.length; i++)
    	{
    		c[i]=(char)(a[i]+48);
    	}
    }
    public static void toHex2(char [] Hex , int [] bin)
    
    {
    	int [] tempsum= new int[Hex.length];
    	int j=Hex.length-1;
    	int b1,b2,b3;
    	for (int i=0 ; i<bin.length;i=i+4)	
    	{
    		
    		 if (i+1==bin.length)
    		{
    			
    			b1=0;
    			b2=0;
    			b3=0;
    		}
    		else if (i+2==bin.length)
    		{
    			b2=0;
    			b3=0;
    			b1=bin[i+1];
    		
    		}
    		else if (i+3==bin.length)
    		{
    			b3=0;
    			b2=bin[i+2];
    			b1=bin[i+1];
    			
    		}
    		else 
    		{
    			b3=bin[i+3];
    			b2=bin[i+2];
    			b1=bin[i+1];
    			
    		}
    		
    		tempsum[j]=bin[i]+2*b1+4*b2+8*b3;
    		j--;
    	}
    	for (int i=0;i<Hex.length;i++)
    	{
    		if(tempsum[i]<10)
    		{
    			Hex[i]=(char)(tempsum[i]+48);
    		}
    		else if (tempsum[i]==10)
    		{
    			Hex[i]='A';
    		}
    		else if (tempsum[i]==11)
    		{
    			Hex[i]='B';
    		}
    		else if (tempsum[i]==12)
    		{
    			Hex[i]='C';
    		}
    		else if (tempsum[i]==13)
    		{
    			Hex[i]='D';
    		}
    		else if (tempsum[i]==14)
    		{
    			Hex[i]='E';
    		}
    		else 
    		{
    			Hex[i]='F';
    		}
    		
    	}
    	
    }
}
