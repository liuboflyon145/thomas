package basic.thread.sync.countdownlatch;

/**
 * Created by liubo on 16/7/19.
 */
public class TestCountDownLatch {
    public static void main(String[] args) {
        VideoConference conference = new VideoConference(10);
        new Thread(conference).start();

        for (int i = 0; i < 10; i++) {
            Participant p = new Participant(conference,"Participant "+i);
            new Thread(p).start();
        }
    }
}
