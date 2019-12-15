import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class LinearProblem_DualProblemFinder {
	

	public static void main(String[] args) {

		System.out.println("Enter text file destination: ");
		Scanner keyboard = new Scanner(System.in);
		String fileName = keyboard.next();
		Scanner inputStream = null;
		
		ArrayList<Integer> C = new ArrayList<>();
		TwoDimentionalArrayList<Integer> A = new TwoDimentionalArrayList<>();
		ArrayList<Integer> Eqin = new ArrayList<>();
		ArrayList<Integer> b = new ArrayList<>();
		int minMax = 0;
		String stStr="";
		int lineCounter = 0;
		boolean errorFound=false;
		String minmaxStr="";
		int counterOfx = 0;
		try
		{
			inputStream = new Scanner(new File(fileName));
			
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file " + fileName);
			System.exit(0);
		}
		while (inputStream.hasNextLine())
		{
			if(errorFound) break;
			
			String line = inputStream.nextLine();
			if(line.isEmpty()) 		continue;
			
			lineCounter++;
			boolean enteredNewLine=true;
			line = line.replaceAll("\\s","");
			int stCounter = 0;
			
			boolean afterx = false;
			int sign=1;							//sintelestis prosimou
			String tempStrForXnumber="";
			String tempStrForFactor="";
			int tempIntForXnumber=0;
			int tempIntForFactor=0;
			boolean lineSplitted =false;
			String [] lineParts = null;
			String linePartForA = null;
			String linePartForB;
			Character aCharacter;
			boolean newMononym=true;
			
			
			if(lineCounter == 1) {
				for(int i=0; i< line.length(); i++) {
					
					aCharacter =line.charAt(i);
				
				
					if(minMax==0) {
						if(minmaxStr.length()==3) {
							if(minmaxStr.equals("min")) 
								minMax=-1;
							else if(minmaxStr.equals("max"))
									minMax=1;
						}
						else {
							minmaxStr+=aCharacter;
							continue;
						}
						if(enteredNewLine&&Character.isDigit(aCharacter)) {
							sign=1;
							tempStrForFactor+=aCharacter;
							enteredNewLine=false;
							newMononym=false;
						}
						else if(enteredNewLine&&aCharacter=='-') {
							sign=-1;
							enteredNewLine=false;
						}
						else if(enteredNewLine&&aCharacter=='x') {
							sign=1;
							tempStrForFactor="1";
							enteredNewLine=false;
							afterx=true;
							newMononym=false;
						}
						else if(newMononym&&aCharacter=='x') {
							tempStrForFactor="1";
							afterx=true;
							newMononym=false;	
						}
						else if(!newMononym&&aCharacter=='x'&&afterx) {
							errorFound=true;
							System.out.println("Error, missing factor on function line");
							break;
						}
						else if(!afterx&&Character.isDigit(aCharacter)) {
							tempStrForFactor+=aCharacter;
							newMononym=false;
						}
						else if(aCharacter=='x') {
							afterx=true;
							
						}
						else if(afterx&&Character.isDigit(aCharacter)) {
							tempStrForXnumber+=aCharacter;
							
						}
						else if(aCharacter=='-'||aCharacter=='+') {
							afterx=false;
							tempIntForFactor=Integer.parseInt(tempStrForFactor);
							tempIntForXnumber=Integer.parseInt(tempStrForXnumber);
							newMononym=true;
							tempStrForFactor="";
							tempStrForXnumber="";
							C.add(tempIntForXnumber-1, tempIntForFactor*sign);
							if(aCharacter=='-') sign=-1;
							else if(aCharacter=='+')sign=1;
						}
						
						if(i+1==line.length()) {
							
							afterx=false;
							tempIntForFactor=Integer.parseInt(tempStrForFactor);
							tempIntForXnumber=Integer.parseInt(tempStrForXnumber);
							newMononym=true;
							tempStrForFactor="";
							tempStrForXnumber="";
							C.add(tempIntForXnumber-1, tempIntForFactor*sign);
						}		
					}
					
					
					
					
					
						
					}
					
					
				
				
			}
				
				
			
			
				
				if(lineCounter!=1&&!line.startsWith("xj")) {
					A.add(new ArrayList<>());
					for(int i=0;i<=counterOfx;i++) {
						
						A.get(lineCounter-2).add(0);
					}
					
					
					if(line.contains("<=")) {
						Eqin.add(-1);
						lineParts=line.split("<=");
						lineSplitted=true;
					}
					else if(line.contains("=")&&!line.contains(">=")&&!line.contains("<=")) {
						Eqin.add(0);
						lineParts=line.split("=");
						lineSplitted=true;
					}
					else if(line.contains(">=")) {
						Eqin.add(1);
						lineParts=line.split(">=");
						lineSplitted=true;
					}
					if(lineSplitted) {
						linePartForA = lineParts[0];
						linePartForB = lineParts[1];
						b.add(Integer.parseInt(linePartForB));
						
						if(!b.contains(Integer.parseInt(linePartForB)))		
						 {
							System.out.println("Error, missing equalliy symbol on line "+lineCounter);
							errorFound=true;
							break;
						}
						afterx = false;
							
						for(int i=0; i< linePartForA.length(); i++) {
						
							
							aCharacter =linePartForA.charAt(i);
							
								
							 if(lineCounter == 2) {
							
								if(stCounter<4) {
									stCounter++;
									stStr+=aCharacter;
									continue;
								}
								if(!stStr.equals("s.t.")) {
									System.out.println("Error, characters \"s.t.\" not found");
									errorFound=true;
									
								}
							
							}
							
							if(enteredNewLine&&Character.isDigit(aCharacter)) {
								sign=1;
								tempStrForFactor+=aCharacter;
								enteredNewLine=false;
								newMononym=false;
							}
							else if(enteredNewLine&&aCharacter=='-') {
								sign=-1;
								enteredNewLine=false;
							}
							else if(enteredNewLine&&aCharacter=='x') {
								sign=1;
								tempStrForFactor="1";
								enteredNewLine=false;
								afterx=true;
								newMononym=false;
							}
							else if(newMononym&&aCharacter=='x') {
								tempStrForFactor="1";
								afterx=true;
								newMononym=false;	
							}
							else if(!newMononym&&aCharacter=='x'&&afterx) {
								errorFound=true;
								System.out.println("Error, missing factor on line "+lineCounter);
								break;
							}
							else if(!afterx&&Character.isDigit(aCharacter)) {
								tempStrForFactor+=aCharacter;
								newMononym=false;
							}
							else if(aCharacter=='x') {
								afterx=true;
								
							}
							else if(afterx&&Character.isDigit(aCharacter)) {
								tempStrForXnumber+=aCharacter;
								
							}
							else if(aCharacter=='-'||aCharacter=='+') {
								afterx=false;
								tempIntForFactor=Integer.parseInt(tempStrForFactor);
								tempIntForXnumber=Integer.parseInt(tempStrForXnumber);
								newMononym=true;
								tempStrForFactor="";
								tempStrForXnumber="";
								A.addToInnerArray(lineCounter-2,tempIntForXnumber-1 , tempIntForFactor*sign);
								if(aCharacter=='-') sign=-1;
								else if(aCharacter=='+')sign=1;
							}
							
							if(i+1==linePartForA.length()) {
								
								afterx=false;
								tempIntForFactor=Integer.parseInt(tempStrForFactor);
								tempIntForXnumber=Integer.parseInt(tempStrForXnumber);
								newMononym=true;
								tempStrForFactor="";
								tempStrForXnumber="";
								A.addToInnerArray(lineCounter-2,tempIntForXnumber-1 , tempIntForFactor*sign);
							}
							
							
						}
					}
					else if(line.startsWith("xj")) {
						System.out.println("End of lines");
						break;
					}
					else if(!line.startsWith("xj")&&!lineSplitted) {
						System.out.println("Missing equallity symbol on line"+lineCounter);
						errorFound=true;
						break;
					}		
				}								
		}	
			
		
		inputStream.close();

		Writer writer = null;
		if(!errorFound) {
			
			ArrayList<Integer> CDual = new ArrayList<>();
			TwoDimentionalArrayList<Integer> ADual = new TwoDimentionalArrayList<>();
			ArrayList<Integer> EqinDual = new ArrayList<>();
			ArrayList<Integer> bDual = new ArrayList<>();
			int minMaxDual = minMax*-1;	
		
			for(int number : b) {
				 CDual.add(number);
			}
			
			for(int number : C) {
				bDual.add(number);
				if(minMax==1) EqinDual.add(1);
				else EqinDual.add(-1);
			}
			
			for(int i=0;i<A.size();i++) {
				for(int j=0;j<A.get(i).size();j++) {
					ADual.addToInnerArray(j,i,A.get(i).get(j) );
				}
			}
			for(int number : Eqin) {
				
			}
			
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("LP_2Dual.txt"), StandardCharsets.UTF_8));
		 
		    writer.write("MinMax:"+minMaxDual+" "+"\r\nc: ");
		    writer.flush();
		    
		    for(int number : CDual) {
		    	writer.write(number+" ");
		    	writer.flush();
		    }
		    writer.write("\r\nb: ");
		    writer.flush();
		    for(int number : bDual) {
		    	writer.write(number+" ");
		    	writer.flush();
		    }
		    writer.write("\r\nEqin: ");
		    for(int number : EqinDual) {
		    	writer.write(number+" ");
		    	writer.flush();
		    }
		    
		    writer.write("\r\nA: ");
		    for(int i=0;i<ADual.size();i++) {
		    	if(i!=0)  writer.write("\r\n   ");
		    	writer.flush();
				for(int j=0;j<ADual.get(i).size();j++) {
					writer.write(ADual.get(i).get(j).toString()+" ");
					writer.flush();
				}
			}
		    writer.write("\r\n");
		    for(int i=0; i<Eqin.size();i++) {
		    	if(minMax==1) {
		    		if(Eqin.get(i)==0) writer.write("x"+(i+1)+" is free variable\r\n");
		    		else if(Eqin.get(i)==1) writer.write("x"+ (i+1)+"<=0\r\n");
		    		else if(Eqin.get(i)==-1) writer.write("x"+ (i+1)+">=0\r\n");
		    	}
		    	else if(minMax==-1) {
		    		if(Eqin.get(i)==0) writer.write("x"+i+1+" is free variable\r\n");
		    		else if(Eqin.get(i)==1) writer.write("x"+ i+1+">=0\r\n");
		    		else if(Eqin.get(i)==-1) writer.write("x"+ i+1+"<=0\r\n");
		    	}
		    }
		    
		    
		    
		    
	    
		} catch (IOException ex) {
		    // Report
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
		

		
		}
		
		
	
	}

	public static class TwoDimentionalArrayList<T> extends ArrayList<ArrayList<Integer>> {
	    public void addToInnerArray(int index, Integer element) {
	        while (index >= this.size()) {
	            this.add(new ArrayList<Integer>());
	        }
	        this.get(index).add(element);
	    }

	    public void addToInnerArray(int index, int index2, int element) {
	        while (index >= this.size()) {
	            this.add(new ArrayList<Integer>());
	        }

	        ArrayList<Integer> inner = this.get(index);
	        while (index2 >= inner.size()) {
	            inner.add(null);
	        }

	        inner.set(index2, element);
	    }
	  
	    public void addRow(int size) {
	    	this.add(new ArrayList<>());
	    	ArrayList<Integer> inner = this.get(size()-1);
	    	for(int i=0;i< size; i++) {
	    		inner.add(0);
	    		
	    	}
	    	
	    }
	}
}



