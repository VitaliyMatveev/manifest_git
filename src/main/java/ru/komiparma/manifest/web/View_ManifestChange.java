package ru.komiparma.manifest.web;

import java.util.Calendar;
import java.util.List;
import javax.faces.bean.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.komiparma.manifest.domain.Manifest;
import ru.komiparma.manifest.domain.WayBill;
import ru.komiparma.manifest.service.IManifestService;
import ru.komiparma.manifest.service.IWayBillService;

@Component
@RequestScoped
public class View_ManifestChange {
	@Autowired
	private IManifestService mnfSrv;
	private List<Manifest> manifetstList;
	private Manifest manifest;
	
	@Autowired
	private IWayBillService wbSrv;
	public Manifest getManifest() {
		return manifest;
	}

	public void setManifest(Manifest manifest) {
		this.manifest = manifest;
	}

	public List<Manifest> getManifetstList() {
		return manifetstList;
	}

	public List<Manifest> manifestList(String city){
			List<Manifest> list=this.mnfSrv.getManifestPeriodDate(city, Calendar.getInstance().getTime());
		return list;
	}
	
	public void setManifetstList(List<Manifest> manifetstList) {
		this.manifetstList = manifetstList;
	}
	
	public void setManifest(List<String> listWBtitle){
		WayBill wb;
		if(manifest!=null){
			for(String wbt:listWBtitle){
				wb=this.wbSrv.get(wbt);
				System.out.println(wb.getTitle());
				manifest.addWayBill(wb);
			}
			this.mnfSrv.save(manifest);
		}
	}

	
}
