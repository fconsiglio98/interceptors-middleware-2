package co.develhope.interceptorsmiddleware2.controllers;

import co.develhope.interceptorsmiddleware2.entities.Month;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/months")
public class MonthController {


    @GetMapping
    public Month getMonth(HttpServletRequest request){
        return (Month) request.getAttribute("month");
    }
}