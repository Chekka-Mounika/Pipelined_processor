package processor.pipeline;

public class MA_RW_LatchType {
	
	boolean RW_enable;
	int reg;
	int val;
	public MA_RW_LatchType()
	{
		RW_enable = false;
	}

	public boolean isRW_enable() {
		return RW_enable;
	}

	public void setRW_enable(boolean rW_enable) {
		RW_enable = rW_enable;
	}
	public void setReg(int reg){
		this.reg=reg;
	}
	public void setVal(int n){
		this.val=n;
	}
	public int getReg(){return reg;}
	public int getVal(){return val;}
	public boolean endInstructions;

}
