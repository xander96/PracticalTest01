package ro.pub.cs.systems.eim.practicaltest01var03;

import java.util.Date;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.util.Log;


class ProcessingThread extends Thread{
    private Context context = null;
    private boolean isRunning = true;

    private Random random = new Random();

    String _firstText;
    String _secondText;

    public ProcessingThread(Context context, String firstText, String secondText) {
        this.context = context;

        _firstText = firstText;
        _secondText = secondText;
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.actionTypes[random.nextInt(Constants.actionTypes.length)]);
        if (random.nextBoolean()){
            intent.putExtra("message", new Date(System.currentTimeMillis()) + " " +  _firstText);
        }else{
            intent.putExtra("message", new Date(System.currentTimeMillis()) + " " +  _secondText);
        }
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
