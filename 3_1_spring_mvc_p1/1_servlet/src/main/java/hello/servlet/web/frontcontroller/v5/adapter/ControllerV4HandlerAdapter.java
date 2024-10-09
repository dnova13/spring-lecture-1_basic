package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean support(Object handler) {
        return (handler instanceof ControllerV4); // V4가 넘어오면 true, 나머지는 false
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;// casting

        Map<String, String> paramMap = createParamMap(request);
        HashMap<String, Object> model = new HashMap<>();

        String viewName = controller.process(paramMap, model);

        // viewName을 return하면 에러발생
        // 110v를 220v로 바꿔주는 역할
        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

        return mv;
    }

    // V3 코드 그대로 사용
    private Map<String, String> createParamMap(HttpServletRequest request) {
        // 로그 출력 용
        Enumeration params = request.getParameterNames();
        while(params.hasMoreElements()) {
            String name = (String) params.nextElement();
            System.out.println(name + " : " + request.getParameter(name));
        }

        // request.getParameterNames()으로 모든 파라미터 이름 다 가져옴
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        System.out.println("createParamMap paramMap : " + paramMap);

        return paramMap;
    }
}