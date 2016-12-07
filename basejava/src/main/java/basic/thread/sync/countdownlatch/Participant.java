package basic.thread.sync.countdownlatch;

/**
 * Created by liubo on 16/7/19.
 */
public class Participant implements Runnable {
    private VideoConference conference;
    private String name;

    public Participant(VideoConference conference, String name) {
        this.conference = conference;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long) (Math.random()*100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        conference.arrive(name);
    }
}
