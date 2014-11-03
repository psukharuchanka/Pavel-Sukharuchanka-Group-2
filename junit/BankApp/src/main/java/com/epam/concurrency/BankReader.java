package com.epam.concurrency;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;

import com.epam.beans.Bank;

public class BankReader {
	static final Logger logger = Logger.getLogger(BankReader.class);
	private File file;
	private ReentrantReadWriteLock lock;
	
	public BankReader(File file, ReentrantReadWriteLock lock) {
		super();
		this.file = file;
		this.lock = lock;
	}
	
	public Bank read() {
		lock.readLock().lock();
		Bank bank = null;
		FileInputStream fin = null;
		ObjectInputStream in = null;
		try {
			fin = new FileInputStream(file);
			in = new ObjectInputStream(fin);
			bank = (Bank) in.readObject();
		} catch(IOException ioe) {
			logger.error("Unable read object", ioe);
		} catch(ClassNotFoundException e) {
			logger.error("Class Bank not found", e);
		} finally {
			try {
				if(fin != null) {
					fin.close();
				}
				if(in != null) {
					in.close();
				}
			} catch(Exception e) {
				logger.error("Unable close streams", e);
			} finally {
				lock.readLock().unlock();
			}
		}
		return bank;
	}
}
