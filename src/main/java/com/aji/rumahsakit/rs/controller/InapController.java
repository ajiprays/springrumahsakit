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
import com.aji.rumahsakit.rs.model.transaksi.DetailTransaksiInap;
import com.aji.rumahsakit.rs.model.transaksi.SubDetailTransaksiInap;
import com.aji.rumahsakit.rs.model.transaksi.TransaksiInap;
import com.aji.rumahsakit.rs.service.TransaksiInapService;

@RestController
@Controller
public class InapController {

	@Autowired
	private TransaksiInapService inapService;
	
	@RequestMapping(value = "/inap/{kode}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieve(@PathVariable String kode) throws ValidationException
	{
		 try 
		 {
			 System.out.println("try find");
			 TransaksiInap inap=inapService.findByKode(kode);

			 return ResponseEntity.ok(inap);
		 }
		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/inap/pasien/{namapasien}/dokter/{namadokter}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieve(@PathVariable String namapasien,@PathVariable String namadokter) throws ValidationException
	{
		 try 
		 {
			List<TransaksiInap> inapList=inapService.findByFilter(namapasien, namadokter);

			return ResponseEntity.ok(inapList);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/inap", method = RequestMethod.POST)
	public ResponseEntity<?> submitInapAndDetail(@RequestBody TransaksiInap inap) throws ValidationException{
		try {
			inapService.saveInapAndDetail(inap);;
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
	
	@RequestMapping(value =  "/inap", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody TransaksiInap inap) throws Exception
	{
		 try 
		 {
			 inapService.updateInapAndDetailAndSub(inap);
			 MessageResponse mg = new MessageResponse("Update Success with id " + inap.getId());
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
	
	@RequestMapping(value = "/detailinap", method = RequestMethod.POST)
	public ResponseEntity<?> submitWithSub(@RequestBody DetailTransaksiInap detinap) throws ValidationException{
		try {
			inapService.saveDetail(detinap);
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
	
	@RequestMapping(value = "/detailinap", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveDetail() throws ValidationException
	{
		 try 
		 {
			List<DetailTransaksiInap> inapList=inapService.findDetailByFilter();

			return ResponseEntity.ok(inapList);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/subinap", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveSub() throws ValidationException
	{
		 try 
		 {
			List<SubDetailTransaksiInap> inapList=inapService.findSubByFilter();

			return ResponseEntity.ok(inapList);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	
	@RequestMapping(value = "/subinap", method = RequestMethod.POST)
	public ResponseEntity<?> submitWithSub(@RequestBody TransaksiInap inap) throws ValidationException{
		try {
			inapService.saveInapAndDetailAndSub(inap);
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
}
