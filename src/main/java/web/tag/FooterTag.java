package web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class FooterTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        String style = " style=\"" +
                "    position: fixed;\n" +
                "    left: 0; bottom: 0;\n" +
//                "    padding: -2px;\n" +
                "    background: #39b54a;\n" +
                "    color: #fff;\n" +
                "    width: 100%;\" ";
        String footer = "<footer" + style + ">\n" +
                "  <p>Posted by: Ivan Biruchov.\n" +
                "  Contact information: <a href=\"mailto:igb9925@gmail.com\">\n" +
                "  igb9925@gmail.com</a>.</p>\n" +
                "</footer>";
        try {
            JspWriter out = pageContext.getOut();
            out.write(footer);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
