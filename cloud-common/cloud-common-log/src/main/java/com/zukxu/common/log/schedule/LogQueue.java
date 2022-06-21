package com.zukxu.common.log.schedule;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * <p>
 * 日志队列
 * </p>
 *
 * @author xupu
 * @since 2022/6/21 16:51
 */
@Component
public class LogQueue implements Serializable {

    private LinkedList<String> list = new LinkedList<>();

    private final int minSize = 0;

    private final int maxSize = 2000;

    private final Object lock = new Object();

    public void put(String log) {
        synchronized(lock) {
            while(size() == this.maxSize) {
                try {
                    lock.wait();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(log);
            lock.notify();
        }
    }

    public int size() {
        return list.size();
    }

    public String take() {
        String res;
        synchronized(lock) {
            while(size() == this.minSize) {
                try {
                    lock.wait();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
            res = list.removeFirst();
            lock.notify();
        }
        return res;
    }

}
