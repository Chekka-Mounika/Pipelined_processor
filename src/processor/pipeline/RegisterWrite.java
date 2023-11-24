package processor.pipeline;

import generic.Simulator;
import processor.Processor;

public class RegisterWrite {
	Processor containingProcessor;
	MA_RW_LatchType MA_RW_Latch;
	IF_EnableLatchType IF_EnableLatch;
	EX_IF_LatchType ex_IF_Latch;
	public RegisterWrite(Processor containingProcessor,
	IF_EnableLatchType IF_EnableLatch,
	 IF_OF_LatchType IF_OF_Latch,
	 OF_EX_LatchType OF_EX_Latch,
	 EX_MA_LatchType EX_MA_Latch,
	 EX_IF_LatchType EX_IF_Latch,
	 MA_RW_LatchType MA_RW_Latch)
	{
		this.containingProcessor = containingProcessor;
		this.MA_RW_Latch = MA_RW_Latch;
		this.IF_EnableLatch = IF_EnableLatch;
		this.ex_IF_Latch=EX_IF_Latch;
	}
	
	public void performRW()
	{
		if(MA_RW_Latch.isRW_enable())
		{
			int reg=MA_RW_Latch.getReg();
			int val=MA_RW_Latch.getVal();
			containingProcessor.getRegisterFile().setValue(reg, val);
			MA_RW_Latch.setRW_enable(false);
			System.out.println("Register write done reg:"+reg+" val:"+val);
		}else{
			
			if(containingProcessor.cycle_number==1){
				MA_RW_Latch.setRW_enable(false);
				IF_EnableLatch.setIF_enable(true);
			}
			
		}

		if(MA_RW_Latch.endInstructions==true){
			Simulator.setSimulationComplete(true);
			System.out.println("DONNEE EENNDD");
		}
		
	}

}
