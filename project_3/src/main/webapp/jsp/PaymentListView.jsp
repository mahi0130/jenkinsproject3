
<%@page import="in.co.rays.project_3.model.EmployeeModelInt"%>
<%@page import="in.co.rays.project_3.dto.EmployeeDTO"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.project_3.dto.PaymentDTO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_3.model.ModelFactory"%>
<%@page import="in.co.rays.project_3.model.RoleModelInt"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.controller.PaymentListCtl"%>
<%@page import="in.co.rays.project_3.util.HTMLUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bank List</title>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/CheckBox11.js"></script>
<style>
.hm {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/rain.jpg');
	background-size: cover;
	background-repeat: no-repeate;
	padding-top: 6%;
}

.p1 {
	padding: 4px;
	width: 200px;
	font-size: bold;
}

.text {
	text-align: center;
}
</style>
</head>

<body class="hm">
	<%@include file="Header.jsp"%>
	<%@include file="calendar.jsp"%>
	<div></div>
	<div>
		<form class="pb-5" action="<%=ORSView.PAYMENT_LIST_CTL%>"
			method="post">
			<jsp:useBean id="dto" class="in.co.rays.project_3.dto.PaymentDTO"
				scope="request"></jsp:useBean>
		<%-- 	<%
				List list1 = (List) request.getAttribute("PList");
			%> --%>


			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());
				EmployeeDTO dto1 = new EmployeeDTO();
				EmployeeModelInt model1 = ModelFactory.getInstance().getEmployeeModel();

				List list = ServletUtility.getList(request);

				Iterator<PaymentDTO> it = list.iterator();
				if (list.size() != 0) {
			%>
			<center>
				<h1 class="text-primary font-weight-bold pt-3">
					<u>Payment List</u>
				</h1>
			</center>
			<div class="row">
				<div class="col-md-4"></div>
				<%
					if (!ServletUtility.getSuccessMessage(request).equals("")) {
				%>

				<div class="col-md-4 alert alert-success alert-dismissible"
					style="background-color: #80ff80">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="#008000"><%=ServletUtility.getSuccessMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>
				<div class="col-md-4"></div>
			</div>
			<div class="row">
				<div class="col-md-4"></div>

				<%
					if (!ServletUtility.getErrorMessage(request).equals("")) {
				%>
				<div class=" col-md-4 alert alert-danger alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>
				<div class="col-md-4"></div>
			</div>

			<div class="row">


				
				<div class="col-sm-2">
					<input type="text" name="name" placeholder="Enter Name"
						class="form-control"
						value="<%=ServletUtility.getParameter("name", request)%>">
				</div>

				<div class="col-sm-2">
					<input type="text" name="accountNumber"
						placeholder="Enter AccountNumber" class="form-control"
						value="<%=ServletUtility.getParameter("accountNumber", request)%>">
				</div>
				<div class="col-sm-2">
					<input type="text" name="amount" placeholder="Enter Address"
						class="form-control"
						value="<%=ServletUtility.getParameter("amount", request)%>">
				</div>
				<div class="col-sm-2">
					<%
						HashMap map = new HashMap();
							map.put("Bank of Baroda", "Bank of Baroda");
							map.put("SBI", "SBI");
							map.put("kenera", "kenera");
							map.put("BIO", "BIO");
							map.put("pubjab national", "punjab national");
							map.put("kotak", "kotak");
							map.put("union", "union");
					%>

					<%=HTMLUtility.getList("bankName", dto.getBankName(), map)%>


				</div>




				<div class="col-sm-2">
					<input type="submit" class="btn btn-primary btn-md"
						style="font-size: 15px" name="operation"
						value="<%=PaymentListCtl.OP_SEARCH%>"> &emsp; <input
						type="submit" class="btn btn-dark btn-md" style="font-size: 15px"
						name="operation" value="<%=PaymentListCtl.OP_RESET%>">
				</div>


				
			</div>


			<div style="margin-bottom: 20px;" class="table-responsive">
				<table class="table table-bordered table-dark table-hover">
					<thead>
						<tr style="background-color: blue;">

							<th width="10%"><input type="checkbox" id="select_all"
								name="Select" class="text"> Select All</th>
							<th width="5%" class="text">S.NO</th>
							<th width="15%" class="text">Name</th>
							<th width="15%" class="text">AccountNumber</th>
							<th width="20%" class="text">Amount</th>
							<th width="15%" class="text">BankName</th>
							<th width="15%" class="text">EmployeeStatus</th>

							<th width="5%" class="text">Edit</th>
						</tr>
					</thead>
					<%
						while (it.hasNext()) {
								dto = it.next();
					
					%>
					<tbody>
						<tr>
							<td align="center"><input type="checkbox" class="checkbox"
								name="ids" value="<%=dto.getId()%>"></td>
							<td class="text"><%=index++%></td>
							<td class="text"><%=dto.getName()%></td>
							<td class="text"><%=dto.getAccountNumber()%></td>
							<td class="text"><%=dto.getAmount()%></td>
							<td class="text"><%=dto.getBankName()%></td>
							<td class="text"><%=dto.getStatus()%></td>

							<td class="text"><a href="PaymentCtl?id=<%=dto.getId()%>">Edit</a></td>
						</tr>
					</tbody>
					<%
						}
					%>
				</table>
			</div>
			<table width="100%">
				<tr>
					<td><input type="submit" name="operation"
						class="btn btn-warning btn-md" style="font-size: 17px"
						value="<%=PaymentListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>

					<td><input type="submit" name="operation"
						class="btn btn-primary btn-md" style="font-size: 17px"
						value="<%=PaymentListCtl.OP_NEW%>"></td>

					<td><input type="submit" name="operation"
						class="btn btn-danger btn-md" style="font-size: 17px"
						value="<%=PaymentListCtl.OP_DELETE%>"></td>

					<td align="right"><input type="submit" name="operation"
						class="btn btn-warning btn-md" style="font-size: 17px"
						style="padding: 5px;" value="<%=PaymentListCtl.OP_NEXT%>"
						<%=(nextPageSize != 0) ? "" : "disabled"%>></td>
				</tr>
				<tr></tr>
			</table>

			<%
				}
				if (list.size() == 0) {
			%>
			</br>
			<div class="row">
				<div class="col-md-4"></div>

				<%
					if (!ServletUtility.getErrorMessage(request).equals("")) {
				%>
				<div class=" col-md-4 alert alert-danger alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>




				<%
					if (!ServletUtility.getSuccessMessage(request).equals("")) {
				%>

				<div class="col-md-4 alert alert-success alert-dismissible"
					style="background-color: #80ff80">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="#008000"><%=ServletUtility.getSuccessMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>
				<div style="padding-left: 48%;">
					<input type="submit" name="operation"
						class="btn btn-primary btn-md" style="font-size: 17px"
						value="<%=PaymentListCtl.OP_BACK%>">
				</div>

				<div class="col-md-4"></div>
			</div>

			<%
				}
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>


	</div>


</body>
<%@include file="FooterView.jsp"%>
</html>