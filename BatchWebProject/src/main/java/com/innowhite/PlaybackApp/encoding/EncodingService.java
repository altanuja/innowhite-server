package com.innowhite.PlaybackApp.encoding;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class EncodingService {
    private static int startEncodingWorkflow() {
	// replace ID and key with your own
	String userID = "9424";
	String userKey = "6126a344932f3e8e8e00238b6d176dd4";
	String mediaID = "1";
	StringBuffer xml = new StringBuffer();
	xml.append("<?xml version='1.0'?>");
	xml.append("<query>");
	xml.append("<userid>" + userID + "</userid>");
	xml.append("<userkey>" + userKey + "</userkey>");
	xml.append("<action>GetMediaInfo</action>");
	xml.append("<mediaid>" + mediaID + "</mediaid>");
	xml.append("</query>");

	URL server = null;

	try {
	    String url = "http://manage.encoding.com";
	    System.out.println("Connecting to:" + url);
	    server = new URL(url);

	} catch (MalformedURLException mfu) {
	    mfu.printStackTrace();
	    return 0;
	}

	try {
	    String sRequest = "xml=" + URLEncoder.encode(xml.toString(), "UTF8");
	    System.out.println("Open new connection to tunnel");
	    HttpURLConnection urlConnection = (HttpURLConnection) server.openConnection();
	    urlConnection.setRequestMethod("POST");
	    urlConnection.setDoOutput(true);
	    urlConnection.setConnectTimeout(60000);
	    urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
	    out.write(sRequest);
	    out.flush();
	    out.close();
	    urlConnection.connect();
	    InputStream is = urlConnection.getInputStream();
	    String str = urlConnection.getResponseMessage();
	    System.out.println("Response:" + urlConnection.getResponseCode());
	    System.out.println("Response:" + urlConnection.getResponseMessage());
	    StringBuffer strbuf = new StringBuffer();
	    byte[] buffer = new byte[1024 * 4];

	    try {
		int n = 0;
		while (-1 != (n = is.read(buffer))) {
		    strbuf.append(new String(buffer, 0, n));
		}

		is.close();

	    } catch (IOException ioe) {
		ioe.printStackTrace();
	    }

	    System.out.println(strbuf.toString());

	} catch (Exception exp) {
	    exp.printStackTrace();
	}

	return 0;
    }

    public static void main(String[] args) {
	startEncodingWorkflow();
    }

}