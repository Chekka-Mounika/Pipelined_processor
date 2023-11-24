package processor.pipeline;

import processor.Processor;
import generic.Simulator;
import generic.Statistics;
public class InstructionFetch {
	
	Processor containingProcessor;
	IF_EnableLatchType IF_EnableLatch;
	IF_OF_LatchType IF_OF_Latch;
	EX_IF_LatchType EX_IF_Latch;
	Z_DataControlUnit dataControlUnit;
	Z_BranchControlUnit branchControlUnit;
	
	public InstructionFetch(Processor containingProcessor, IF_EnableLatchType iF_EnableLatch, IF_OF_LatchType iF_OF_Latch, EX_IF_LatchType eX_IF_Latch,Z_DataControlUnit dataControlUnit,Z_BranchControlUnit branchControlUnit)
	{
		this.containingProcessor = containingProcessor;
		this.IF_EnableLatch = iF_EnableLatch;
		this.IF_OF_Latch = iF_OF_Latch;
		this.EX_IF_Latch = eX_IF_Latch;
		this.dataControlUnit=dataControlUnit;
		this.branchControlUnit=branchControlUnit;
	}
	
	public void performIF()
	{
		if(dataControlUnit.getHault()==false)
		{

			if(dataControlUnit.dontDoAnyInIns==true){
				IF_OF_Latch.setOF_enable(true);
				System.out.println("instruction fetch , not done anything due to EENNDD");
			}else{
				Statistics.setNumberOfInstructions(Statistics.getNumberOfInstructions() + 1);
				if(branchControlUnit.branchTakenfn()==true) {
				
				int currentPC = containingProcessor.getRegisterFile().getProgramCounter();
				
				int newInstruction = containingProcessor.getMainMemory().getWord(currentPC);
				System.out.println(" branch takeninstruction fetched pc:=============================================="+currentPC+" instruction:"+newInstruction);
				IF_OF_Latch.setInstruction(newInstruction);
				branchControlUnit.branchTaken=false;
				containingProcessor.getRegisterFile().setProgramCounter(currentPC+1);
				
				}
			
				else{
				int currentPC = containingProcessor.getRegisterFile().getProgramCounter();
				int newInstruction = containingProcessor.getMainMemory().getWord(currentPC);
				System.out.println("instruction fetched pc:============================================="+currentPC+" instruction:"+newInstruction);
				IF_OF_Latch.setInstruction(newInstruction);
				containingProcessor.getRegisterFile().setProgramCounter(currentPC+1);
				
				}
				IF_OF_Latch.setOF_enable(true);
			}
			
			
			
			
			
		}else{
			System.out.println("haulted in instruction fetch");
			IF_OF_Latch.setOF_enable(true);
		}
	}

}
