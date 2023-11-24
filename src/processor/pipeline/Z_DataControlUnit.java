package processor.pipeline;

import processor.Processor;

public class Z_DataControlUnit {
    Processor containingProcessor;
    IF_EnableLatchType IF_EnableLatch;
	IF_OF_LatchType IF_OF_Latch;
	OF_EX_LatchType OF_EX_Latch;
	EX_MA_LatchType EX_MA_Latch;
	EX_IF_LatchType EX_IF_Latch;
	MA_RW_LatchType MA_RW_Latch;
    public Z_DataControlUnit(Processor containingProcessor,
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
    boolean hault=false;
    int noOfHaults=0;
    public void FindHazard(int rs1, int rs2){
        int exec_desreg=OF_EX_Latch.get_rdes();
        int ma_desreg=EX_MA_Latch.getReg();
        int rw_desreg=MA_RW_Latch.getReg();
        if( (rs1==exec_desreg || rs2==exec_desreg ) &&
                        (OF_EX_Latch.isEX_enable()) && 
                        (OF_EX_Latch.getOpcode()!=23)&&  //store
                        (OF_EX_Latch.getOpcode()!=24)&&  //jmp
                        (OF_EX_Latch.getOpcode()!=25)&&  //beq
                        (OF_EX_Latch.getOpcode()!=26)&&  //bne
                        (OF_EX_Latch.getOpcode()!=27)&&  //blt
                        (OF_EX_Latch.getOpcode()!=28)    //bgt
                         ){ 
            hault=true;
            noOfHaults=1;
            if(rs1==0 && rs2==0){
                hault=false;
            }
           
        }else if( (rs1==ma_desreg || rs2==ma_desreg) && 
                  (EX_MA_Latch.getOpcode()!=23) && 
                  (EX_MA_Latch.isMA_enable()) ){
            hault=true;
            noOfHaults=2;
            if(rs1==0 && rs2==0){
                hault=false;
            }
        }else if((rs1==rw_desreg || rs2==rw_desreg) && 
                 (MA_RW_Latch.isRW_enable())){
            hault=true;
            noOfHaults=3;
            if(rs1==0 && rs2==0){
                hault=false;
            }
        }else{
            hault=false;
        }
    }
    public void FindHazard(int rs1){
          int exec_desreg=OF_EX_Latch.get_rdes();
        int ma_desreg=EX_MA_Latch.getReg();
        int rw_desreg=MA_RW_Latch.getReg();
        if( (rs1==exec_desreg  ) &&
                        (OF_EX_Latch.isEX_enable()) && 
                        (OF_EX_Latch.getOpcode()!=23)&&  //store
                        (OF_EX_Latch.getOpcode()!=24)&&  //jmp
                        (OF_EX_Latch.getOpcode()!=25)&&  //beq
                        (OF_EX_Latch.getOpcode()!=26)&&  //bne
                        (OF_EX_Latch.getOpcode()!=27)&&  //blt
                        (OF_EX_Latch.getOpcode()!=28)    //bgt
                         ){ 
            hault=true;
            noOfHaults=1;
           if(rs1==0 ){
                hault=false;
            }
        }else if( (rs1==ma_desreg ) && 
                  (EX_MA_Latch.getOpcode()!=23) && 
                  (EX_MA_Latch.isMA_enable()) ){
            hault=true;
            noOfHaults=2;
             if(rs1==0 ){
                hault=false;
            }
        }else if((rs1==rw_desreg ) && 
                 (MA_RW_Latch.isRW_enable())){
            hault=true;
            noOfHaults=3;
             if(rs1==0 ){
                hault=false;
            }
        }else{
            hault=false;
        }
    }
    public boolean getHault(){
        return hault;
    }

    public boolean CompletedExec=false;
   
    public void setCompletedExec(boolean x){
         CompletedExec=x;
         IF_EnableLatch.setIF_enable(false);
         IF_OF_Latch.setInstruction(0);
       
    }

    public boolean dontDoAnyInIns=false;
    public boolean dontDoAnyOperand=false;
    public boolean dontDoAnyExec=false;
    public boolean dontDoAnyMem=false;
    
   
    
}
