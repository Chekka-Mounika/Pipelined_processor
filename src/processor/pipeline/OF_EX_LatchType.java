package processor.pipeline;

public class OF_EX_LatchType {
	
	boolean EX_enable;
	
	public OF_EX_LatchType()
	{
		EX_enable = false;
	}

	public boolean isEX_enable() {
		return EX_enable;
	}

	public void setEX_enable(boolean eX_enable) {
		EX_enable = eX_enable;
	}

	int reg1;
	int reg1_val;
	int reg2;
	int reg2_val;
	int rdes;
	int rdes_val;
	int immediate;
	int opcode;
	int type;
	public void set_register1(int reg_num,int val){
		reg1=reg_num;
		reg1_val=val;
	}
	public void set_register2(int reg_num,int val){
		reg2=reg_num;
		reg2_val=val;
	}
	public void set_dest(int reg_num,int val){
		rdes=reg_num;
		rdes_val=val;
	}
	public void setImm(int n){
		immediate=n;
	}
	public void setOpcode(int n){
		opcode=n;
	}
	public void setType(int n){type=n;}

	
	public int getType(){return type;}
	public int get_register1(){
		return reg1;
	}
	public int get_register1_val(){
		return reg1_val;
	}
	public int get_register2(){
		return reg2;
	}
	public int get_register2_val(){
		return reg2_val;
	}
	public int get_rdes(){
		return rdes;
	}
	public int get_rdes_val(){
		return rdes_val;
	}
	public int getImm(){
		return immediate;
	}
	public int getOpcode(){
		return opcode;
	}

}
