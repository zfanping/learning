package com.example.demo.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <b>功能：</b>XXX<br>
 * <b>Copyright TCSL</b>
 * <li>版本| 修改日期| 作者| 变更内容</li>
 * <hr>
 * <li>v1.0| 20190401| 曾凡平| 创建</li>
 */
public class ReadWriteLockTest {

    public static void main(String[] args) {
         ReadWriteLock lock  = new ReentrantReadWriteLock();

        lock.readLock().lock();
        System.out.println("get readlock");
        lock.writeLock().lock();
        System.out.println("get wirtelock");
    }
}
