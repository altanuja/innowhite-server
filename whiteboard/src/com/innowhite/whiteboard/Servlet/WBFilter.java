package com.innowhite.whiteboard.Servlet;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;

import com.innowhite.whiteboard.persistence.dao.WhiteboardAuthenticationDAOImpl;
import com.innowhite.whiteboard.service.BeanService;
import com.innowhite.whiteboard.service.WhiteboardAuthenticatorService;
import com.innowhite.whiteboard.util.InnowhiteConstants;
import com.util.Constants;
import com.util.InnowhiteProperties;

public class WBFilter implements Filter {

	public static boolean firstTime = false;
	private static Logger log = Red5LoggerFactory.getLogger(WBFilter.class, InnowhiteConstants.APP_NAME);
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		if (firstTime == false) {

			Constants.HOST_IP = req.getLocalAddr();
			firstTime = true;
			Constants.RTMP_BASE_URL = Constants.RTMP_URL + Constants.HOST_IP
					+ "/" + Constants.RTMP_APP + "/";
			// chain.doFilter(req, res);
			// return;
		}

		log.debug(" WBFilter bSessionFlag value Entry:   "
				+ Constants.bsessionFlag);
		boolean bValidRequest = true;
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(true);
		log.debug("session id: " + session.getId()
				+ " session created time: " + session.getCreationTime());
		String checkSum = request.getParameter(InnowhiteConstants.CHECKSUM);
		String orgName = "";
		String parentOrg = "";
		String queryStringWithoutcheckSum = "";
		String validServiceStatus = InnowhiteConstants.AUTH_FAILED;

		if (checkSum != null) {
			String queryString = request.getQueryString();
			log.debug("request query String " + queryString);
			String queryStringArray[] = queryString.split("&checksum=");
			if (queryStringArray.length > 1) {
				queryStringWithoutcheckSum = queryStringArray[0];
			}
			log.debug(queryStringArray[0]);

			orgName = request.getParameter(InnowhiteConstants.ORG_NAME);
			checkSum = request.getParameter(InnowhiteConstants.CHECKSUM);
			parentOrg = request.getParameter(InnowhiteConstants.PARENT_ORG);
			
			if(parentOrg == null)
				parentOrg = orgName;

			log.debug("oflaDemo orgName: " + orgName);

			String hostURL = null;
			if (orgName.indexOf(Constants.WEB_DELIMITER) > 0 && orgName.contains("INET") ) {
				StringTokenizer st = new StringTokenizer(orgName,
						Constants.WEB_DELIMITER);

				parentOrg = st.nextToken();
				orgName = hostURL = st.nextToken();
				
			}

			log.debug("oflaDemo orgName: " + orgName);
			log.debug("oflaDemo checkSum: " + checkSum);

			validServiceStatus = WhiteboardAuthenticatorService
					.validateRequest(queryStringWithoutcheckSum, parentOrg,
							checkSum);
			log.debug("validServiceStatus= " + validServiceStatus);
			if (validServiceStatus.equals(InnowhiteConstants.SUCCESS)) {
				bValidRequest = true;
			} else {
				bValidRequest = false;
				response.sendError(response.SC_UNAUTHORIZED, "NOT AUTHROIZED.");
			}
		}
		/*
		 * else { HttpSession session = request.getSession(false);
		 * if(session==null){ //Fail the request. unauthorized redirect code
		 * send the response code } else { bValidRequest = true; } }
		 */

		// request.getSess
		if (bValidRequest) {
			chain.doFilter(req, res);
			log.debug("bSessionFlag Exit value:   "
					+ Constants.bsessionFlag);
		}
	}

	public void init(FilterConfig config) throws ServletException {
		InnowhiteProperties.getPropertyVal("dummy");
 
		// log.debug("filter init::::##############");
		if (InnowhiteConstants.CONTEXT_PATH == null) {
			// Get the IP address of client machine.
			WhiteboardAuthenticationDAOImpl.main(null);
			String cont = config.getServletContext().getContextPath();

			
			
			
			log.debug("  cont ::::+++" + cont);
			InnowhiteConstants.CONTEXT_PATH = cont.substring(1);
			Constants.MAC_FOLDER_PATH = Constants.MAC_FOLDER_PATH.replaceAll(
					Constants.APP_NAME, InnowhiteConstants.CONTEXT_PATH);
			Constants.UBUNTU_FOLDER_PATH = Constants.UBUNTU_FOLDER_PATH
					.replaceAll(Constants.APP_NAME,
							InnowhiteConstants.CONTEXT_PATH);
			Constants.UBUNTU_FOLDER_PATH_COMMAND = Constants.UBUNTU_FOLDER_PATH_COMMAND
					.replaceAll(Constants.APP_NAME,
							InnowhiteConstants.CONTEXT_PATH);
			Constants.TEMP_LOCATION = Constants.TEMP_LOCATION.replaceAll(
					Constants.APP_NAME, InnowhiteConstants.CONTEXT_PATH);
			Constants.URL_MEDIA = Constants.URL_MEDIA.replaceAll(
					Constants.APP_NAME, InnowhiteConstants.CONTEXT_PATH);

			// Constants.CLIENT_MEDIA_CONTENT_PATH =
			// Constants.CLIENT_MEDIA_CONTENT_PATH.replaceAll(Constants.APP_NAME,
			// InnowhiteConstants.CONTEXT_PATH);

			Constants.APP_NAME = InnowhiteConstants.CONTEXT_PATH;
			InnowhiteConstants.FILE_SEPRATOR = System
					.getProperty("file.separator");
			
			
			BeanService bs = new BeanService();

		}
		// Get init parameter
		String testParam = config.getInitParameter("test-param");

		// Print the init parameter
		log.debug("Test Param: " + testParam);
	}

	public void destroy() {
		// add code to release any resource
	}
}