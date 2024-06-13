package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.PaymentDTO;
import in.co.rays.project_3.dto.PaymentDTO;
import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.PaymentDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.EmployeeModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.PaymentModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;
import jdk.nashorn.internal.ir.RuntimeNode.Request;
@WebServlet(name = "PaymentCtl", urlPatterns = { "/ctl/PaymentCtl" })
public class PaymentCtl extends BaseCtl {

	@Override
	protected void preload(HttpServletRequest request) {
		PaymentModelInt model = ModelFactory.getInstance().getPaymentModel();
		EmployeeModelInt emodel =  ModelFactory.getInstance().getEmployeeModel();
		try {
			
			List list = model.list();
			List elist =  emodel.list();
			request.setAttribute("elist", elist);
			request.setAttribute("Plist", list);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", "name must contain alphabets only");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("accountNumber"))) {
			request.setAttribute("accountNumber", PropertyReader.getValue("error.require", "accountNumber"));
			pass = false;
		} else if (!DataValidator.isAccountNo(request.getParameter("accountNumber"))) {
			request.setAttribute("accountNumber", "Must contain digit Only And Length 6 to 12 ");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("amount"))) {
			request.setAttribute("amount", PropertyReader.getValue("error.require", "amount"));
			pass = false;
		} else if (!DataValidator.isInteger(request.getParameter("amount"))) {
			request.setAttribute("amount", "Must contain integer only");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("bankName"))) {
			request.setAttribute("bankName", PropertyReader.getValue("error.require", "bankName"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("bankName"))) {
			request.setAttribute("bankName", "Must contain alphabets only");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("bankName"))) {
			request.setAttribute("status", PropertyReader.getValue("error.require", "status"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("status"))) {
			request.setAttribute("status", "Must contain alphabets only");
			pass = false;
		}

		return pass;

	}

	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		PaymentDTO dto = new PaymentDTO();
		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setName(DataUtility.getString(request.getParameter("name")));
		dto.setAccountNumber(DataUtility.getString(request.getParameter("accountNumber")));
		dto.setAmount(DataUtility.getInt(request.getParameter("amount")));
		dto.setBankName(DataUtility.getString(request.getParameter("bankName")));
		dto.setStatus(DataUtility.getString(request.getParameter("status")));
		populateBean(dto, request);

		return dto;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));
		PaymentModelInt model = ModelFactory.getInstance().getPaymentModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			PaymentDTO dto = new PaymentDTO();
			try {
				dto = model.findByPK(id);
				ServletUtility.setDto(dto, request);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		PaymentModelInt model = ModelFactory.getInstance().getPaymentModel();
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			PaymentDTO dto = (PaymentDTO) populateDTO(request);
			try {
				if (id > 0) {
					model.update(dto);

					ServletUtility.setSuccessMessage("Data is successfully Update", request);
				} else {

					try {
						model.add(dto);
						ServletUtility.setDto(dto, request);
						ServletUtility.setSuccessMessage("Data is successfully saved", request);
					} catch (ApplicationException e) {
						ServletUtility.handleException(e, request, response);
						return;
					} catch (DuplicateRecordException e) {
						ServletUtility.setDto(dto, request);
						ServletUtility.setErrorMessage("Login id already exists", request);
					}

				}
				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			}
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			PaymentDTO dto = (PaymentDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.PAYMENT_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.PAYMENT_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.PAYMENT_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.PAYMENT_VIEW;
	}

}
