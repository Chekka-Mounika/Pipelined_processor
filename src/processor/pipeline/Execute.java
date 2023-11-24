package processor.pipeline;

import generic.Statistics;
import processor.Processor;

public class Execute {
	Processor containingProcessor;
	OF_EX_LatchType OF_EX_Latch;
	EX_MA_LatchType EX_MA_Latch;
	EX_IF_LatchType EX_IF_Latch;
	Z_DataControlUnit dataControlUnit;
	Z_BranchControlUnit branchControlUnit;
	public Execute(Processor containingProcessor, OF_EX_LatchType oF_EX_Latch, EX_MA_LatchType eX_MA_Latch, EX_IF_LatchType eX_IF_Latch , Z_DataControlUnit dataControlUnit, Z_BranchControlUnit branchControlUnit)
	{
		this.containingProcessor = containingProcessor;
		this.OF_EX_Latch = oF_EX_Latch;
		this.EX_MA_Latch = eX_MA_Latch;
		this.EX_IF_Latch = eX_IF_Latch;
		this.dataControlUnit=dataControlUnit;
		this.branchControlUnit=branchControlUnit;
	}
	public static int StringToDecimal(String s, int numOfBits){
		String x=convert(s,numOfBits);
		int num=cToDecimal(x);
		return num;
	}
	public static String convert(String s,int numOfBits){
        int len=s.length();
        String temp=s;
        while(len<numOfBits){
            temp="0"+temp;
            len++;
        }
       return temp;
     }
	
	 static int cToDecimal(String s){
		if(s.charAt(0)=='1'){
			String temp="";
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)=='0'){
                    temp=temp+"1";
                }else{
                    temp=temp+"0";
                }
            }
            int x=Integer.parseInt(temp,2);
            return -(x+1);
        }else{
            return Integer.parseInt(s,2);
        }
     }
	
    
	public void performEX()
	{
	if(OF_EX_Latch.isEX_enable()){
		if(dataControlUnit.dontDoAnyExec==false){
		int opcode=OF_EX_Latch.getOpcode();
		boolean branchTakenHere=false;
		if(opcode>=24 && opcode<=28){
			int val=0;
			OF_EX_Latch.setEX_enable(false);
			EX_MA_Latch.setMA_enable(false);
			if(opcode==24){
				String temp=Integer.toBinaryString(OF_EX_Latch.getImm());
				val=StringToDecimal(temp,22);
				int current_pc=containingProcessor.getRegisterFile().getProgramCounter();
				containingProcessor.getRegisterFile().setProgramCounter((current_pc-2)+val);
				branchTakenHere=true;
				System.out.print("updated pc :");
				System.out.println((current_pc-2)+val);
			

			}else if(opcode==25){
				
				if(OF_EX_Latch.get_register1_val()==OF_EX_Latch.get_rdes_val()){
					String temp=Integer.toBinaryString(OF_EX_Latch.getImm());
					val=StringToDecimal(temp,17);
					int current_pc=containingProcessor.getRegisterFile().getProgramCounter();
					containingProcessor.getRegisterFile().setProgramCounter((current_pc-2)+val);
					branchTakenHere=true;
					System.out.print("updated pc :");
					System.out.println((current_pc-2)+val);
				}
				
			}else if(opcode==26){
				
				if(OF_EX_Latch.get_register1_val()!=OF_EX_Latch.get_rdes_val()){
					String temp=Integer.toBinaryString(OF_EX_Latch.getImm());
					val=StringToDecimal(temp,17);
					int current_pc=containingProcessor.getRegisterFile().getProgramCounter();
					containingProcessor.getRegisterFile().setProgramCounter((current_pc-2)+val);
					branchTakenHere=true;
					System.out.print("updated pc :");
					System.out.println((current_pc-2)+val);
				}
			}else if(opcode==27){
				
				if(OF_EX_Latch.get_register1_val()<OF_EX_Latch.get_rdes_val()){
					String temp=Integer.toBinaryString(OF_EX_Latch.getImm());
					val=StringToDecimal(temp,17);
					int current_pc=containingProcessor.getRegisterFile().getProgramCounter();
					containingProcessor.getRegisterFile().setProgramCounter((current_pc-2)+val);
					branchTakenHere=true;
					System.out.print("updated pc :");
					System.out.println((current_pc-2)+val);
				}
				
			}else if(opcode==28){
				
				if(OF_EX_Latch.get_register1_val()>OF_EX_Latch.get_rdes_val()){
					String temp=Integer.toBinaryString(OF_EX_Latch.getImm());
					val=StringToDecimal(temp,17);
					int current_pc=containingProcessor.getRegisterFile().getProgramCounter();
					containingProcessor.getRegisterFile().setProgramCounter((current_pc-2)+val);
					branchTakenHere=true;
					System.out.print("updated pc :");
					System.out.println((current_pc-2)+val-2);
				}
			}
			//*************************************************
			if(branchTakenHere==true){
			
				containingProcessor.getBranchControlUnit().convPrevTwotonull(); 
				branchControlUnit.branchTaken=true;
				System.out.println("execution done , opcode : "+opcode+"reg1 : "+ OF_EX_Latch.get_register1_val()+"rdes : "+OF_EX_Latch.get_rdes_val());
			}else{
				Statistics.setnowrongbranches(Statistics.getnoOfWrongbranches()+1);
		        System.out.println("execution not done , opcode : "+opcode+" reg1 : "+ OF_EX_Latch.get_register1_val()+" rdes : "+OF_EX_Latch.get_rdes_val());
						
			} 
			
		}else{
		
			int val=0;
			switch(opcode){
						case 0:
						val=OF_EX_Latch.get_register1_val()+OF_EX_Latch.get_register2_val();
						break;

						case 1:
						val=OF_EX_Latch.get_register1_val()+OF_EX_Latch.getImm();
						break;

						case 2:
						val=OF_EX_Latch.get_register1_val()-OF_EX_Latch.get_register2_val();
						break;

						case 3:
						val=OF_EX_Latch.get_register1_val()-OF_EX_Latch.getImm();
						break;

						case 4:
						val=OF_EX_Latch.get_register1_val()*OF_EX_Latch.get_register2_val();
						break;

						case 5:
						val=OF_EX_Latch.get_register1_val()*OF_EX_Latch.getImm();
						break;

						case 6:
						val=OF_EX_Latch.get_register1_val()/OF_EX_Latch.get_register2_val();
						int remainder=(OF_EX_Latch.get_register1_val())%(OF_EX_Latch.get_register2_val());
						containingProcessor.getRegisterFile().setValue(31, remainder);
						break;

						case 7:
						val=OF_EX_Latch.get_register1_val()/OF_EX_Latch.getImm();
						int remainder1=(OF_EX_Latch.get_register1_val())%(OF_EX_Latch.getImm());
						containingProcessor.getRegisterFile().setValue(31, remainder1);
						break;

						case 8:
						val=OF_EX_Latch.get_register1_val() & OF_EX_Latch.get_register2_val();
						break;

						case 9:
						val=OF_EX_Latch.get_register1_val() & OF_EX_Latch.getImm();
						break;

						case 10:
						val=OF_EX_Latch.get_register1_val()|OF_EX_Latch.get_register2_val();
						break;

						case 11:
						val=OF_EX_Latch.get_register1_val() | OF_EX_Latch.getImm();
						break;

						case 12:
						val=(OF_EX_Latch.get_register1_val()) ^ OF_EX_Latch.get_register2_val();
						break;

						case 13:
						val=OF_EX_Latch.get_register1_val() ^ OF_EX_Latch.getImm();
						break;

						case 14:
						val=(OF_EX_Latch.get_register1_val()<OF_EX_Latch.get_register2_val())?1:0;
						break;

						case 15:
						val=(OF_EX_Latch.get_register1_val()<OF_EX_Latch.getImm())?1:0;
						break;

						case 16:
						val=OF_EX_Latch.get_register1_val()<<OF_EX_Latch.get_register2_val();
						break;

						case 17:
						val=OF_EX_Latch.get_register1_val()<<OF_EX_Latch.getImm();
						break;

						case 18:
						val=OF_EX_Latch.get_register1_val()>>OF_EX_Latch.get_register2_val();
						break;

						case 19:
						val=OF_EX_Latch.get_register1_val()>>OF_EX_Latch.getImm();
						break;

						case 20:
						val=OF_EX_Latch.get_register1_val()>>>OF_EX_Latch.get_register2_val();
						break;

						case 21:
						val=OF_EX_Latch.get_register1_val()>>>OF_EX_Latch.getImm();
						break;

						case 22://load
						val=OF_EX_Latch.get_register1_val()+OF_EX_Latch.getImm();

						break;

						case 23://store
						
						int des_va=OF_EX_Latch.get_rdes_val();
						String temp=Integer.toBinaryString(OF_EX_Latch.getImm());
						int immediate=StringToDecimal(temp,17);
						val=des_va+immediate;
						break;
						default:
						break;
					
			}

			EX_MA_Latch.setMA_enable(true);
			OF_EX_Latch.setEX_enable(false);

			EX_MA_Latch.setOpcode(opcode);	
			
			if(opcode==22){ //load
				EX_MA_Latch.setReg(OF_EX_Latch.get_rdes());
				EX_MA_Latch.setMemoryAddress(val);
			}if(opcode==23){ //store 
				EX_MA_Latch.setReg(OF_EX_Latch.get_register1());
				EX_MA_Latch.setMemoryAddress(val);
			}else{
				EX_MA_Latch.setReg(OF_EX_Latch.get_rdes());
				EX_MA_Latch.setVal(val);
			}

			if(opcode==29){
				EX_MA_Latch.setOpcode(29);
				OF_EX_Latch.setEX_enable(false);
				dataControlUnit.dontDoAnyExec=true;
			}
			System.out.println("execution done , opcode:"+opcode);
			
		}
	}else{
		EX_MA_Latch.setMA_enable(true);
			OF_EX_Latch.setEX_enable(false);
			dataControlUnit.dontDoAnyMem=true;
			System.out.println("execution fetch , not done anything due to EENNDD");
	}
	}
	}

}
