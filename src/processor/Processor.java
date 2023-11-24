package processor;

import processor.memorysystem.MainMemory;
import processor.pipeline.EX_IF_LatchType;
import processor.pipeline.EX_MA_LatchType;
import processor.pipeline.Execute;
import processor.pipeline.IF_EnableLatchType;
import processor.pipeline.IF_OF_LatchType;
import processor.pipeline.InstructionFetch;
import processor.pipeline.MA_RW_LatchType;
import processor.pipeline.MemoryAccess;
import processor.pipeline.OF_EX_LatchType;
import processor.pipeline.OperandFetch;
import processor.pipeline.RegisterFile;
import processor.pipeline.RegisterWrite;
import processor.pipeline.Z_BranchControlUnit;
import processor.pipeline.Z_DataControlUnit;

public class Processor {
	
	RegisterFile registerFile;
	MainMemory mainMemory;
	
	IF_EnableLatchType IF_EnableLatch;
	IF_OF_LatchType IF_OF_Latch;
	OF_EX_LatchType OF_EX_Latch;
	EX_MA_LatchType EX_MA_Latch;
	EX_IF_LatchType EX_IF_Latch;
	MA_RW_LatchType MA_RW_Latch;
	
	InstructionFetch IFUnit;
	OperandFetch OFUnit;
	Execute EXUnit;
	MemoryAccess MAUnit;
	RegisterWrite RWUnit;
	Z_DataControlUnit DataCUnit;
	Z_BranchControlUnit BranchCUnit;

	public int cycle_number=1;
	public Processor()
	{
		registerFile = new RegisterFile();
		mainMemory = new MainMemory();
		
		IF_EnableLatch = new IF_EnableLatchType();
		IF_OF_Latch = new IF_OF_LatchType();
		OF_EX_Latch = new OF_EX_LatchType();
		EX_MA_Latch = new EX_MA_LatchType();
		EX_IF_Latch = new EX_IF_LatchType();
		MA_RW_Latch = new MA_RW_LatchType();
		
		DataCUnit=new Z_DataControlUnit(this, IF_EnableLatch, IF_OF_Latch, OF_EX_Latch, EX_MA_Latch, EX_IF_Latch, MA_RW_Latch);
		BranchCUnit=new Z_BranchControlUnit(this, IF_EnableLatch, IF_OF_Latch, OF_EX_Latch, EX_MA_Latch, EX_IF_Latch, MA_RW_Latch);

		IFUnit = new InstructionFetch(this, IF_EnableLatch, IF_OF_Latch, EX_IF_Latch,DataCUnit,BranchCUnit);
		OFUnit = new OperandFetch(this, IF_OF_Latch, OF_EX_Latch,DataCUnit,BranchCUnit);
		EXUnit = new Execute(this, OF_EX_Latch, EX_MA_Latch, EX_IF_Latch,DataCUnit,BranchCUnit);
		MAUnit = new MemoryAccess(this, EX_MA_Latch, MA_RW_Latch,DataCUnit,IF_EnableLatch);
		RWUnit = new RegisterWrite(this, IF_EnableLatch, IF_OF_Latch, OF_EX_Latch, EX_MA_Latch, EX_IF_Latch, MA_RW_Latch);
	}
	
	public void printState(int memoryStartingAddress, int memoryEndingAddress) 
	{
		System.out.println(registerFile.getContentsAsString());
		
		System.out.println(mainMemory.getContentsAsString(memoryStartingAddress, memoryEndingAddress));		
	}

	public RegisterFile getRegisterFile() {
		return registerFile;
	}

	public void setRegisterFile(RegisterFile registerFile) {
		this.registerFile = registerFile;
	}

	public MainMemory getMainMemory() {
		return mainMemory;
	}

	public void setMainMemory(MainMemory mainMemory) {
		this.mainMemory = mainMemory;
	}

	public InstructionFetch getIFUnit() {
		return IFUnit;
	}

	public OperandFetch getOFUnit() {
		return OFUnit;
	}

	public Execute getEXUnit() {
		return EXUnit;
	}

	public MemoryAccess getMAUnit() {
		return MAUnit;
	}

	public RegisterWrite getRWUnit() {
		return RWUnit;
	}

	public Z_DataControlUnit getDataControlUnit(){
		return DataCUnit;
	}
	public Z_BranchControlUnit getBranchControlUnit(){
		return BranchCUnit;
	}

}
