package com.sata.others.learning;

public class JavaThreadAnyWhere {
    public static void main(String[] args) {
        //get current thread
        Thread currentThread = Thread.currentThread();

        //get current thread name
        String currentThreadName = currentThread.getName();
        System.out.println(String.format("The main method was executed by thread: %s", currentThreadName));
        Helper helper = new Helper("java thread Anywhere");
        helper.run();

        Thread t = new Thread(helper);
        t.start();
    }

    static class Helper implements Runnable {
        private final String message;
        public Helper(String message) {
            this.message = message;
        }
        private void doSomthing(String message) {
            //get current thread
            Thread currentThread = Thread.currentThread();

            //get current threan name
            String currentThreadName = currentThread.getName();
            System.out.println(String.format("The doSomeThing method was executed by thread: %s", currentThreadName));
            System.out.println("Do something with " + message);
        }
        @Override
        public void run() {
            doSomthing(message);
        }
    }
}
