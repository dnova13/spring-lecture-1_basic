package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    //    private Map<String, ControllerV3> controllerMap = new HashMap<>(); // 기존 코드
    private final Map<String, Object> handlerMappingMap = new HashMap<>(); // V2, V3 등등 상관없이 다 들어가되기 때문에 Object
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>(); // 여러개 중 하나를 꺼내서 써야 함

    public FrontControllerServletV5() {
        initHandlerMappingMap();

        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 기존 코드
//        String requestURI = request.getRequestURI();
//
//        ControllerV3 controller = controllerMap.get(requestURI);
//
//        if (controller == null) {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
//
//        Map<String, String> paramMap = createParamMap(request);
//
//        System.out.println("service paramMap : " + paramMap);
//
//        ModelView mv = controller.process(paramMap);
//
//        String viewName = mv.getViewName(); // 논리이름 ex) new-form
//
//        System.out.println("service viewName : " + viewName);
//
//        // 논리 이름을 이용해서 물리 이름으로 변환
//        MyView view = viewResolver(viewName);
//
//        view.render(mv.getModel(), request, response);

        // MemberFormControllerV3() 반환됨
        Object handler = getHandler(request);

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // ControllerV3HandlerAdapter() 반환됨
        // 핸들러 어댑터 목록을 뒤져서 알맞은 핸들러 어댑터를 찾아와야함(그림에서 2번)
        MyHandlerAdapter adapter = gethandlerAdapter(handler);

        // 핸들러 호출(그림에서 3번, 4번)
        ModelView mv = adapter.handle(request, response, handler);

        String viewName = mv.getViewName(); // 논리이름 ex) new-form

        System.out.println("service viewName : " + viewName);

        // 논리 이름을 이용해서 물리 이름으로 변환
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);
    }

    private MyHandlerAdapter gethandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.support(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler = " + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}