package com.innowhite.mp4converter.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessExecutor {

	public static final Logger log = LoggerFactory
			.getLogger(ProcessExecutor.class);

	public boolean executeProcess(String cmd, 
			HashMap<String, String> videohm, boolean printLog) {

		try {

			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(cmd);

			// any error message?
			StreamGobbler errorGobbler = new StreamGobbler(
					proc.getErrorStream(), "ERR", printLog);

			// any output?
			StreamGobbler outputGobbler = new StreamGobbler(
					proc.getInputStream(), "OUT", printLog);

			// Start the threads
			errorGobbler.start();
			outputGobbler.start();

			// any error???
			// Appears to be blocking operation
			int exitVal = proc.waitFor();
			// log.debug("ExitValue: " + exitVal);

			// TO DO -- This is a temporary fix... need to find a permanent
			// solution.
			while (true) {
				if (errorGobbler.isAlive() || outputGobbler.isAlive()) {
					if (printLog)
						log.debug(" The threads which read the data from the terminal are still alive --------so sleeping for 10 seconds...  ");
					Thread.sleep(2000);
				} else {
					break;
				}
			}

			if (exitVal == 0) {
				return true;
			}

		} catch (Exception t) {
			t.printStackTrace();
			log.error(t.getMessage(), t);
		}

		return false;
	}
}

// Consume process output
class StreamGobbler extends Thread {
	Logger log = LoggerFactory.getLogger(StreamGobbler.class);
	InputStream is;
	String type;
	// PlayBackPlayList pbp;
	HashMap<String, String> videohm;
	boolean printLog = false;

	StreamGobbler(InputStream is, String type,
			 boolean printLog) {
		this.is = is;
		this.type = type;
		
		this.printLog = printLog;
	}

	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
							// log.debug(" starting sleep for the thred ... ");
				// Thread.sleep(10000);
				// log.debug(" Thread woke up");

				while ((line = br.readLine()) != null) {
					// log.debug(" line ::::---- "+line);
					log.debug(" line "+line);		
					
				}
				

			
			// Show output in development
			if (printLog)
				log.debug(type + ">" + line);
		} catch (Exception ioe) {
			log.error("" + ioe.getMessage(), ioe);
			ioe.printStackTrace();
		}
	}

	

	
}