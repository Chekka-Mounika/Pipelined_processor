package processor.pipeline;

import processor.Processor;

public class MemoryAccess {
	Processor containingProcessor;
	EX_MA_LatchType EX_MA_Latch;
	MA_RW_LatchType MA_RW_Latch;
	Z_DataControlUnit dataControlUnit;
	IF_EnableLatchType IF_EnableLatch;
	public MemoryAccess(Processor containingProcessor, EX_MA_LatchType eX_MA_Latch, MA_RW_LatchType mA_RW_Latch,Z_DataControlUnit dataControlUnit,IF_EnableLatchType IF_EnableLatch)
	{
		this.containingProcessor = containingProcessor;
		this.EX_MA_Latch = eX_MA_Latch;
		this.MA_RW_Latch = mA_RW_Latch;
		this.dataControlUnit=dataControlUnit;
		this.IF_EnableLatch=IF_EnableLatch;
	}
	
	public void performMA()
	{
		if(EX_MA_Latch.isMA_enable()){
		if(dataControlUnit.dontDoAnyMem==false){

		
			if(EX_MA_Latch.getOpcode()==22){//load
				int val=containingProcessor.getMainMemory().getWord(EX_MA_Latch.getMemoryAddress());
				int reg=EX_MA_Latch.getReg();
				MA_RW_Latch.setRW_enable(true);
				EX_MA_Latch.setMA_enable(false);
				MA_RW_Latch.setReg(reg);
				MA_RW_Latch.setVal(val);
				System.out.println("Memory access done opcode:"+EX_MA_Latch.getOpcode()+" reg:"+EX_MA_Latch.getReg()+" val:"+val);
			}
			else if(EX_MA_Latch.getOpcode()==23){//store
				int val_to_set=containingProcessor.getRegisterFile().getValue(EX_MA_Latch.getReg());
				containingProcessor.getMainMemory().setWord(EX_MA_Latch.getMemoryAddress(),val_to_set);
				MA_RW_Latch.setRW_enable(false);
				IF_EnableLatch.setIF_enable(true);
				int current_pc=containingProcessor.getRegisterFile().getProgramCounter();
				//containingProcessor.getRegisterFile().setProgramCounter(current_pc+1);
				System.out.println("Memory access done opcode:"+EX_MA_Latch.getOpcode()+" reg:"+EX_MA_Latch.getReg()+" val:"+val_to_set);

			}else if(EX_MA_Latch.getOpcode()==29){
				MA_RW_Latch.endInstructions=true;
				MA_RW_Latch.setRW_enable(true);
				System.out.println("memory access jump reached");
				dataControlUnit.dontDoAnyMem=true;
				EX_MA_Latch.setMA_enable(false);
			}
			else{
				int reg=EX_MA_Latch.getReg();
				int val=EX_MA_Latch.getVal();
				MA_RW_Latch.setRW_enable(true);
				EX_MA_Latch.setMA_enable(false);
				MA_RW_Latch.setReg(reg);
				MA_RW_Latch.setVal(val);
			}
		}else{
			MA_RW_Latch.setRW_enable(true);
			EX_MA_Latch.setMA_enable(false);
			MA_RW_Latch.endInstructions=true;
			System.out.println("memory  fetch , not done anything due to EENNDD");
		}
		}
	}

}
