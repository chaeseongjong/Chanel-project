package com.seongjong.chae;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
        // MIME Type 을 application/octet-stream 타입으로 변경
        // 무조건 팝업(다운로드창)이 뜨게 된다.
        response.setContentType("application/octet-stream");
        // 브라우저는 ISO-8859-1을 인식하기 때문에
        // UTF-8 -> ISO-8859-1로 디코딩, 인코딩 한다.
        String orgname = "Portfolio.pdf";
        orgname = new String(orgname.getBytes("UTF-8"), "iso-8859-1");
        // 파일명 지정
        response.setHeader("Content-Disposition", "attachment; filename=\""+orgname+"\"");
        OutputStream os = response.getOutputStream();
        // String path = servletContext.getRealPath("/resources");
        // d:/upload 폴더를 생성한다.
        // server에 clean을 하면 resources 경로의 것이 다 지워지기 때문에
        // 다른 경로로 잡는다(실제 서버에서는 위의 방식으로)
        String path = "D:";
        FileInputStream fis = new FileInputStream(path + File.separator + orgname);
        int n = 0;
        byte[] b = new byte[512];
        while((n = fis.read(b)) != -1 ) {
            os.write(b, 0, n);

        }
        fis.close();
        os.close();

    }
	
}
