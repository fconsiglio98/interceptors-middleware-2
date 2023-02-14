package co.develhope.interceptorsmiddleware2.interceptors;

import co.develhope.interceptorsmiddleware2.entities.Month;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class MonthInterceptor implements HandlerInterceptor {

    public static List<Month> monthsList = new ArrayList<>(Arrays.asList(
            new Month(1, "January", "Gennaio", "Januar"),
            new Month(2, "February", "Febbraio", "Februar"),
            new Month(3, "March", "Marzo", "Marz"),
            new Month(4, "April", "Aprile", "April"),
            new Month(5, "May", "Maggio", "Mai"),
            new Month(6, "June", "Giugno", "Juni")
    ));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String monthNumberString = request.getHeader("monthNumber");
        if (monthNumberString == null || monthNumberString.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return true;
        } else {
            long monthNumber = Long.parseLong(monthNumberString);
            Optional<Month> month = monthsList.stream().filter(filteredMonth ->
                    filteredMonth.getMonthNumber() == (monthNumber)).findFirst();

            if (month.isPresent()) {
                request.setAttribute("month", month.get());
            } else {
                request.setAttribute("month", new Month(0, "nope", "nope", "nope"));
            }
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
    }
}