import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.LinkedList;

public class Q1 {
    static int NUM_OF_CHAIRS = 3;
    public static void main(String[] args) throws InterruptedException {
        Ta.getInstance();
        Ta.getInstance().start();
        Thread.sleep(1000);
        Student student1 = new Student();
        student1.start();

    }
}

class Ta extends Thread {

    private static Ta instance = null;

    final ReentrantLock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Queue<Student> waittingStudents = new LinkedList<>();
    volatile int queue_size = 0;

    public static Ta getInstance()
    {
        if (instance == null)
            instance = new Ta();

        return instance;
    }

    synchronized int getWaittingListSize() {
        System.out.println("waiting list size " + queue_size);
        return queue_size;
    }

    public void enqueue(Student st) throws InterruptedException {
        lock.lock();
        try
        {

            if (queue_size < 3) {
                this.waittingStudents.add(st);
                queue_size++;
                notEmpty.signalAll();

            }
            else {
                System.out.println("cannot empty it ...");
            }
        }
        finally {
            lock.unlock();
        }

    }

    public Student dequeue() throws InterruptedException {
        Student st = null;
        lock.lock();
        try
        {
            while (queue_size == 0) {
                notEmpty.await();
                System.out.println("dequeue after await queue size " + queue_size);
                if (this.waittingStudents.size() == 0) continue;
            }

            st = this.waittingStudents.peek();
            if (st != null)
            {
                this.waittingStudents.remove();
                queue_size--;
            }
            notFull.signalAll();
        }
        finally {
            lock.unlock();
        }

        return st;
    }


    void helpStudent(Student st) throws InterruptedException {
        System.out.println("helping student");
        Thread.sleep(500);
    }

    public void WaitUntilNotFull() throws InterruptedException {
        lock.lock();
        try
        {
            while (queue_size >= 3) {
                notFull.await();
                System.out.println("waked up queue size " + queue_size);
            }
        }
        finally {
            lock.unlock();
        }
    }

    @Override
        public void run() {

        while (true) {
            try {
                System.out.println("Sleeping ZZZ...");
                Student st = dequeue();
                helpStudent(st);
                int len = this.waittingStudents.size();
                System.out.println("len : " + len);
            }
            catch (InterruptedException e) {

            }

        }
    }
}

class Student extends Thread {

    @Override
        public void run() {

        while (true) {
            try {
                System.out.println("enter Student thread");
                if (Ta.getInstance().getWaittingListSize() < 3)
                {
                    Ta.getInstance().enqueue(new Student());

                }
                else {
                    System.out.println("programming...");
                    Ta.getInstance().WaitUntilNotFull();
                    System.out.println("wake up after programming...");
                }

            }
            catch (InterruptedException e) {

            }

        }
    }

}
