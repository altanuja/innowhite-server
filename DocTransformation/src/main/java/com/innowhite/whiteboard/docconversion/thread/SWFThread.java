package com.innowhite.whiteboard.docconversion.thread;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.innowhite.whiteboard.docconversion.messages.ConversionMessageListener;
import com.innowhite.whiteboard.docconversion.persistence.dao.MessagePersistenceDAO;
import com.innowhite.whiteboard.docconversion.util.DocTransUtil;
import com.innowhite.whiteboard.docconversion.vo.DocConversionBean;
import com.innowhite.whiteboard.docconversion.vo.FileTransformatioBean;

public class SWFThread extends Thread {

    private static final Logger log = LoggerFactory.getLogger(SWFThread.class);

    private DocConversionBean docBean = null;
    private FileTransformatioBean fileTransBean = null;

    private int MAX_SLEEP_TIME = 180000;
    private int sleepCounter = 0;

    private int sleepInterval = 2000;

    static String separator = System.getProperty("file.separator");

    public SWFThread() {
	// TODO Auto-generated constructor stub
    }

    public SWFThread(DocConversionBean docBean, FileTransformatioBean fileTransBean) {
	this.docBean = docBean;
	this.fileTransBean = fileTransBean;
    }

    // private static final Logger LOG = Logger.getLogger(SWFThread.class);

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(SWFThread.class);

    public void run() {
	boolean conversionFlag = false;
	boolean b = false;
	try {

	    createBatchFile();
	    b = invokeProcess();
	    executeSymlinkProcess();
	    while (!conversionFlag) {
		log.debug("conversion Flag......... " + conversionFlag);
		conversionFlag = ConversionMessageListener.hTable.get(docBean.getConversionID());

		sleep(sleepInterval);
		sleepCounter += sleepInterval;

		if (sleepCounter == MAX_SLEEP_TIME)
		    break;

	    }
	    saveImagesToDB(b);

	    LOG.info("IN Thread in Listener: ++++++++++++++++++++ ");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    protected void createBatchFile() {
	LOG.info("ENTER SWF createBatchFile...... ");
	BufferedWriter out = null;
	try {
	    boolean bCreated = false;
	    String originalFilePath = docBean != null ? docBean.getFilePath() : ""; // C:/Documents
										    // and
										    // Settings/Administrator/Desktop/Presentation1.pptx
	    File f = new File(originalFilePath);
	    String swfFolder = f.getParent() + separator + DocTransUtil.SWF; // C:/Documents
									     // and
									     // Settings/Administrator/Desktop/SWF
	    String swfBatFileContent = "";

	    // Batch File Content
	    StringBuffer sb = new StringBuffer();
	    // sb.append(" \"C:/Program Files/Ppt2SwfSDK/Samples/Precompiled/Ppt2SwfSampleCSharpConsole.exe\" ");
	    // sb.append(" \"Dhiraj Peechara - Vireka LLC\" ");
	    // sb.append(" \"d4f0ec7829702966119f20d2a5a74837\" -tc jpg 700 700 ");

	    sb.append(fileTransBean.getActualFileCommand() + " ");

	    sb.append("\"" + swfFolder + "\" ");
	    sb.append("\"" + originalFilePath + "\" ");
	    sb.append(" \"\"");
	    swfBatFileContent = sb.toString();

	    // BatchFile path
	    String originalDir = f.getParent(); // C:/Documents and
						// Settings/Administrator/Desktop

	    String swfBatFilePath = originalDir + separator + "swf.bat"; // C:/Documents
									 // and
									 // Settings/Administrator/Desktop/thumbs.bat

	    fileTransBean.setOriginalFilePath(originalFilePath);
	    fileTransBean.setSwfFolder(swfFolder);
	    fileTransBean.setSwfBatFileContent(swfBatFileContent);
	    fileTransBean.setOriginalDir(originalDir);
	    fileTransBean.setSwfBatFilePath(swfBatFilePath);

	    out = new BufferedWriter(new FileWriter(new File(swfBatFilePath)));
	    out.write(swfBatFileContent);
	    out.close();
	    // C:/Documen and Settings/Administrator/Desktop/s.bat
	    String symlinkBatFilePath = originalDir + separator + "symlink.bat";
	    f = new File(symlinkBatFilePath);
	    sb = new StringBuffer();
	    sb.append("mklink \\j " + originalDir + "/fullimages/" + "  " + originalDir + "/" + fileTransBean.getOriginalFileNameStripped() + "x");
	    out = new BufferedWriter(new FileWriter(f));
	    out.write(sb.toString());
	    out.close();
	    fileTransBean.setSymlinkBatFilePath(symlinkBatFilePath);
	    // Create symlink batch file ..

	} catch (IOException ioException) {
	    LOG.error("createBatchFile " + ioException);
	} catch (Exception exception) {
	    LOG.error("createBatchFile " + exception);
	} finally {
	    try {
		out.close();
	    } catch (Exception e) {
		LOG.error("createBatchFile finally block: " + e);
	    }
	}
	LOG.info(fileTransBean.getClass().toString());
	LOG.info("EXIT createBatchFile........");
    }

    private boolean invokeProcess() {
	boolean bFlag = false;

	int x = -1;
	try {

	    // String command = "C:/sharedFloder/pptx/1021/thumbs.bat";
	    Process child = Runtime.getRuntime().exec(fileTransBean.getSwfBatFilePath());
	    x = child.waitFor();
	    log.debug("" + child.exitValue());

	    // int x = CommandExec.invoke(args);
	    log.debug("x " + x);
	    if (x == 0)
		bFlag = true;
	} catch (Exception e) {
	    LOG.error(" exception ", e.fillInStackTrace());
	}
	return bFlag;
    }

    private boolean executeSymlinkProcess() {
	boolean bFlag = false;
	log.debug(" entered  executeSymlinkProcess ");
	int x = -1;
	try {

	    // String command = "C:/sharedFloder/pptx/1021/thumbs.bat";
	    Process child = Runtime.getRuntime().exec(fileTransBean.getSymlinkBatFilePath());
	    x = child.waitFor();
	    log.debug("" + child.exitValue());

	    // int x = CommandExec.invoke(args);
	    log.debug("x " + x);
	    if (x == 0)
		bFlag = true;
	} catch (Exception e) {
	    LOG.error(" exception ", e.fillInStackTrace());
	}
	return bFlag;
    }

    private boolean saveImagesToDB(boolean bInvoked) {
	LOG.info("ENTER saveThumbnailsToDB........");
	boolean bSavedToDB = false;
	log.debug("bInvoked " + bInvoked);
	MessagePersistenceDAO mdao = new MessagePersistenceDAO();
	if (bInvoked) {
	    String fileArray[] = DocTransUtil.getSortedImagesArr(fileTransBean, DocTransUtil.SWF);
	    for (int i = 0; i < fileArray.length; i++) {
		log.debug(fileArray[i]);
		mdao.updateImageURL(docBean.getConversionID(), docBean.getServerFilePath() + "/" + docBean.getConversionID() + "/" + DocTransUtil.SWF + "/" + fileArray[i], i + 1);

	    }

	    mdao.updateLDCSWFURL(docBean.getConversionID(), DocTransUtil.CREATED);
	    bSavedToDB = true;
	} else {
	    log.debug("Else block ");
	    mdao.updateLDCSWFURL(docBean.getConversionID(), DocTransUtil.ERROR);
	    bSavedToDB = true;
	}
	LOG.info("EXIT saveThumbnailsToDB........");
	return bSavedToDB;
    }

}
