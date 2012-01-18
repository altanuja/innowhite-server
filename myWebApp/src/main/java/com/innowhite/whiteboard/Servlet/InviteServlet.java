package com.innowhite.whiteboard.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.innowhite.whiteboard.service.SendInviteService;

/**
 * Servlet implementation class InviteServlet
 */
public class InviteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InviteServlet() {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String xml = request.getParameter("xml");
	//String roomId = request.getParameter("roomId");
	log.debug(" The xml from flex client is :: " + xml);
	if(xml != null){
	    
	    SendInviteService.sendInviteService(xml);
	}

    }

}