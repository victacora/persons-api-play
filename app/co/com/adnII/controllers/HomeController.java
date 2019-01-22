package co.com.adnII.controllers;

import play.mvc.*;

public class HomeController extends Controller {


    public Result index() {
        return ok("Welcome");
    }

}
