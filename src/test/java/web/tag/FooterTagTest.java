package web.tag;

import org.junit.Test;

import javax.servlet.jsp.JspException;

public class FooterTagTest {
    private FooterTag tag = new FooterTag();

    @Test(expected = JspException.class)
    public void testDoStart() throws JspException {
        tag.doStartTag();
    }
    @Test
    public void testDoEnd() throws JspException {
        tag.doEndTag();
    }
}
