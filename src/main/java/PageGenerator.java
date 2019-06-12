
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class PageGenerator {
    private static final String HTML_DIR = "src/templates";
    private static PageGenerator pageGenerator;
    private final Configuration cnfg;

    private PageGenerator() {
        cnfg = new Configuration();
    }

    public static PageGenerator getInstance() {
        if (pageGenerator == null) {
            pageGenerator = new PageGenerator();
        }
        return pageGenerator;
    }

    public String getPage(String fileName, Map<String, Object> date) throws Exception {
        Writer stream = new StringWriter();
        try {
            Template template = cnfg.getTemplate(HTML_DIR + File.separator + fileName);
            template.process(date, stream);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stream.toString();
    }
}
