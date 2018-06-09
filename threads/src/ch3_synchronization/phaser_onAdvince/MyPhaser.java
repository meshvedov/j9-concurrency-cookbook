package ch3_synchronization.phaser_onAdvince;

import java.util.concurrent.Phaser;

public class MyPhaser extends Phaser {

    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase) {
            case 0:
                return studentsArrived();
            case 1:
                return finishFirstExercise();
            case 2:
                return finishSecondExercise();
            case 3:
                return finishExam();
            default:
                return true;
        }
    }

    private boolean finishExam() {
        System.out.printf("Phaser: All the students have finished the exam. \n");
        System.out.printf("Phaser: Thank you for your time.\n");
        return true;
    }

    private boolean finishSecondExercise() {
        System.out.printf("All the students have finished second exercise.\n");
        System.out.printf("Phase: Its time for the third one. \n");
        return false;
    }

    private boolean finishFirstExercise() {
        System.out.printf("Phaser: All the students have finished the first exercise.\n");
        System.out.printf("Phaser: Its time for a second one. \n");
        return false;
    }

    private boolean studentsArrived() {
        System.out.printf("Phaser: The exam is going to start. The students are ready.\n");
        System.out.printf("Phaser: We have %d students. \n", getRegisteredParties());
        return false;
    }


}
