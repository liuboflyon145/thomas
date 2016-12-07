package basic.thread.sync.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by liubo on 16/7/19.
 */
public class VideoConference implements Runnable {
    private final CountDownLatch controller;

    public VideoConference(int num) {
        controller = new CountDownLatch(num);
    }

    @Override
    public void run() {
        System.out.println("此次参加会议人员个数:"+controller.getCount());
        try {
            controller.await();
            System.out.println("所有人员已到齐,会议马上开始。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void arrive(String name){
        System.out.println(name+" 已进入视频会议室.");
        controller.countDown();
        System.out.println("未到会者还有: "+controller.getCount()+" 人");
    }
}
