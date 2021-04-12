package reghzy.crosslanguage.serial;

import com.fazecast.jSerialComm.SerialPort;

public class SerialTransceiver {
    public final SerialTransmitter transmitter;
    private final SerialReceiver receiver;
    private final SerialPort serialPort;

    public SerialTransceiver(String portDescriptor, IMessageListener listener) {
        this.serialPort = SerialPort.getCommPort(portDescriptor);
        this.serialPort.setBaudRate(9600);
        this.serialPort.setNumDataBits(8);
        this.serialPort.setNumStopBits(1);
        this.transmitter = new SerialTransmitter(this.serialPort);
        this.receiver = new SerialReceiver(this.serialPort, listener);
        this.receiver.startThread();
    }

    public void connect() {
        this.serialPort.openPort();
        this.receiver.resume();
    }

    public void disconnect() {
        this.receiver.pause();
        this.serialPort.closePort();
    }
}
