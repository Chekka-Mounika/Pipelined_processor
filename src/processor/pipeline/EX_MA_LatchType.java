package processor.pipeline;

public class EX_MA_LatchType {
	
	boolean MA_enable;
	int opcode;
	int reg;
	int val;
	int MemoryAddress;
	public EX_MA_LatchType()
	{
		MA_enable = false;
	}

	public boolean isMA_enable() {
		return MA_enable;
	}

	public void setMA_enable(boolean mA_enable) {
		MA_enable = mA_enable;
	}
	public void setOpcode(int n){
		opcode=n;
	}
	public int getOpcode(){return opcode;}
	public void setReg(int n){
		reg=n;
	}
	public int getReg(){return reg;}
	public void setVal(int valu){val=valu;}
	public int getVal(){return val;}
	public void setMemoryAddress(int n){
		MemoryAddress=n;
	}
	public int getMemoryAddress(){return MemoryAddress;}
}
