package com.prantoran.myfirstgame2;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by pinku on 6/17/16.
 */

//This thread gets started in the surfaceCreated of gamePanel

public class MainThread extends Thread {
    private int FPS = 30;
    private double averageFPS;

    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;  //the main objective of experiment

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run(){ //overriding the run method in class thread
        /*
        * What we are trying to do here, in the gameloop, is to count the fps at 30.
        * */
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount =0;
        long targetTime = 1000/FPS; //This should be in millisec. How long you want. How many millisec you want
        //in each gameloop

        while(running) {
            startTime = System.nanoTime();
            canvas = null;
            //try locking the canvas for pixel editing
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    //The following are the two lines make everything work
                    this.gamePanel.update();  //obviously update() is in the gamePanel class
                    this.gamePanel.draw(canvas);
                    //since fps is 30, the gamepanel will updated and drawn around 30times a second

                }
            }
            catch(Exception e){

            }
            finally{ //always executed regardless the catch
                if(canvas!=null)
                {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch(Exception e){e.printStackTrace();}
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;//how many nanoseconds it took to
            //update and draw the game ones

            waitTime = targetTime-timeMillis;

            try{
                this.sleep(waitTime);
            }catch(Exception e){}

            totalTime += System.nanoTime()-startTime;
            frameCount++;
            if(frameCount == FPS){
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount =0;
                totalTime = 0;
                System.out.println(averageFPS);
            }

        }



    }

    public void setRunning(boolean b){
        running=b;
    }

}
