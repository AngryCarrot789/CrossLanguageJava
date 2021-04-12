package reghzy.crosslanguage.serial;

import com.fazecast.jSerialComm.SerialPort;

public class SerialTransmitter {
    private final SerialPort serialPort;

    public boolean canSend;

    public SerialTransmitter(SerialPort serialPort) {
        this.serialPort = serialPort;
        this.canSend = true;
    }

    public boolean sendMessageLine(String message) {
        return sendMessage(message, true);
    }

    public boolean sendMessage(String message, boolean newLine) {
        if (newLine) {
            message += '\n';
        }

        return sendBytes(message.getBytes(), message.length());
    }

    private boolean sendBytes(byte[] buffer, int count) {
        if (canSend) {
            return this.serialPort.writeBytes(buffer, count) > 0;
        }
        return false;
    }
}
