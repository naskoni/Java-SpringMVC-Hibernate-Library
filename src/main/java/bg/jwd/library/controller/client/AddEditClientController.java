package bg.jwd.library.controller.client;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bg.jwd.library.constant.ClientConstants;
import bg.jwd.library.constant.CommonConstants;
import bg.jwd.library.entity.client.Client;
import bg.jwd.library.service.client.ClientService;
import bg.jwd.library.util.ErrorUtils;
import bg.jwd.library.util.UserUtils;

@Controller
public final class AddEditClientController {

	private static final Logger logger = LoggerFactory.getLogger(AddEditClientController.class);

	@Autowired
	private ClientService clientService;

	@RequestMapping(value = ClientConstants.URL_ADD_CLIENT, method = RequestMethod.GET)
	public String addClient(Model model) {
		UserUtils.addUserToModel(model);
		model.addAttribute(CommonConstants.URL_REGISTER, ClientConstants.URL_CLIENT_REGISTER);
		model.addAttribute(CommonConstants.URL_POST, ClientConstants.URL_ADD_CLIENT_POST);

		return "addEditClient";
	}

	@RequestMapping(value = ClientConstants.URL_ADD_CLIENT_POST, method = RequestMethod.POST)
	public String addClientPost(@ModelAttribute("client") Client client) {
		clientService.addClient(client);

		return CommonConstants.REDIRECT + ClientConstants.CLIENT_REGISTER;
	}

	@RequestMapping(value = ClientConstants.URL_EDIT_CLIENT, method = RequestMethod.GET)
	public String editClient(Model model, HttpServletRequest request) {

		Long id = Long.parseLong(request.getParameter(CommonConstants.ID));

		UserUtils.addUserToModel(model);
		model.addAttribute("client", clientService.findClient(id));
		model.addAttribute(CommonConstants.URL_REGISTER, ClientConstants.URL_CLIENT_REGISTER);
		model.addAttribute(CommonConstants.URL_POST, ClientConstants.URL_EDIT_CLIENT_POST);
		model.addAttribute(CommonConstants.ID, id);

		return "addEditClient";
	}

	@RequestMapping(value = ClientConstants.URL_EDIT_CLIENT_POST, method = RequestMethod.POST)
	public String editClientPost(@ModelAttribute("client") Client client) {
		clientService.update(client);

		return CommonConstants.REDIRECT + ClientConstants.CLIENT_REGISTER;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest request, Exception exception) {
		logger.error("Request: " + request.getRequestURL() + " raised " + exception);

		return ErrorUtils.prepareErrorModelAndView();
	}
}
