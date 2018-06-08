package controllers;

/**
 * Created by sunny.singh on 5/8/2017.
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import play.Configuration;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicReference;

public class Login extends Controller {

  @Inject
  WSClient ws;

  String HostName = ConfigFactory.load().getString("hostname");

  public Result logout() {
    session().clear();
    flash("success", "You've been logged out");
    return redirect(
      routes.Application.login()
    );
  }

  public Result authenticate() {
    ObjectNode resultfinal = Json.newObject();
    AtomicReference<String> usersession = new AtomicReference();
    JsonNode json = request().body().asJson();
    String username1 = json.findPath("username").textValue().toString();
    String password = json.findPath("password").textValue().toString();
    //String str ;
    /*if(username1.equals("demo@bbh") && password.equals("test")){
      str = "success";
    }else{
      str = "failure";
    }*/

    JsonNode jsonobj = Json.newObject()
      .put("user_name", username1)
      .put("password", password);
    String res123 = "testing testing";
    final Http.Session session = session();
    final CompletionStage<WSResponse> result = ws.url(HostName + "/api/userAuthentication").post(jsonobj).thenApply(res -> res);
    final CompletionStage<Result> resultCompletionStage = result.thenApplyAsync(token -> {
      Gson gson = new Gson();
      User user = gson.fromJson(token.getBody().toString(), User.class);

      System.out.println("*******" + user.getUserName());
      String username = null;
      username = user.getUserName();

      if (username1.equals(user.getUserName())) {
        session.put("uname", username);
        return ok("");
      } else {
        return ok("");
      }
    }).toCompletableFuture();

    try {
      Thread.sleep(2000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
    String uname = session("uname");
    String str = "";
    if (username1.equals(uname)) {
      //return ok("success");
      str = "success";
    } else {
      //return ok("fail");
      str = "failure";
    }

    resultfinal.put("result", str);

    return ok(resultfinal);

    // return ok(resultCompletionStage.toCompletableFuture()+uname);
  }
}
