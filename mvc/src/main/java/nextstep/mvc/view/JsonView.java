package nextstep.mvc.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nextstep.mvc.exception.NotExistException;
import nextstep.web.support.MediaType;

import java.io.PrintWriter;
import java.util.Map;

public class JsonView implements View {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object value = getModelValue(model);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        final PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value));
        writer.flush();
    }

    private Object getModelValue(Map<String, ?> model) {
        if (model.size() == 1) {
            return model.values().toArray()[0];
        }
        return model;
    }

}
