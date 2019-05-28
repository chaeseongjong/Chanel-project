package com.seongjong.chae;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		logger.info("메인 페이지 접속.");
		return "home";
	}
	
	@RequestMapping(value="/download",method=RequestMethod.GET)
    public void down(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        String orgname = "Portfolio.pdf";
        orgname = new String(orgname.getBytes("UTF-8"), "iso-8859-1");
        response.setHeader("Content-Disposition", "attachment; filename=\""+orgname+"\"");
        OutputStream os = response.getOutputStream();
        String path = "D:";
        FileInputStream fis = new FileInputStream(path + File.separator + orgname);
        int n = 0;
        byte[] b = new byte[1024];
        
        while((n = fis.read(b)) != -1 ) 
        	os.write(b, 0, n); 
        
        fis.close();
        os.close();
        
        

    }
	
}
