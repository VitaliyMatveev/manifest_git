package ru.komiparma.manifest.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.komiparma.manifest.dao.IRoutingSheetDAO;
import ru.komiparma.manifest.domain.RoutingSheet;

@Repository
public class RoutingSheetServiceImpl extends GenericServiceImpl<RoutingSheet> implements IRoutingSheetService{
	private IRoutingSheetDAO rsDAO;

	@Autowired
	public RoutingSheetServiceImpl(IRoutingSheetDAO routingSheetDAO){
		super(routingSheetDAO);
		this.rsDAO=routingSheetDAO;
	}

	public void exportToPdf(RoutingSheet rs, OutputStream stream) throws JRException, IOException{
		String template = "D:/template/report.jasper";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("RS_TITLE", rs.getTitle());
		params.put("RS_COURIER", rs.getCourier().getFio());
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		params.put("RS_DATE", df.format(rs.getDateCreated()));
		
		if ((template!=null)){
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(template, params, new JRBeanCollectionDataSource(rs.getWayBillList()));
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
			exporter.exportReport();
			
		}
	}
	
	public File exportToXls(RoutingSheet rs) throws FileNotFoundException,JRException{
		String report = "resources/pdf/routsheet_"+rs.getTitle()+".xls";
		String template = "D:/template/report.jasper";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("RS_TITLE", rs.getTitle());
		params.put("RS_COURIER", rs.getCourier().getFio());
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		params.put("RS_DATE", df.format(rs.getDateCreated()));
		
		if ((template!=null)&&(report!=null)){
			JasperExportManager.exportReportToHtmlFile(JasperFillManager.fillReport(template, params, new JRBeanCollectionDataSource(rs.getWayBillList())),report);
			return new File(report);
		}
		return null;
	}

	@Override
	@Transactional
	public List<RoutingSheet> getLastUseRS() {
		List<RoutingSheet> list=this.rsDAO.getAllEntity();
		if(list.size()>5){
			List<RoutingSheet> result = list.subList(list.size()-5, list.size());
			return result;
		} else {
			return list;
		}
	}
	
/*	@Transactional
	public boolean removeWayBillFromCollection(RoutingSheet rs,WayBill wbToRemove){
		if(rs.getWayBillList().contains(wbToRemove)){
			rs.removeWayBill(wbToRemove);
			this.wbDAO.saveEntity(wbToRemove);
			return true;
		}
		return false;
	}*/

}
