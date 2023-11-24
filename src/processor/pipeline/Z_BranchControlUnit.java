package processor.pipeline;

import processor.Processor;

public class Z_BranchControlUnit {
    Processor containingProcessor;
    IF_EnableLatchType IF_EnableLatch;
	IF_OF_LatchType IF_OF_Latch;
	OF_EX_LatchType OF_EX_Latch;
	EX_MA_LatchType EX_MA_Latch;
	EX_IF_LatchType EX_IF_Latch;
	MA_RW_LatchType MA_RW_Latch;
    public boolean branchTaken=false;
    public Z_BranchControlUnit(Processor containingProcessor,
                       IF_EnableLatchType IF_EnableLatch,
                        IF_OF_LatchType IF_OF_Latch,
                        OF_EX_LatchType OF_EX_Latch,
                        EX_MA_LatchType EX_MA_Latch,
                        EX_IF_LatchType EX_IF_Latch,
                        MA_RW_LatchType MA_RW_Latch){
            this.containingProcessor=containingProcessor;
            this.IF_EnableLatch=IF_EnableLatch;
            this.IF_OF_Latch=IF_OF_Latch;
            this.OF_EX_Latch=OF_EX_Latch;
            this.EX_MA_Latch=EX_MA_Latch;
            this.EX_IF_Latch=EX_IF_Latch;
            this.MA_RW_Latch=MA_RW_Latch;

    }
    public void convPrevTwotonull(){
        IF_OF_Latch.setInstruction(0);
        OF_EX_Latch.set_register1(0, 0);
        OF_EX_Latch.set_register2(0, 0);
        OF_EX_Latch.setImm(0);
        OF_EX_Latch.setOpcode(0);
    }
    public boolean branchTakenfn(){
        return branchTaken;
    }
}
