package se.lexicon.spring_bootrestful_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    //http://localhost:8080/index
    //http://localhost:8080/helloworld
    //    @RequestMapping(method = RequestMethod.GET, path = {"/", "/index", "/helloWorld"})
    @GetMapping(path = { "/index", "/helloworld"})
    public String helloWorld() {
        return "<h1> Hello World - Message form home Controller! </h1>";
    }


    //http://localhost:8080/message?message=hello%20group%2043
    @GetMapping("/message")
    public ResponseEntity<String> responseString(@RequestParam(value = "message", defaultValue = "Hello this is another message") String message){
        return ResponseEntity.status(200).body(message);
    }


    //http://localhost:8080/
    @GetMapping
    public ResponseEntity<Void> foo(){
        return ResponseEntity.notFound().build();
    }


}
