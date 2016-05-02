package squidteam.ihm;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

public class Serveur {


  public Serveur() {
    // TODO Auto-generated constructor stub

  }


  public static void lancerServeur() throws Exception {
    Server server = new Server(8080);
    WebAppContext context = new WebAppContext();
    context.setContextPath("/");
    context.addServlet(new ServletHolder(new MainServlet()), "/mobilitas/*");
    context.addServlet(new ServletHolder(new DefaultServlet()), "/");
    context.setResourceBase("web");
    context.setWelcomeFiles(new String[] {"web/index.html"});
    context.setInitParameter("cacheControl", "no-store,no-cache,must-revalidate");
    server.setHandler(context);
    server.start();
  }
}

