package processor.pipeline;

import processor.Processor;
import generic.Simulator;
import generic.Statistics;
public class OperandFetch {
	Processor containingProcessor;
	IF_OF_LatchType IF_OF_Latch;
	OF_EX_LatchType OF_EX_Latch;
	Z_DataControlUnit dataControlUnit;
	Z_BranchControlUnit branchControlUnit;
	
	public OperandFetch(Processor containingProcessor, IF_OF_LatchType iF_OF_Latch, OF_EX_LatchType oF_EX_Latch ,Z_DataControlUnit dataControlUnit,Z_BranchControlUnit branchControlUnit)
	{
		this.containingProcessor = containingProcessor;
		this.IF_OF_Latch = iF_OF_Latch;
		this.OF_EX_Latch = oF_EX_Latch;
		this.dataControlUnit=dataControlUnit;
		this.branchControlUnit=branchControlUnit;
	}
	
	public void performOF()
	{
		
			
		if(IF_OF_Latch.isOF_enable()){
		
		if(dataControlUnit.dontDoAnyOperand==false){
			int instruc=IF_OF_Latch.getInstruction();
			int rs1=0;
			int rs2=0;
			int rd=0;
			int imme=0;
			int type=0;
			int opcode = (instruc >> 27) & 0b11111;
			if(0<=opcode && opcode<=21){
				if(opcode%2==0){
					type=3;
					 rs1 = (instruc >> 22) & 0b11111;
					 rs2 = (instruc >> 17) & 0b11111;
					 rd = (instruc >> 12) & 0b11111;
				}else{
					type=2;
					 rs1 = (instruc >> 22) & 0b11111;
					 rd = (instruc >> 17) & 0b11111;
					 imme = instruc & 0x1FFFF;
				}
			}
			else if(opcode==22 || opcode==23){
				type=2;
				 rs1 = (instruc >> 22) & 0b11111;
				 rd = (instruc >> 17) & 0b11111;
				imme = instruc & 0x1FFFF;
			}else if(opcode==24){
					type=1;
					 rd = (instruc >> 22) & 0b11111;	
					 imme=instruc & 0b1111111111111111111111;
			}else if(opcode>=25 && opcode<=28){
				type=2;
				 rs1 = (instruc >> 22) & 0b11111;
				 rd = (instruc >> 17) & 0b11111;
				 imme = instruc & 0x1FFFF;
			}


			Z_DataControlUnit dc=containingProcessor.getDataControlUnit();
			//**********************************************************************************
			//**********************************************************************************
			if( (0<=opcode && opcode<=21)  && (opcode%2==0)){
				dc.FindHazard(rs1,rs2);
			}else if((0<=opcode && opcode<=21)  && (opcode%2!=0)){
				dc.FindHazard(rs1);
			}else if( (opcode>=25 && opcode<=28) || (opcode==23)){
				dc.FindHazard(rs1,rd);
			}else if(opcode==22){
				dc.FindHazard(rs1);
			}
			
			//**********************************************************************************
			//**********************************************************************************
			if(dc.hault==true){
				Statistics.setNoHaults(Statistics.getNoHaults()+1);
				IF_OF_Latch.setOF_enable(false);
				OF_EX_Latch.setEX_enable(false);
				dc.noOfHaults=dc.noOfHaults-1;
				System.out.println("haulted in operation fetch opcode:"+opcode+"rs1:"+rs1+" rs2:"+rs2+" rd:"+rd+" imm:"+imme);
			}else{
			//=============================================================================
				OF_EX_Latch.set_register1(rs1, containingProcessor.getRegisterFile().getValue(rs1));
				OF_EX_Latch.set_register2(rs2, containingProcessor.getRegisterFile().getValue(rs2));
				OF_EX_Latch.set_dest(rd, containingProcessor.getRegisterFile().getValue(rd));
				OF_EX_Latch.setImm(imme);
				OF_EX_Latch.setType(type);
				OF_EX_Latch.setOpcode(opcode);

				IF_OF_Latch.setOF_enable(false);
				OF_EX_Latch.setEX_enable(true);
				if(opcode==29){
					OF_EX_Latch.setOpcode(29);
					OF_EX_Latch.setEX_enable(true);
					//dataControlUnit.setCompletedExec(true);
					System.out.print("end reached operand fetched ");
					//IF_OF_Latch.setOF_enable(false);
					dataControlUnit.dontDoAnyOperand=true;
					dataControlUnit.dontDoAnyInIns=true;
					dataControlUnit.dontDoAnyExec=true;
				}	
				System.out.println("operandfetched ,   opcode:"+opcode+" rs1: "+rs1+" rs2: "+rs2+" rd: "+rd+" imm: "+imme);
			//=================================================================================
			}
		}else{
			OF_EX_Latch.setEX_enable(true);
			IF_OF_Latch.setOF_enable(false);
			System.out.println("operand fetch , not done anything due to EENNDD");
		}
		}

			

		
	}

}
