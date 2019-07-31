package com.aji.rumahsakit.rs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aji.rumahsakit.rs.exception.MessageResponse;
import com.aji.rumahsakit.rs.exception.ValidationException;
import com.aji.rumahsakit.rs.model.transaksi.DetailPemeriksaanDiagnosa;
import com.aji.rumahsakit.rs.model.transaksi.DetailPemeriksaanTindakan;
import com.aji.rumahsakit.rs.model.transaksi.Pemeriksaan;
import com.aji.rumahsakit.rs.service.PemeriksaanService;

@RestController
@Controller
public class PemeriksaanController {

	@Autowired
	private PemeriksaanService pemService;
	
	@RequestMapping(value = "/pemeriksaan/{kode}", method = RequestMethod.GET)
	public ResponseEntity<?> retrievebyKode(@PathVariable String kode) throws ValidationException
	{
		 try 
		 {
			 System.out.println("try find");
			 Pemeriksaan pem=pemService.findByKode(kode);

			 return ResponseEntity.ok(pem);
		 }
		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/pemeriksaan/nama/{nama}/jenis/{jenisPerawatan}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveByFilters(@PathVariable String nama,@PathVariable String jenisPerawatan) throws ValidationException
	{
		 try 
		 {
			List<Pemeriksaan> pemList=pemService.findByFilter(nama, jenisPerawatan);

			return ResponseEntity.ok(pemList);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/pemeriksaan", method = RequestMethod.POST)
	public ResponseEntity<?> submit(@RequestBody Pemeriksaan pem) throws ValidationException{
		try {
			pemService.savePemeriksaanAndDetail(pem);;
			MessageResponse mg  = new MessageResponse("success insert");
			
			return ResponseEntity.ok(mg);
			
		}
		catch(ValidationException val) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(val.getMessage());
			
		 }
		catch (Exception e) {
			MessageResponse mg = new MessageResponse("Failed insert" );
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		}
	}
	
	@RequestMapping(value =  "/pemeriksaan", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody Pemeriksaan pem) throws Exception
	{
		 try 
		 {
			 pemService.update(pem);
			 MessageResponse mg = new MessageResponse("Update Success with id " + pem.getId());
			 return ResponseEntity.ok(mg);
		 }
		 		 
		 catch(ValidationException val) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(val.getMessage());
				
		 }
		catch (Exception e) {
			MessageResponse mg = new MessageResponse("Failed update" );
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		}
	}
	
	@RequestMapping(value = "/detailpemeriksaan/tindakan", method = RequestMethod.POST)
	public ResponseEntity<?> submitTindakan(@RequestBody DetailPemeriksaanTindakan tind) throws ValidationException{
		try {
			pemService.saveTindakan(tind);
			MessageResponse mg  = new MessageResponse("success insert");
			
			return ResponseEntity.ok(mg);
			
		}
		catch(ValidationException val) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(val.getMessage());
			
		 }
		catch (Exception e) {
			MessageResponse mg = new MessageResponse("Failed insert" );
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		}
	}
	
	@RequestMapping(value = "/detailpemeriksaan/diagnosa", method = RequestMethod.POST)
	public ResponseEntity<?> submitDiagnosa(@RequestBody DetailPemeriksaanDiagnosa diag) throws ValidationException{
		try {
			pemService.saveDiagnosa(diag);
			MessageResponse mg  = new MessageResponse("success insert");
			
			return ResponseEntity.ok(mg);
			
		}
		catch(ValidationException val) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(val.getMessage());
			
		 }
		catch (Exception e) {
			MessageResponse mg = new MessageResponse("Failed insert" );
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		}
	}
	
}
