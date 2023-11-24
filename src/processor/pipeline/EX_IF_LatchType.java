package processor.pipeline;
import processor.Processor;
public class EX_IF_LatchType {
	int updatedPc;
	boolean isEnable;
	public EX_IF_LatchType()
	{
		isEnable=false;
	}
	public void setEX_IF_enable(boolean x){this.isEnable=x;}
	public boolean isEX_IF_enable(){return isEnable;}
}
