package basic.thread.sync.phaser;

import java.util.concurrent.Phaser;

/**
 * Created by liubo on 16/7/19.
 */
public class TestPhaser {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        FileSearch sys = new FileSearch("/usr/local/var/log","log",phaser);
        FileSearch app = new FileSearch("/Users/liubo/Applications","log",phaser);
        FileSearch other = new FileSearch("/Users/liubo/Documents","log",phaser);
        Thread sysThread = new Thread(sys,"System");
        sysThread.start();
        Thread appThread = new Thread(app,"application");
        appThread.start();
        Thread otherThread = new Thread(other,"other");
        otherThread.start();

        try {
            sysThread.join();
            appThread.join();
            otherThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Terminated: "+phaser.isTerminated());
    }
}
