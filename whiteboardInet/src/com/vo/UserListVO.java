package com.vo;

public class UserListVO {

	String username;
	String textcolor;
	int shapeCount;
	boolean groupLeader;
	String objName;
	String myStatus;
	int loggedInTime;
	boolean userDataSynch;
	

	
	

	int whiteboardDrawRights;
	int audioRights;
	int videoRights;


	
	


	public int getWhiteboardDrawRights() {
		return whiteboardDrawRights;
	}

	public void setWhiteboardDrawRights(int whiteboardDrawRights) {
		this.whiteboardDrawRights = whiteboardDrawRights;
	}

	public int getAudioRights() {
		return audioRights;
	}

	public void setAudioRights(int audioRights) {
		this.audioRights = audioRights;
	}

	public int getVideoRights() {
		return videoRights;
	}

	public void setVideoRights(int videoRights) {
		this.videoRights = videoRights;
	}

	// ability to create a new whiteboard
	boolean newWhiteboardRights;

	// who ever has text editor rights can modify it.
	boolean textEditorRights;
	boolean handRaise;
	// any one can grab and start talking.
	boolean audioOpenAccess;

	public boolean isUserDataSynch() {

		return userDataSynch;
	}

	public void setUserDataSynch(boolean userDataSynch) {
	//	log.debug(" setting setUserDataSynch  ::" + userDataSynch);
		this.userDataSynch = userDataSynch;
	}

	public int getLoggedInTime() {
		return loggedInTime;
	}

	public void setLoggedInTime(int loggedInTime) {
		this.loggedInTime = loggedInTime;
	}

	public String getMyStatus() {
		return myStatus;
	}

	public void setMyStatus(String myStatus) {
		this.myStatus = myStatus;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public boolean isGroupLeader() {
		return groupLeader;
	}

	public void setGroupLeader(boolean groupLeader) {
		this.groupLeader = groupLeader;
	}

	public boolean getNewWhiteboardRights() {
		return newWhiteboardRights;
	}

	public void setNewWhiteboardRights(boolean newWhiteboardRights) {
		this.newWhiteboardRights = newWhiteboardRights;
	}

	public boolean getTextEditorRights() {
		return textEditorRights;
	}

	public void setTextEditorRights(boolean textEditorRights) {
		this.textEditorRights = textEditorRights;
	}

	public boolean getHandRaise() {
		return handRaise;
	}

	public void setHandRaise(boolean handRaise) {
		this.handRaise = handRaise;
	}

	public boolean getAudioOpenAccess() {
		return audioOpenAccess;
	}

	public void setAudioOpenAccess(boolean audioOpenAccess) {
		this.audioOpenAccess = audioOpenAccess;
	}

	public int getShapeCount() {
		return shapeCount;
	}

	public void setShapeCount(int shapeCount) {
		this.shapeCount = shapeCount;
	}

	public String getTextcolor() {
		return textcolor;
	}

	public void setTextcolor(String textcolor) {
		this.textcolor = textcolor;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}