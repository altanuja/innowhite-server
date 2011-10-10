package com.innowhite.PlaybackApp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.innowhite.PlaybackApp.model.VideoData;
import com.innowhite.PlaybackApp.util.PlaybackVO;
import com.innowhite.PlaybackApp.util.ProcessExecutor;

public class PreProcessFLV {

    private static final Logger log = LoggerFactory.getLogger(PreProcessFLV.class);

    public PreProcessFLV() {

    }

    public static void main(String[] args) {
	new PreProcessFLV();
    }

    public static void processFLV(List<VideoData> videoDataList, PlaybackVO playbackVO) {

	for (VideoData vData : videoDataList) {

	    String flvPath = vData.getFilePath();
	    
	    long duration = (vData.getEndTime().getTime() - vData.getStartTime().getTime());
	    
	    log.debug("entered pre processing of FLV file for " + flvPath+"  video type "+vData.getVideoType()+"  video actual duration "+duration);

	    String command = "ruby /opt/InnowhiteData/scripts/Transcoder/transcoder.rb " + flvPath+" "+vData.getVideoType()+" "+duration;

	    ProcessExecutor pe = new ProcessExecutor();
	    // MakeExectuable obj = new MakeExectu

	    boolean val = pe.executeProcess(command, playbackVO.getTempLocation());

	    log.debug(" the script that is  exeucted  ::" + command + " and the return val is " + val);

	    command = "flvtool2 -U " + flvPath;

	    val = pe.executeProcess(command, "/opt/InnowhiteData/scripts/Transcoder/");
	    
	    log.debug(" the script that is  exeucted  ::" + command + " and the return val is " + val);

	}

    }

}