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
import com.aji.rumahsakit.rs.model.transaksi.DetailTransaksiObat;
import com.aji.rumahsakit.rs.model.transaksi.TransaksiObat;
import com.aji.rumahsakit.rs.service.TransaksiObatService;

@RestController
@Controller
public class TransaksiObatController {

	@Autowired
	private TransaksiObatService obatService;
	
	@RequestMapping(value = "/transaksiobat/{kode}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveByKode(@PathVariable String kode) throws ValidationException
	{
		 try 
		 {
			 System.out.println("try find");
			 TransaksiObat obat=obatService.findByKode(kode);

			 return ResponseEntity.ok(obat);
		 }
		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/transaksiobat", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveAll() throws ValidationException
	{
		 try 
		 {
			List<TransaksiObat> obatList=obatService.findAll();

			return ResponseEntity.ok(obatList);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/transaksiobat/tanggal/{tanggal}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveByFilters(@PathVariable String tanggal) throws ValidationException
	{
		 try 
		 {
			List<TransaksiObat> obatList=obatService.findByFilter(tanggal);

			return ResponseEntity.ok(obatList);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}

	@RequestMapping(value = "/transaksiobat", method = RequestMethod.POST)
	public ResponseEntity<?> submit(@RequestBody TransaksiObat obat) throws ValidationException{
		try {
			obatService.saveTrxObatAndDetail(obat);
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
	
	@RequestMapping(value =  "/transaksiobat", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody TransaksiObat obat) throws Exception
	{
		 try 
		 {
			 obatService.update(obat);
			 MessageResponse mg = new MessageResponse("Update Success with id " + obat.getId());
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

	
	@RequestMapping(value = "/detailtransaksiobat", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveDetail() throws ValidationException
	{
		 try 
		 {
			List<DetailTransaksiObat> detList=obatService.findDetailByFilter();

			return ResponseEntity.ok(detList);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
		
	
	@RequestMapping(value = "/detailtransaksiobat", method = RequestMethod.POST)
	public ResponseEntity<?> submitDetail(@RequestBody DetailTransaksiObat detail) throws ValidationException{
		try {
			obatService.saveDetail(detail);
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
	
	@RequestMapping(value =  "/detailtransaksiobat", method = RequestMethod.PUT)
	public ResponseEntity<?> updateDetail(@RequestBody DetailTransaksiObat detail) throws Exception
	{
		 try 
		 {
			 obatService.updateDetail(detail);
			 MessageResponse mg = new MessageResponse("Update Success with id " + detail.getId());
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
