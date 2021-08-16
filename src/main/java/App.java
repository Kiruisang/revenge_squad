import models.Hero;
import models.Squad;
import spark.ModelAndView;
import java.util.ArrayList;
import spark.QueryParamsMap;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;



public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        post("/heroes/new",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            int age = Integer.parseInt(request.queryParams("age"));

            String[] specialPowerAdd = request.queryMap().toMap().get("power");
            ArrayList<String> specialPower = new ArrayList<String>();
            for (int i=0;i<specialPowerAdd.length;i++){
                specialPower.add(specialPowerAdd[i]);
            }
            model.put("specialPower",specialPower);

            String[] weaknessAdd =  request.queryMap().toMap().get("weakness");
            ArrayList<String> weakness = new ArrayList<String>();
            for (String s:weaknessAdd){
                weakness.add(s);
            }
            model.put("weakness",weakness);

            ArrayList<Squad> squads = Squad.getAll();
            model.put("squads",squads);
            int squadId = Integer.parseInt(request.queryParams("squadId"));

            Hero hero = new Hero(name,age,specialPower,weakness,squadId);

            model.put("hero",hero);

            return new ModelAndView(model,"success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Hero> heroes = Hero.getAll();
            model.put("heroes",heroes);
            ArrayList<Squad> squads = Squad.getAll();
            model.put("squads",squads);
            return new ModelAndView(model, "heros.hbs");
        },new HandlebarsTemplateEngine());

        get("/heroes/list",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            ArrayList<Hero> heroes = Hero.getAll();
            model.put("heroes",heroes);
            ArrayList<Squad> squads = Squad.getAll();
            model.put("squads",squads);
            for (Hero hero: heroes) {
                int idOfHeroToFind = hero.getSquadId();
                Squad squad = Squad.findById(idOfHeroToFind);
                model.put("squad",squad);
            }

            return new ModelAndView(model,"herosList.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heroes/delete", (request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            Hero.clearAllHeros();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heroes/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToFind = Integer.parseInt(request.params("id"));
            Hero foundHero = Hero.findById(idOfHeroToFind);
            int squadId = foundHero.getSquadId();
            Squad squad = Squad.findById(squadId);
            ArrayList<Hero> heroes = Hero.getAll();
            model.put("squad",squad);
            model.put("hero",foundHero);
            model.put("heroes",heroes);
            return new ModelAndView(model,"herosList.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heroes/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Squad> squads = Squad.getAll();
            int idOfHeroToEdit = Integer.parseInt(request.params("id"));

            Hero editHero = Hero.findById(idOfHeroToEdit);

            model.put("editHero", editHero);
            model.put("squads",squads);

            return  new ModelAndView(model, "heros.hbs");

        }, new HandlebarsTemplateEngine());

        post("/heroes/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = request.queryParams("name");
            int newAge = Integer.parseInt(request.queryParams("age"));
            int idOfHeroToEdit = Integer.parseInt(request.params("id"));
            String[] specialPowerAdd = request.queryMap().toMap().get("power");
            ArrayList<String> specialPower = new ArrayList<String>();
            for (int i=0;i<specialPowerAdd.length;i++){
                specialPower.add(specialPowerAdd[i]);
            }
            model.put("specialPower",specialPower);

            String[] weaknessAdd =  request.queryMap().toMap().get("weakness");
            ArrayList<String> weakness = new ArrayList<String>();
            for (String s:weaknessAdd){
                weakness.add(s);
            }
            Hero editHero = Hero.findById(idOfHeroToEdit);
            editHero.update(newName, newAge, specialPower, weakness);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heroes/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToDelete = Integer.parseInt(request.params("id"));

            Hero deleteHero = Hero.findById(idOfHeroToDelete);
            deleteHero.deleteHero();

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //squad
        get("/squads", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "squad.hbs");
        },new HandlebarsTemplateEngine());

        post("/squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            int maxSize = Integer.parseInt(request.queryParams("maxSize"));
            String cause = request.queryParams("cause");
            Squad squad = new Squad(maxSize,name,cause);
            return new ModelAndView(model, "successSquad.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squads/list", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Squad> squads = Squad.getAll();
            model.put("squads", squads);

            return new ModelAndView(model, "squadList.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squad/:id",(request, response) ->{
            Map<String, Object> model = new HashMap<>();
            int squadId =Integer.parseInt(request.params("squadId"));
            Hero heroes = Hero.findById(squadId);
            Squad squads = Squad.findById(squadId);
            model.put("squad",squads);
            model.put("heroes", heroes);
            return new ModelAndView(model,"squadList.hbs");
        },new HandlebarsTemplateEngine());

    }
}
