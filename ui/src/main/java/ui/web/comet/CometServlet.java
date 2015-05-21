package ui.web.comet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.comet.CometEvent;
import org.apache.catalina.comet.CometProcessor;

import ui.common.comet.MessageSender;
import ui.common.json.reader.JsonReader;

public class CometServlet extends HttpServlet implements CometProcessor {
	private static final long serialVersionUID = 1L;
	private MessageSender messageSender = null;
	private static final Integer TIMEOUT = 60 * 1000;

	@Override
	public void destroy() {
		messageSender.stop();
		messageSender = null;

	}

	@Override
	public void init() throws ServletException {
		messageSender = new MessageSender();
		Thread messageSenderThread = new Thread(messageSender, "MessageSender[" + getServletContext().getContextPath() + "]");
		messageSenderThread.setDaemon(true);
		messageSenderThread.start();

	}

	@Override
	public void event(CometEvent event) throws IOException, ServletException {
		HttpServletRequest request = event.getHttpServletRequest();
		HttpServletResponse response = event.getHttpServletResponse();
		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-type","application/json; charset=utf-8");
		if (event.getEventType() == CometEvent.EventType.BEGIN) {
			request.setAttribute("org.apache.tomcat.comet.timeout", TIMEOUT);
			log("Begin for session: " + request.getSession(true).getId());
			messageSender.setConnection(response);
			JsonReader jsonReader = new JsonReader(messageSender);
			jsonReader.start();
		} else if (event.getEventType() == CometEvent.EventType.ERROR) {
			log("Error for session: " + request.getSession(true).getId());
			event.close();
		} else if (event.getEventType() == CometEvent.EventType.END) {
			log("End for session: " + request.getSession(true).getId());
			event.close();
		} else if (event.getEventType() == CometEvent.EventType.READ) {
			throw new UnsupportedOperationException("This servlet does not accept data");
		}

	}

	
}
