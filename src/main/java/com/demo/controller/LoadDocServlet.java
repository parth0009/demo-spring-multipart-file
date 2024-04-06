package com.demo.controller;

import com.demo.model.ListItem;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class will be used to populate Document Types dropdown on HR/CO and Service Requestor pages
 * Mainly for the multiple upload functionality. Since we're using HTML tags for dropdown
 * 
 * @web.servlet name="LoadDocServlet"
 * 
 * @web.servlet-mapping url-pattern="/controller/LoadDoc"
 * 
 * @author Parth Parikh
 * 
 */
@SuppressWarnings("unchecked")
public class LoadDocServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	List<ListItem> docTypeList = new ArrayList<ListItem>();
    	ListItem item1 = new ListItem();
    	item1.setLabel("OF-306");
    	item1.setValue("OF-306");
    	
    	ListItem item2 = new ListItem();
    	item2.setLabel("OF-8");
    	item2.setValue("OF-8");
    	
    	ListItem item3 = new ListItem();
    	item3.setLabel("Resume");
    	item3.setValue("Resume");
    	
    	ListItem item4 = new ListItem();
    	item4.setLabel("Position Designation Tool");
    	item4.setValue("PDT");
    	
    	ListItem item5 = new ListItem();
    	item5.setLabel("CV");
    	item5.setValue("CV");
    	
    	docTypeList.add(item1);
    	docTypeList.add(item2);
    	docTypeList.add(item3);
    	docTypeList.add(item4);
    	docTypeList.add(item5);
    	
        // SCMS-777 Getting the below parameters to get the document types required on Service Requestor page.
        // Sharing this servlet between two calls. One gets all the Document Type options and the other gets the predefined required doc types in properties.
        String callForRequiredDocTypes = request.getParameter("callForRequiredDocTypes");
        boolean callForRequiredDocTypesBool = Boolean.parseBoolean(callForRequiredDocTypes);    
        
        String requiredDocumentTypes = "OF-306,OF-8";
        
                
        Map<String, Object> data = new HashMap<String, Object>();
        
        if (callForRequiredDocTypesBool) {
        	data.put("requiredDocTypes", requiredDocumentTypes);
        } else {
        	data.put("docTypes", docTypeList);
        }

        Gson gson = new Gson();        
        String json = gson.toJson(data);


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // Make sure no caching happens
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(req, resp);
    }

}
