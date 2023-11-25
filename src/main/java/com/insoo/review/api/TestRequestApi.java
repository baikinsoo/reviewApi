package com.insoo.review.api;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestRequestApi {

    //Request Parameter 방식
    @GetMapping("/test/param")
    public String requestParam(
            @RequestParam("name") String name,
            @RequestParam("age") Integer age
    ) {
        return "Hello, Request Param, I am " + name + ", " + age;
    }

    //Path Variable 방식
    @GetMapping("/test/path/{name}/{age}")
    public String requestPathVariable(
            @PathVariable("name") String name,
            @PathVariable("age") Integer age
    ) {
        return "Hello, Path Variable, I am " + name + ", " + age;
    }

    //Request Body 방식
    @PostMapping("/test/body")
    public String requestBody(
            @RequestBody TestRequestBody requestBody
    ) {
        return "Hello, Request Body, I am " + requestBody.name + ", "  + requestBody.age;
    }

    public static class TestRequestBody {

        String name;

        Integer age;

        public TestRequestBody(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }

}
