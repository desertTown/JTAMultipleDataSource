package com.evan.jta.controller;


import com.evan.jta.service.JtaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author EvanHuang
 * @date 1/10/2019 6:34 PM
 * @since
 */
@RestController
public class JtaController {
    @Autowired
    private JtaService jtaService;

    @PostMapping("/testJTA")
    public void testJta() {
        jtaService.jtaTesting();
    }

}
