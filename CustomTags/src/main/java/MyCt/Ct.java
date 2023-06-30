package MyCt;
import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.*;
public class Ct extends TagSupport {

	@Override
	public int doStartTag() throws JspException {
		JspWriter o=pageContext.getOut();
		try {
			o.print("hi");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.doStartTag();
	}
}
