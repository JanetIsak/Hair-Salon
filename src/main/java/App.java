import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

   //  ProcessBuilder process = new ProcessBuilder();
   //  Integer port;
   //  if (process.environment().get("PORT") != null) {
   //      port = Integer.parseInt(process.environment().get("PORT"));
   //  } else {
   //      port = 4567;
   //  }
   //
   // setPort(port);

    get("/", (request, response) -> {
     Map<String, Object> model = new HashMap<String, Object>();
     model.put("stylists", Stylist.all());
     model.put("template", "templates/index.vtl");
     return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());

   // get("clients/new", (request, response) -> {
   //    Map<String, Object> model = new HashMap<String, Object>();
   //    model.put("template", "templates/client-form.vtl");
   //    return new ModelAndView(model, layout);
   //  }, new VelocityTemplateEngine());

    get("/clients", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("clients", Client.all());
      model.put("template", "templates/clients.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/clients/:id", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Client client = Client.find(Integer.parseInt(request.params(":id")));
    model.put("client", client);
    model.put("template", "templates/client.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

    get("/stylists/new", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/stylist-form.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

    get("/stylists", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("stylists", Stylist.all());
    model.put("template", "templates/stylists.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

    get("/stylists/:id", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
    model.put("stylist", stylist);
    model.put("template", "templates/stylist.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

    get("stylists/:id/clients/new", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
    model.put("stylist", stylist);
    model.put("template", "templates/stylist-clients-form.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

   post("/clients", (request, response) -> {
     Map<String, Object> model = new HashMap<String, Object>();

     ArrayList<Client> clients = request.session().attribute("clients");
     if (clients == null) {
        clients = new ArrayList<Client>();
        request.session().attribute("clients", clients);
     }

     String name = request.queryParams("name");
     String gender = request.queryParams("gender");
     int cellphone = Integer.parseInt(request.queryParams("cellphone"));
     int stylistId = Integer.parseInt(request.queryParams("stylistId"));

     Client newClient = new Client(name, gender, cellphone, stylistId);
     newClient.save();
     request.session().attribute("clients", clients);
     response.redirect("/clients");
     // model.put("template", "templates/clientList.vtl");
     return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());

     post("/stylists", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        String name = request.queryParams("name");
        Stylist newStylist = new Stylist(name);
        newStylist.save();
        model.put("template", "templates/stylist-List.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

     // post("/clients", (request, response) -> {
     //    Map<String, Object> model = new HashMap<String, Object>();
     //
     //    Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
     //
     //    String name = request.queryParams("name");
     //    String gender = request.queryParams("gender");
     //    int cellphone = Integer.parseInt(request.queryParams("cellphone"));
     //    Client newClient = new Client(name, gender, cellphone, stylist.getId());
     //
     //    newClient.save();
     //
     //    model.put("stylist", stylist);
     //    model.put("template", "templates/stylist-clients-List.vtl");
     //    return new ModelAndView(model, layout);
     //  }, new VelocityTemplateEngine());

  }
}
