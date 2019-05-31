package ck.janko.designPattern;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 设计一个单例模式
 * add by nyk
 */
public class Singleton {
    private static ReentrantLock lock = new ReentrantLock();
    private static volatile Singleton singleton = null;

    public Singleton() {
    }

    public static Singleton getSingleton() {
        if (singleton == null) {//使用双重检查锁 来写单例模式
//            synchronized (Signature.class) {
//                if (singleton == null) {
//                    singleton = new Singleton();
//                }
//            }
            lock.lock();//加锁
            if(singleton==null){
                singleton=new Singleton();
            }
            lock.unlock();//一定要手动释放锁
        }
        return singleton;
    }
}
