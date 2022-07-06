package design;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

import static sun.misc.PostVMInitHook.run;

public class WaitNotify {


    // 使用wait   notify
    // 使用2个线程，交替打印出来1，2，3，4，5……

    final Object lock = new Object();

    AtomicInteger num = new AtomicInteger(0);


    /**
     * synchronized的原理，多个线程同时抢一把锁，该锁维护一个队列（同步队列），没有争抢到锁的所有线程在同一个同步队列里。
     * wait:该线程释放锁后阻塞，该线程进入另一个队列（等待队列）
     * notify:随机叫醒等待队列中的任何一个线程
     * notifyAll: 唤醒等待队列中的所有线程，让他们去争夺同一把锁，谁争抢到谁执行。
     *
     * 下面t1和t2同时争抢同一把锁：
     *  1.假如t1争抢到锁，则t1执行而t2进入到该锁的同步队列中，
     *  2.t1执行时，执行到notify，这时等待队列中没有线程，继续执行到wait时，释放锁并让t1进入到等待队列，等待notify唤醒
     *  3.由于t1释放锁了，同步队列中的t2就可以获得锁执行，执行到notify时，会随机唤醒等待队列中的一个线程，因为队列中只有一个t1,所以t1去争抢锁；t2执行到wait,t2释放锁并让t2进入到等待队列，等待notify唤醒,t1会获得锁执行。
     *  4.依次循环
     */



    // 方案一：
    // 缺点：1. t1 和 t2 无法保证谁先打印第一个数。    使用countDownLatch来控制
    //      2. 程序不会终止，有一个线程将数字加到31之后，便会阻塞，另外一个线程由于break了，便无法走到唤醒其他线程的代码行。
    //         针对此缺点，在终止条件判断一栏，加上notify，唤醒阻塞的线程
//    public static void main(String[] args) throws InterruptedException {
//        WaitNotify wn = new WaitNotify();
//
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//
//        Thread t1 = new Thread(() -> {
//            while (true) {
//                countDownLatch.countDown(); // 线程t1 将计数器减一为0，以便让t2能够被触发
//                synchronized (wn.lock) {
//                    if (wn.num.get() > 30) {
//                        wn.lock.notify(); // 针对缺点2，清空等待队列，让大家不会被阻塞
//                        break;
//                    }
//                    System.out.println("ThreadName = " + Thread.currentThread() + ", print " + wn.num.getAndIncrement());
//                    try {
//                        wn.lock.notify();// 先唤醒一个正在等待的
//                        wn.lock.wait(); // 然后自己进入阻塞
//                    } catch (InterruptedException e) {
//                    }
//                }
//            }
//        });
//
//        Thread t2 = new Thread(() -> {
//            try {
//                countDownLatch.await();   // t2 先阻塞，确保一定有其他线程countDown将计数器减为了0
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            while (true) {
//                synchronized (wn.lock) {
//                    if (wn.num.get() > 30) {
//                        wn.lock.notify(); // 针对缺点2，清空等待队列
//                        break;
//                    }
//                    System.out.println("ThreadName = " + Thread.currentThread() + ", print " + wn.num.getAndIncrement());
//                    try {
//                        wn.lock.notify();
//                        wn.lock.wait();
//                    } catch (InterruptedException e) {
//                    }
//                }
//            }
//        });
//        t2.start();
//        Thread.sleep(1000 * 5);
//        t1.start();
//    }


    // 方案二   ReentrantLock + Condition
//    public static void main(String[] args) throws InterruptedException {
//        /**
//         * 每一个Condition都有一个队列，cT1的条件队列里有线程t1，cT2的条件队列里有线程t2。
//         * 每个队列里的线程都是通过await释放锁，通过signal唤醒。
//         *
//         * 可以使用多个Condition精确的控制程序的打印.
//         *
//         * 也需要使用CountDownLantch精确的让哪一个线程先执行
//         */
//        ReentrantLock lock = new ReentrantLock();
//        Condition ct1 = lock.newCondition();
//        Condition ct2 = lock.newCondition();
//
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//
//        AtomicInteger num = new AtomicInteger(0);
//
//        Thread t1 = new Thread(() -> {
//            while (true) {
//                countDownLatch.countDown();
//
//                lock.lock();   // 在加锁这一行上面，只有一个线程能加锁成功
//                System.out.println("线程" + Thread.currentThread() + " 打印" + num.getAndIncrement());
//                try {
//                    ct2.signal(); // 通知第二个线程执行
//                    ct1.await(); // 线程一等待
//                } catch (InterruptedException e) {
//                }
//            }
//        }, "T1");
//
//
//        Thread t2 = new Thread(() -> {
//            while (true) {
//                try {
//                countDownLatch.await();
//                lock.lock();   // 在加锁这一行上面，只有一个线程能加锁成功
//                System.out.println("线程" + Thread.currentThread() + " 打印" + num.getAndIncrement());
//
//                    ct1.signal(); // 通知第一个线程执行
//                    ct2.await(); // 线程二等待
//                } catch (InterruptedException e) {
//                }
//            }
//        }, "T2");
//
//        t2.start();
//        Thread.sleep(1000 * 3);
//        t1.start();
//    }


    static Thread t1 = null;
    static Thread t2 = null;
    // 方案三
    // ParkSupport
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger num = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(1);

        t1 = new Thread(() -> {
            while (true) {
                countDownLatch.countDown();
                System.out.println("线程" + Thread.currentThread() + " 打印" + num.getAndIncrement());
                LockSupport.unpark(t2); // 叫醒t2
                LockSupport.park();   // 阻塞当前
            }
        }, "T1");


        t2 = new Thread(() -> {
            while (true) {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                }
                System.out.println("线程" + Thread.currentThread() + " 打印" + num.getAndIncrement());
                LockSupport.unpark(t1); // 叫醒t2
                LockSupport.park();   // 阻塞当前
            }
        }, "T2");

        t2.start();
        Thread.sleep(1000 * 2);
        t1.start();
    }
}
