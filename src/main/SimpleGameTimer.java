package main;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;


public class SimpleGameTimer {

    GamePanel gp;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicLong startTime = new AtomicLong(0);
    private final AtomicLong lastElapsedTime = new AtomicLong(0);
    public Thread timerThread;

    public SimpleGameTimer(GamePanel gp) {
        this.gp = gp;
    }

    public void start() {

        // Завершаем предыдущий поток, если он был
        if (timerThread != null && timerThread.isAlive()) {
            running.set(false);
            timerThread.interrupt();
        }

        // Сбрасываем состояние
        lastElapsedTime.set(0);
        startTime.set(System.currentTimeMillis());
        running.set(true);


        // Создаем новый поток
        timerThread = new Thread(() -> {
            while (running.get() && !Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(10); // Обновление каждые 10 мс
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        timerThread.start();
    }

    public void stop() {
        if (running.get()) {
            lastElapsedTime.set(System.currentTimeMillis() - startTime.get());
            running.set(false);
            timerThread = null;
        }
    }

    public float getElapsedSeconds() {
        return running.get() ?
                (System.currentTimeMillis() - startTime.get()) / 1000f :
                lastElapsedTime.get() / 1000f;
    }

    public String getFormattedTime() {
        return String.format("%.2f", getElapsedSeconds());
    }

    public boolean isRunning() {
        return running.get();
    }

    public void reset() {
            stop();
            lastElapsedTime.set(0);
        }

}