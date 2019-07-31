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
import com.aji.rumahsakit.rs.model.transaksi.DetailRekamMedis;
import com.aji.rumahsakit.rs.model.transaksi.RekamMedis;
import com.aji.rumahsakit.rs.service.RekamMedisService;

@RestController
@Controller
public class RekamMedisController {

	@Autowired
	private RekamMedisService rmService;
	
	@RequestMapping(value = "/rekammedis/{kode}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveByKode(@PathVariable String kode) throws ValidationException
	{
		 try 
		 {
			 System.out.println("try find");
			 RekamMedis rm=rmService.findByKode(kode);

			 return ResponseEntity.ok(rm);
		 }
		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/rekammedis/namapasien/{pasien}/namadokter/{dokter}/tanggal/{tanggal}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveByFilters(@PathVariable String pasien,@PathVariable String dokter,@PathVariable String tanggal) throws ValidationException
	{
		 try 
		 {
			List<RekamMedis> rmList=rmService.findByFilter(pasien, dokter, tanggal);

			return ResponseEntity.ok(rmList);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/rekammedis", method = RequestMethod.POST)
	public ResponseEntity<?> submit(@RequestBody RekamMedis rm) throws ValidationException{
		try {
			rmService.saveRekamMedisAndDetail(rm);
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
	
	@RequestMapping(value =  "/rekammedis", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody RekamMedis rm) throws Exception
	{
		 try 
		 {
			 rmService.updateRekamMedisAndDetail(rm);
			 MessageResponse mg = new MessageResponse("Update Success with id " + rm.getId());
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
	///////////////////////////////////detail/////////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = "/detailrekammedis", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveDetail() throws ValidationException
	{
		 try 
		 {
			List<DetailRekamMedis> detRmList=rmService.findDetailByFilter();

			return ResponseEntity.ok(detRmList);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
		
	@RequestMapping(value = "/detailrekammedis", method = RequestMethod.POST)
	public ResponseEntity<?> submitDetail(@RequestBody DetailRekamMedis detRm) throws ValidationException{
		try {
			rmService.saveDetail(detRm);
			MessageResponse mg  = new MessageResponse("success insert");
			
			return ResponseEntity.ok(mg);
			
		}
		catch(ValidationException val) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(val.getMessage());
			
		 }
		catch (Exception e) {
			System.out.println(e);
			MessageResponse mg = new MessageResponse("Failed insert" );
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		}
	}
	
	@RequestMapping(value =  "/detailrekammedis", method = RequestMethod.PUT)
	public ResponseEntity<?> updateDetail(@RequestBody DetailRekamMedis detRm) throws Exception
	{
		 try 
		 {
			 rmService.updateDetail(detRm);
			 MessageResponse mg = new MessageResponse("Update Success with id " + detRm.getId());
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
}
