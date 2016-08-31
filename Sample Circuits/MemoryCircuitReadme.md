# How to simulate memory

- Top left power switches are the bits you want to save into memory (top switch corresponds to top LED, and bottom switch corresponds to bottom LED)
- The other two power switches are the memory slots. Only 01, 10, and 11 are hooked up to a memory location, so 00 will not have anything.
- The main switch in the bottom middle of the circuit is used for read/write. On means read, and off means write.

### How to write into memory

1. Set the read/write switch to read mode (ON)
2. Select which slot you want to write in using the bottom 2 power switches. Remember, 00 is not a valid slot.
3. Select what you want to save into the memory slot using the top left two power switches.
4. Set the read/write switch to write mode (OFF)
5. Those 2 bits will be written into that memory slot now.

### How to read memory

1. Set the read/write switch to read mode (ON)
2. Select which slot you want to read from using the bottom 2 power switches. Remember, 00 is not a valid slot.
3. The bits stored into that memory slot should now appear in the output LEDs.
