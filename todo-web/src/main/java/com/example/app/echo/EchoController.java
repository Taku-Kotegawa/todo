package com.example.app.echo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * EchoController.
 */
@Controller
@RequestMapping("echo")
public class EchoController {

    /**
     * フォームビーンの生成.
     *
     * @return フォームビーン
     */
    @ModelAttribute
    public EchoForm setUpEchoForm() {

        EchoForm form = new EchoForm();
        form.setName("example");
        return form;
    }

    /**
     * 初期表示.
     *
     * @return JSPのパス
     */
    @SuppressWarnings("SameReturnValue")
    @RequestMapping
    public String index() {
        return "echo/index";
    }

    /**
     * エコー画面.
     *
     * @param form フォーム
     * @param result バリデーションの結果
     * @param model モデル
     * @return JSPのパス
     */
    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "hello", method = RequestMethod.POST)
    public String hello(
        @Validated EchoForm form, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "echo/index";
        }

        model.addAttribute("name", form.getName());
        return "echo/hello";
    }

}
