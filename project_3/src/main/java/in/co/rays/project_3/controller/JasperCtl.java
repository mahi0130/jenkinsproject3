// /
// /**
//  * Jasper functionality Controller. Performs operation for Print pdf of
//  * MarksheetMeriteList
//  * @author Mahi Singh
//  *
//  */
//@WebServlet(name = "JasperCtl", urlPatterns = { "/ctl/JasperCtl" })
//public class JasperCtl extends BaseCtl {

	
//	private static final long serialVersionUID = 1L;
//
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try {
//			
//			ResourceBundle rb= ResourceBundle.getBundle("in.co.rays.project_3.bundle.system");
//			/* Compilation of jrxml file */
//			JasperReport jasperReport = JasperCompileManager
//					.compileReport(rb.getString("jasper"));
//
//			HttpSession session = request.getSession(true);
//			UserDTO dto = (UserDTO) session.getAttribute("user");
//			dto.getFirstName();
//			dto.getLastName();
//
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("ID", 1l);
//			Connection conn = null;
//
//			
//
//
//			String Database = rb.getString("DATABASE");
//			
//			if ("Hibernate".equalsIgnoreCase(Database)) {
//				conn = ((SessionImpl) HibDataSource.getSession()).connection();
//			}
//
//			if ("JDBC".equalsIgnoreCase(Database)) {
//				conn = JDBCDataSource.getConnection();
//			}
//
//			/* Filling data into the report */
//			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn);
//
//			/* Export Jasper report */
//			byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
//
//			response.setContentType("application/pdf");
//			response.getOutputStream().write(pdf);
//			response.getOutputStream().flush();
//		} catch (Exception e) {
//
//		}
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//	}
//

