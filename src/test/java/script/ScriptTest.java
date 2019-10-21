package script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.List;

/**
 * @author: GuanBin
 * @date: Created in 下午6:19 2019/9/30
 */
public class ScriptTest {

    public static void main(String[] args) {
        //Get the script engine manager
        ScriptEngineManager manager = new ScriptEngineManager();
        List<ScriptEngineFactory> engineFactories = manager.getEngineFactories();
        engineFactories.forEach(e -> {
            System.out.println(e.getLanguageName());
            System.out.println(e.getEngineName());
            System.out.println(e.getEngineVersion());
            System.out.println(e.getExtensions());
            System.out.println(e.getNames());
            System.out.println(e.getScriptEngine());
        });

        // Try executing scripts in Nashorn, Groovy, Jython, and JRuby
        execute(manager, "JavaScript", "print('Hello JavaScript')");
        execute(manager, "Groovy", "println('Hello Groovy')");
        execute(manager, "jython", "print('Hello Jython)");
        execute(manager, "jruby", "puts('Hello JRuby')");
    }

    public static void execute(ScriptEngineManager manager, String engineName,
                               String script) {

        ScriptEngine engine = manager.getEngineByName(engineName);
        if (engine == null) {
            System.out.println(engineName + " is not available.");
            return;
        }

        try {
            engine.eval(script);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

}
