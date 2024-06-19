# CPU Simulator for TOY-RISC Architecture

## Table of Contents

- [Problem Statement](#problem-statement)
- [Solution](#solution)
- [Run Instructions](#run-instructions)
- [Code Structure](#code-structure)
  - [Configuration](#configuration)
  - [Generic](#generic)
  - [Main](#main)
  - [Processor](#processor)
    - [Memory System](#memory-system)
    - [Pipeline](#pipeline)
      - [EX_IF_LatchType.java](#ex_if_latchtypejava)
      - [EX_MA_LatchType.java](#ex_ma_latchtypejava)
      - [Execute.java](#executejava)
      - [IF_EnableLatchType.java](#if_enablelatchtypejava)
      - [IF_OF_LatchType.java](#if_of_latchtypejava)
      - [InstructionFetch.java](#instructionfetchjava)
      - [MA_RW_LatchType.java](#ma_rw_latchtypejava)
      - [MemoryAccess.java](#memoryaccessjava)
      - [OF_EX_LatchType.java](#of_ex_latchtypejava)
      - [OperandFetch.java](#operandfetchjava)
      - [RegisterFile.java](#registerfilejava)
      - [RegisterWrite.java](#registerwritejava)
      - [Z_BranchControlUnit.java](#z_branchcontrolunitjava)
      - [Z_DataControlUnit.java](#z_datacontrolunitjava)

## Problem Statement

In the realm of computer architecture, simulating a processor design is essential for understanding its performance and behavior under various conditions. The challenge was to develop a software-based CPU simulator capable of accurately processing instructions according to the TOY-RISC architecture. This involved implementing a 5-staged pipelined processor with robust handling of data and branch interlocks to ensure precise execution.

## Solution

This project addresses the problem by creating a highly efficient CPU simulator in Java, focusing on the TOY-RISC architecture. Key features include:

- **5-Staged Pipelined Processor:** Implemented a pipelined processor model with stages including Instruction Fetch (IF), Operand Fetch (OF), Execute (EX), Memory Access (MA), and Register Write (RW).
- **Latches for Pipeline:** Utilized various latch types (e.g., IF_OF_LatchType, OF_EX_LatchType) to manage data flow between pipeline stages, ensuring smooth and efficient operation.
- **Handling Interlocks:** Implemented mechanisms to handle data hazards and branch hazards using sophisticated control units (e.g., Z_BranchControlUnit, Z_DataControlUnit), ensuring 100% accurate execution of instructions.
- **Configurable Simulation:** Provided flexibility through a configuration file (`config.xml`) to define processor parameters and behavior.

## Run Instructions

To run the CPU simulator:

1. Build the JAR file:
   ```bash
   ant make-jar


## Code Structure
The project is organized into several packages and classes:

## Configuration
Contains classes related to configuration management:
- Configuration.java: Handles loading and parsing of the config.xml file.
## Generic
Contains generic utility classes and core simulation components:
- Instruction.java, Misc.java, Operand.java: Classes for defining and managing instructions and operands.
- Simulator.java: Main class for initializing and running the CPU simulator.
- Statistics.java: Collects and manages statistics during simulation.
## Main
Contains the main entry point of the application:
- main.java: Entry point for starting the CPU simulation.
## Processor
Contains classes related to the CPU and its components:
### Memory System
- MainMemory.java: Simulates the main memory of the processor.
### Pipeline
### EX_IF_LatchType.java
Latch type for managing data flow between Execute (EX) and Instruction Fetch (IF) stages.
### EX_MA_LatchType.java
Latch type for managing data flow between Execute (EX) and Memory Access (MA) stages.
### Execute.java
Implements the Execute (EX) stage of the pipeline.
### IF_EnableLatchType.java
Latch type for managing data flow between Instruction Fetch (IF) and Enable stages.
### IF_OF_LatchType.java
Latch type for managing data flow between Instruction Fetch (IF) and Operand Fetch (OF) stages.
### InstructionFetch.java
Implements the Instruction Fetch (IF) stage of the pipeline.
### MA_RW_LatchType.java
Latch type for managing data flow between Memory Access (MA) and Register Write (RW) stages.
### MemoryAccess.java
Implements the Memory Access (MA) stage of the pipeline.
### OF_EX_LatchType.java
Latch type for managing data flow between Operand Fetch (OF) and Execute (EX) stages.
### OperandFetch.java
Implements the Operand Fetch (OF) stage of the pipeline.
### RegisterFile.java
Manages the register file operations.
### RegisterWrite.java
Implements the Register Write (RW) stage of the pipeline.
### Z_BranchControlUnit.java
Control unit for managing branch hazards.
### Z_DataControlUnit.java
Control unit for managing data hazards.

---

These files collectively define the structure and behavior of the pipelined processor in the CPU simulator, ensuring efficient and accurate execution of instructions.






