package com.tpc.groot.course.controller;

import org.python.core.PyFloat;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.python.util.PythonInterpreter;

@RequestMapping("/api/map")
@RestController
public class CoursePythonController {

    private static PythonInterpreter interpreter;

    @GetMapping("/recommend")
    public String recommend(@RequestParam float lat, @RequestParam float lng) {
        interpreter = new PythonInterpreter();
        interpreter.execfile("src/main/python/recommend.py");

        PyFunction pyFunction = interpreter.get("recommend_route", PyFunction.class);

        PyObject pyobj = pyFunction.__call__(new PyFloat(lat), new PyFloat(lng));
        return pyobj.toString();
    }
}
