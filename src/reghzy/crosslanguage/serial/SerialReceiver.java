package reghzy.crosslanguage.serial;

import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class SerialReceiver {
    private final SerialPort serialPort;
    private final IMessageListener listener;

    private Thread receiverThread;
    private ReceiverMain receiver;

    public SerialReceiver(SerialPort serialPort, IMessageListener listener) {
        this.serialPort = serialPort;
        this.listener = listener;
        this.receiver = new ReceiverMain(this.serialPort);
        this.receiverThread = new Thread(this.receiver);
    }

    public void startThread() {
        if (this.receiver.isEnabled) {
            System.err.println("Receiver thread already started, but something tried to ");
            return;
        }

        this.receiver.isEnabled = true;
        this.receiverThread.start();
    }

    public void shutdownThread() {
        if (this.receiver.isEnabled) {
            this.receiver.isEnabled = false;
            return;
        }

        System.out.println("Attempted to stop the receiver thread when it hasn't started yet");
    }

    public void pause() {
        this.receiver.isEnabled = false;
    }

    public void resume() {
        this.receiver.isEnabled = true;
    }

    private class ReceiverMain implements Runnable {
        public boolean isEnabled;
        public boolean shutdownThread;
        public Reader reader;
        private final SerialPort serialPort;

        public ReceiverMain(SerialPort serialPort) {
            this.serialPort = serialPort;
        }

        @Override
        public void run() {
            StringBuilder stringBuffer = new StringBuilder(512);
            char input;

            this.reader = new InputStreamReader(serialPort.getInputStream());
            while(true) {
                if (shutdownThread) {
                    return;
                }

                if (isEnabled) {
                    if (serialPort.isOpen()) {
                        try {
                            while (reader.ready()) {
                                input = (char) reader.read();
                                if (input == '\n') {
                                    listener.onLineReceived(stringBuffer.toString());
                                    stringBuffer.setLength(0);
                                }
                                else {
                                    stringBuffer.append(input);
                                }
                            }
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    sleep();
                }
            }
        }

        public void sleep() {
            try {
                Thread.sleep(5);
            }
            catch (InterruptedException e) {

            }
        }
    }
}
