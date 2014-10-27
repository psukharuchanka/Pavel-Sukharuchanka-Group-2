package com.epam.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;

import com.epam.beans.Bank;

public class BankWriter implements Runnable {
	static final Logger logger = Logger.getLogger(BankWriter.class);
	
	private File file;
	private ReentrantReadWriteLock lock;
	protected BlockingQueue<Bank> queue;
	
	public BankWriter(File file, ReentrantReadWriteLock lock, BlockingQueue<Bank> queue) {
		super();
		this.file = file;
		this.lock = lock;
		this.queue = queue;
	}

	private void write(Bank bank) {
		lock.writeLock().lock();
		FileOutputStream fout = null;
		ObjectOutputStream out = null;
		try {
			fout = new FileOutputStream(file);
			out = new ObjectOutputStream(fout);
			out.writeObject(bank);
			logger.info("Successfully written");
		} catch (IOException ioe) {
			logger.error("Unable write object to file", ioe);
		} finally {
			try {
				if(fout != null) {
					fout.close();
				}
				if(out != null) {
					out.close();
				}
			} catch(Exception e) {
				logger.error("Unable close streams", e);
			} finally {
				lock.writeLock().unlock();
			}
		}
	}

	public void run() {
		try {
			while(true) {
				logger.info("trying to write...");
				write(queue.take());
			}
		} catch(InterruptedException e) {
			logger.error("Thread was interrapted with errors", e);
		}
	}

}
